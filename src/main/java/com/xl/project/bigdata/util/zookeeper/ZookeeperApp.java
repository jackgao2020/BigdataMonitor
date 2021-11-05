package com.xl.project.bigdata.util.zookeeper;

import com.xl.project.bigdata.util.KafkaInfoClient;
import com.xl.project.bigdata.util.dingding.DingApp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: letu
 * @description:
 * @author: XL.Gao
 * @create: 2021-05-11 10:55
 **/
public class ZookeeperApp {

    private static String connectionInfo = "lx-cdh-08:2181";

    private static String brokers = "lx-cdh-08";

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        int i = 0;
        while (i < 100){

            long start = System.currentTimeMillis();

            ZookeeperTools zookeeperTools = new ZookeeperTools().getInstanceHolder(connectionInfo);

            String offset = "/kafka/consumers/NewStreamingCusCombin20200501/offsets/pre_lecc_customer";

            String topic = "pre_lecc_customer";

            long lag = startKafkaOffsetOverStock(offset, zookeeperTools, brokers, topic);
            String format = dateTimeFormatter.format(LocalDateTime.now());

            String textMsg = "{ \"msgtype\": \"text\", " +
                    "\"text\": {\"content\": \"【监控】" +format +" -- Kafka积压监控：用户合并程序积压数量条数-" +lag+"\"}," +
                    "\"at\":{\"atMobiles\":[\"18561808500\"],\"isAtAll\":true}}";

            try {
                boolean b = DingApp.sendDingDing(textMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }

            zookeeperTools.closeClient();

            long end = System.currentTimeMillis();
            System.out.println("执行次数："+ i++ +" -- 执行时间：" + (end - start));
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

//        zookeeperTools.getCuratorFramework(connectionInfo);
//
//        System.out.println("createPath:" + zookeeperTools.createPath("/kafka/consumers/test02"));
//
//        System.out.println("getData:" + zookeeperTools.getData("/kafka/consumers/test02"));
//
//        zookeeperTools.setData("/kafka/consumers/test02", "hello");
//
//        System.out.println("getData:" + zookeeperTools.getData("/kafka/consumers/test02"));

    }


    /*
     * 功能描述: 监控Kafka的数据是否有延迟
     *
     * @Param: [offset ：zookeeper保存offset的路径, zookeeperTools, broker：kafka的broker, topic：监控的kafka的topic名称]
     * @Return: int 积压的数据量
     * @Author: XL.Gao
     * @Date: 2021/5/12 13:40
     */
    public static long startKafkaOffsetOverStock(String offset, ZookeeperTools zookeeperTools, String broker, String topic) {

        long start = System.currentTimeMillis();
        String format = dateTimeFormatter.format(LocalDateTime.now());

        Map<Integer, Long> integerLongHashMap = new HashMap<>();

        List<String> childrenPath = zookeeperTools.getChildrenPath(offset);
        childrenPath.forEach(path -> {

            String offsetPath = offset + "/" + path;

            String data = zookeeperTools.getData(offsetPath);

            integerLongHashMap.put(new Integer(path), new Long(data));

        });
        long startZK = System.currentTimeMillis();
        KafkaInfoClient kafkaInfoClient = new KafkaInfoClient(broker);
        Map<Integer, Long> pre_lecc_customer = kafkaInfoClient.getLatestOffset(topic);
//        System.out.println(pre_lecc_customer.toString());

        long lag = 0;
        if (integerLongHashMap != null && pre_lecc_customer != null) {
            for (Integer key : integerLongHashMap.keySet()) {
                System.out.println("ZookeeperApp  -- 监控kafka的消费是否积压 --  " + format + "-- 分区：" + key + " -- 消费者提交Offset：" + integerLongHashMap.get(new Integer(key)) + " -- kafka当前Offset：" + pre_lecc_customer.get(new Integer(key)));

                lag = lag + pre_lecc_customer.get(new Integer(key)) - integerLongHashMap.get(new Integer(key));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("ZookeeperApp  -- 监控kafka的消费是否积压 --  " + format + " -- lag : " + lag +" -- 获取ZK的时间(ms):"+(startZK - start) +" -- 获取Kafka的时间(ms):"+(end - startZK));


        return lag;

    }
}
