package com.xl.project.bigdata.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xl.project.bigdata.bean.KafkaMonitorBean;
import com.xl.project.bigdata.bean.KafkaOffsetsBean;
import com.xl.project.bigdata.bean.NumberOfOpenFilesBean;
import com.xl.project.bigdata.manager.KafkaConsumer60Client;
import com.xl.project.bigdata.util.dingding.DingApp;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.PartitionInfo;

import java.io.IOException;
import java.util.*;

/**
 * Copyright (C), 2015-2019, 乐信云科技有限公司
 * FileName: CustomerApp
 * Author:   GaoXL
 * Date:     2021/4/12 9:16
 * Description: Kafka监控的具体指标信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * GaoXL             2021/4/12 9:16..V1.0.............大数据
 */
public class KafkaMonitorAPP {

//    private static String servers = "lx-cs-05:9092";
//    private static String serverIps = "lx-cs-05";
//    private static String cluster = "cs-86";
//    private static String esServers = "10.155.20.86";


    public static void main(String[] args) {

    }

    public static String kafkaOpenFileCountMonitor(){
        String servers60 = "10.155.20.62:9092, 10.155.20.63:9092, 10.155.20.64:9092";
        String topicMonitor60 = "lx_monitor_log";  // 监控文件打开数t
        String groupMonitor60 = "lx_monitor_log—001";
        int maxPollRecords = 200;
        boolean commit = true;

        KafkaConsumer<String, String> kafkaConsumer = KafkaConsumer60Client.getKafkaConsumer(servers60, topicMonitor60, groupMonitor60, maxPollRecords, commit);

        Map<String, String> hashMap = new HashMap<String, String>();

        try{
            int i = 0;

            while(true){

                ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    String json = record.value();
                    String numberOfFilesData  = JSON.parseObject(json).getString("message");//
                    // 解析Bean
                    NumberOfOpenFilesBean numberOfOpenFilesBean = KafkaConsumer60Client.convertToNumberOfOpenFiles(numberOfFilesData);


                    if(numberOfOpenFilesBean != null && !"".equals(numberOfOpenFilesBean.getKafka_open_file()) && numberOfOpenFilesBean.getKafka_open_file()!= null){

                        String kafka_open_file = numberOfOpenFilesBean.getKafka_open_file();
                        Integer integer = Integer.valueOf(kafka_open_file);

                        if(integer.longValue() > 3000){

                            hashMap.put(numberOfOpenFilesBean.getIp(), numberOfOpenFilesBean.getKafka_open_file());
                        }
                    }
                }

                i++;
                if(i >= 20){
                    break;
                }
            }

            // 发送钉钉 Kafka打开文件大于1W
            if(hashMap.size() > 0)
                return  hashMap.toString();
        } catch (Exception e){
            kafkaConsumer.close();
            kafkaConsumer = null;
            e.printStackTrace();
        }

