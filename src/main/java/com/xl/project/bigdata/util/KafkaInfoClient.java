package com.xl.project.bigdata.util;

/**
 * Copyright (C), 2015-2019, 乐信云科技有限公司
 * FileName: CustomerApp
 * Author:   GaoXL
 * Date:     2021/4/10 11:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * GaoXL             2021/4/10 11:21..V1.0.............大数据
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.api.OffsetRequest;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.TopicAndPartition;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.consumer.SimpleConsumer;
import java.util.*;

public class KafkaInfoClient {
    private Logger logger = LoggerFactory.getLogger(KafkaInfoClient.class);

    private HashMap<String, Integer> hosts;
    private int timeOut = 30000;
    private int bufferSize = 64 * 1000000;
    private String clientID;

    public KafkaInfoClient(String server) {
        init(server);
    }

    private void init(String server) {
        hosts = new HashMap<>();

        if(server != null && !"".equals(server) && server.contains(",")){
            String[] ids = server.split(",");
            for(String id : ids){
                hosts.put(id, 9092);
            }
        } else {
            hosts.put(server, 9092);
        }
        clientID = "kafka_info_client_" + System.currentTimeMillis();
    }

    public Map<Integer, Long> getLatestOffset(String topic) {
        //kafka.api.OffsetRequest.LatestTime() = -1
        return getTopicOffset(topic, OffsetRequest.LatestTime());
    }

    public Map<Integer, Long> getEarliestOffset(String topic) {
        //kafka.api.OffsetRequest.EarliestTime() = -2
        return getTopicOffset(topic, OffsetRequest.EarliestTime());
    }


    /***
     * 获取指定 topic 的所有分区 offset
     * @param topic
     * @param whichTime   要获取offset的时间,-1 最新，-2 最早
     * @return
     */
    public Map<Integer, Long> getTopicOffset(String topic, long whichTime) {
        HashMap<Integer, Long> offsets = new HashMap<>();
        TreeMap<Integer, PartitionMetadata> leaders = this.findLeader(hosts, topic);
        leaders.forEach((part, matedata) -> {
            String leadBroker = matedata.leader().host();
            int leadPort = matedata.leader().port();
            SimpleConsumer consumer = new SimpleConsumer(leadBroker, leadPort, timeOut, bufferSize, clientID);
            long partationOffset = this.getPartationOffset(consumer, topic, part, whichTime);
            offsets.put(part, partationOffset);
            consumer.close();
        });
        return offsets;
    }

    /***
     * 获取 offset
     * @param consumer SimpleConsumer
     * @param topic topic
     * @param partition partition
     * @param whichTime 要获取offset的时间,-1 最新，-2 最早
     * @return
     */
    private long getPartationOffset(SimpleConsumer consumer, String topic, int partition, long whichTime) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic,
                partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
        //PartitionOffsetRequestInfo(long time, int maxNumOffsets) 中的第二个参数maxNumOffsets，没弄明白是什么意思，但是测试后发现传入1 时返回whichTime 对应的offset，传入2 返回一个包含最大和最小offset的元组

        kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo, OffsetRequest.CurrentVersion(), consumer.clientId());

        OffsetResponse response = consumer.getOffsetsBefore(request);

        if (response.hasError()) {
            logger.error("Error fetching data Offset Data the Broker. Reason:{}", response.errorCode(topic, partition));
            return 0;
        }
        long[] offsets = response.offsets(topic, partition);
        return offsets[0];
    }

    /***
     * 获取每个 partition 元数据信息
     * @param bootstraps (host,port)
     * @param topic topic
     * @return
     */
    private TreeMap<Integer, PartitionMetadata> findLeader(Map<String, Integer> bootstraps, String topic) {
        TreeMap<Integer, PartitionMetadata> map = new TreeMap<>();
        loop:
        for (Map.Entry<String, Integer> bootstrap : bootstraps.entrySet()) {
            SimpleConsumer consumer = null;
            try {
                consumer = new SimpleConsumer(bootstrap.getKey(), bootstrap.getValue(), timeOut, bufferSize, clientID);
                List<String> topics = Collections.singletonList(topic);
                TopicMetadataRequest req = new TopicMetadataRequest(topics);
                kafka.javaapi.TopicMetadataResponse resp = consumer.send(req);

                List<TopicMetadata> metaData = resp.topicsMetadata();
                for (TopicMetadata item : metaData) {
                    for (PartitionMetadata part : item.partitionsMetadata()) {
                        map.put(part.partitionId(), part);
                    }
                }
            } catch (Exception e) {
                logger.error("Error communicating with Broker [{}] to find Leader for [{}] Reason: ", bootstrap, topic, e);
            } finally {
                if (consumer != null)
                    consumer.close();
            }
        }
        return map;
    }
}