        return "";
    }

    public  static List<KafkaOffsetsBean> kafkaOffsetsMonitor(){

        HttpClient client = HttpClients.createDefault();

        String url = "http://10.155.20.100:8001/group/NewStreamingCusCombin0719001";
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");

        CloseableHttpResponse response2 = null;

        HttpResponse execute = null;
        try {
            execute = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity2 = execute.getEntity();

        String htmlJson = null;
        try {
            htmlJson = EntityUtils.toString(entity2, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonAlarmMsg = JSON.parseObject(htmlJson);
        JSONArray offsets1 = jsonAlarmMsg.getJSONArray("offsets");

        java.util.List<KafkaOffsetsBean> list = new java.util.ArrayList<>();

        String groupAll = "";
        String topicAll = "";
        String partitionAll = "";
        Long offsetAll = 0l;
        Long logSizeAll = 0l;
        Long modifiedAll = 0l;
        long lagAll = 0l;

        for(int i = 0; i < offsets1.size(); i++){
            JSONObject jsonObject = (JSONObject) offsets1.get(i);

            String group = jsonObject.getString("group");
            String topic = jsonObject.getString("topic");
            String partition = jsonObject.getString("partition");
            Long offset = jsonObject.getLong("offset");
            Long logSize = jsonObject.getLong("logSize");
            Long modified = jsonObject.getLong("modified");

            if("pre_lecc_customer".equals(topic)){
                long lag = logSize - offset;
                KafkaOffsetsBean bean = new KafkaOffsetsBean();
                bean.setGroup(group);
                bean.setTopic(topic);
                bean.setPartition(partition);
                bean.setOffset(offset);
                bean.setLogSize(logSize);
                bean.setCreateTime(modified);
                bean.setLag(lag);

                groupAll = group;
                topicAll = topic;
                partitionAll = "all";
                offsetAll = offsetAll + offset;
                logSizeAll = logSizeAll + logSize;
                modifiedAll = modified;
                lagAll = lagAll + lag;

                list.add(bean);
            }

        }

        KafkaOffsetsBean bean = new KafkaOffsetsBean();
        bean.setGroup(groupAll);
        bean.setTopic(topicAll);
        bean.setPartition(partitionAll);
        bean.setOffset(offsetAll);
        bean.setLogSize(logSizeAll);
        bean.setCreateTime(modifiedAll);
        bean.setLag(lagAll);
        list.add(bean);

        return list;
    }

    public static List<KafkaMonitorBean> kafkaMoniter(String kafkaServers, String kafkaServerIps, String kafkaCluster){


        long s = System.currentTimeMillis();

        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaServers); // "hmaster:9092"
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        KafkaInfoClient kafkaInfoClient = new KafkaInfoClient(kafkaServerIps);

        Map<String, List<PartitionInfo>> stringListMap = kafkaConsumer.listTopics();



        List<KafkaMonitorBean> kafkaMonitorBeans = new ArrayList<>();

        for (String topic : stringListMap.keySet()) {

            long start = System.currentTimeMillis();
//            System.out.println(topic);
            List<PartitionInfo> lists = stringListMap.get(topic);

            Map<String, String> mapIp = new HashedMap();
            lists.forEach(par -> {
                Node leader = par.leader();
                String host = leader.host();
                mapIp.put(host, "");
            });

            KafkaMonitorBean kafkaMonitorBean = new KafkaMonitorBean();
            kafkaMonitorBean.setCluster(kafkaCluster);
            kafkaMonitorBean.setTopic(topic);
            if (lists != null)
                kafkaMonitorBean.setPartitionsSize(lists.size());
            if(mapIp != null)
                kafkaMonitorBean.setBrokersSize(mapIp.size());

            Map<Integer, Long> latestOffsetMap = kafkaInfoClient.getLatestOffset(topic);
            String latestOffset = JSON.toJSONString(latestOffsetMap);
            kafkaMonitorBean.setLatestOffset(latestOffset);

            long allMessageSize = 0;

            long maxLatestOffset = 0;

            long avgLatestOffset = 0;

            long minLatestOffset = 0;
            for(long offset : latestOffsetMap.values()){
                allMessageSize = allMessageSize + offset;

                if(maxLatestOffset < offset){
                    maxLatestOffset = offset;
                }

                if(minLatestOffset > offset){
                    minLatestOffset = offset;
                } else if(minLatestOffset == 0){
                    minLatestOffset = offset;
                }
            }
            if(latestOffsetMap != null && allMessageSize != 0){
                avgLatestOffset = allMessageSize/ latestOffsetMap.size();
            }
            kafkaMonitorBean.setAllMessageSize(allMessageSize);
            kafkaMonitorBean.setMaxLatestOffset(maxLatestOffset);
            kafkaMonitorBean.setAvgLatestOffset(avgLatestOffset);
            kafkaMonitorBean.setMinLatestOffset(minLatestOffset);


            Map<Integer, Long> earliestOffsetMap = kafkaInfoClient.getEarliestOffset(topic);
            String earliestOffset = JSON.toJSONString(earliestOffsetMap);
            kafkaMonitorBean.setEarliestOffset(earliestOffset);

            long curMessageSize = 0;

            long maxEarliestOffset = 0;

            long avgEarliestOffset = 0;

            long minEarliestOffset = 0;
            for(long offset : earliestOffsetMap.values()){
                curMessageSize = curMessageSize + offset;

                if(maxEarliestOffset < offset){
                    maxEarliestOffset = offset;
                }

                if(minEarliestOffset > offset){
                    minEarliestOffset = offset;
                } else if(minEarliestOffset == 0){
                    minEarliestOffset = offset;
                }
            }
            if(earliestOffsetMap != null && curMessageSize != 0){
                avgEarliestOffset = curMessageSize/ earliestOffsetMap.size();
            }
            kafkaMonitorBean.setCurMessageSize(curMessageSize);
            kafkaMonitorBean.setMaxEarliestOffset(maxEarliestOffset);
            kafkaMonitorBean.setAvgEarliestOffset(avgEarliestOffset);
            kafkaMonitorBean.setMinEarliestOffset(minEarliestOffset);

            long perMessageSize = 0;
            kafkaMonitorBean.setPerMessageSize(perMessageSize);

            String id = kafkaCluster+"-"+topic;
            kafkaMonitorBean.setId(id);

            long end = System.currentTimeMillis();
            kafkaMonitorBean.setInsertDate(end);

            kafkaMonitorBeans.add(kafkaMonitorBean);

            System.out.println("KafkaMonitorAPP - Topic:" + topic + " - partitionSize:" + lists.size() + " - BrokerSize:" + mapIp.size() + " - time(ms):" + (end - start));
        }
        long e = System.currentTimeMillis();
        System.out.println("KafkaMonitorAPP - 获取Kafka基本信息花费的时间(ms)" + (e - s));

        return kafkaMonitorBeans;
    }
}
