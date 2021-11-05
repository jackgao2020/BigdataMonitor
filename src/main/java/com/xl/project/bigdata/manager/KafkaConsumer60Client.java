package com.xl.project.bigdata.manager;

import com.alibaba.fastjson.JSON;
import com.xl.project.bigdata.bean.NumberOfOpenFilesBean;
import com.xl.project.bigdata.util.dingding.DingApp;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class KafkaConsumer60Client {

    public static volatile KafkaConsumer<String, String> kafkaConsumer;

    public static Properties initKafkaConsumer(String servers, String groupId, int maxPollRecords, boolean commit) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", servers); // "hmaster:9092"
        properties.put("group.id", groupId);
        if (commit) {
            properties.put("enable.auto.commit", "true");
            properties.put("auto.commit.interval.ms", "1000");
        } else {
            properties.put("enable.auto.commit", "false");
        }
//        properties.put("enable.auto.commit", "false");
        properties.put("auto.offset.reset", "earliest");  // earliest  latest
        properties.put("session.timeout.ms", "30000");
//        properties.put("max.poll.interval.ms", "3000");
        properties.put("max.poll.records", maxPollRecords); // 50
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return properties;
    }

    public static synchronized KafkaConsumer<String, String> getKafkaConsumer(String servers, String topic, String groupId, int maxPollRecords, boolean commit) {

        if (kafkaConsumer == null) {
            synchronized (KafkaConsumer60Client.class) {
                if (kafkaConsumer == null) {
                    Properties properties = initKafkaConsumer(servers, groupId, maxPollRecords, commit);
                    kafkaConsumer = new KafkaConsumer<>(properties);
                    kafkaConsumer.subscribe(Arrays.asList(topic));
                    System.out.println("KafkaConsumer60Client kafkaConsumer初期化完成...");

                } else {
                    System.out.println("KafkaConsumer60Client kafkaConsumer重复使用中");
                }
            }
        } else {
            System.out.println("KafkaConsumer60Client kafkaConsumer重复使用中");
        }

        return kafkaConsumer;
    }


    public static KafkaConsumer<String, String> getKafkaConsumerOnly(String servers, String topic, String groupId, int maxPollRecords, boolean commit) {


        Properties properties = initKafkaConsumer(servers, groupId, maxPollRecords, commit);
        KafkaConsumer<String, String> kafkaConsumerOnly = new KafkaConsumer<>(properties);
        kafkaConsumerOnly.subscribe(Arrays.asList(topic));
        System.out.println("KafkaConsumer60Client kafkaConsumer初期化完成...");

        return kafkaConsumerOnly;
    }

    public static synchronized void closeKafkaConsumer() {
        if (kafkaConsumer != null) {
            kafkaConsumer.close();
        }
    }

    public static void main(String[] args) {

        String servers60 = "10.155.20.62:9092, 10.155.20.63:9092, 10.155.20.64:9092";
        String topicMonitor60 = "lx_monitor_log";  // 监控文件打开数t
        String groupMonitor60 = "lx_monitor_log—001";
        int maxPollRecords = 50;
        boolean commit = false;

        KafkaConsumer<String, String> kafkaConsumer = getKafkaConsumer(servers60, topicMonitor60, groupMonitor60, maxPollRecords, commit);

        try{
            while(true){


                StringBuffer sb = new StringBuffer();

                if(kafkaConsumer == null){
                    kafkaConsumer = getKafkaConsumer(servers60, topicMonitor60, groupMonitor60, maxPollRecords, commit);
                }

                ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    String json = record.value();
                    String numberOfFilesData  = JSON.parseObject(json).getString("message");//
                    // 解析Bean
                    NumberOfOpenFilesBean numberOfOpenFilesBean = convertToNumberOfOpenFiles(numberOfFilesData);


                   if(numberOfOpenFilesBean != null && numberOfOpenFilesBean.getKafka_open_file() != null && !"".equals(numberOfOpenFilesBean.getKafka_open_file())){

                       String kafka_open_file = numberOfOpenFilesBean.getKafka_open_file();
                       Integer integer = Integer.valueOf(kafka_open_file);

                       if(integer.longValue() > 10000l){
                           sb.append("IP：").append(numberOfOpenFilesBean.getIp());
                           sb.append("  kafka-open-file-count:").append(numberOfOpenFilesBean.getKafka_open_file());
                       }


                   }


                }

                // 发送钉钉 Kafka打开文件大于1W
                if(sb.length() >= 2)
                DingApp.sendDingDing(sb.toString());


            }
        } catch (Exception e){
            kafkaConsumer.close();
            kafkaConsumer = null;
            e.printStackTrace();
        }
    }



    public static NumberOfOpenFilesBean convertToNumberOfOpenFiles(String numberOfFilesData ){


        NumberOfOpenFilesBean numberOfOpenFilesBean = new NumberOfOpenFilesBean();


        String[] numberOfFilesArray = numberOfFilesData.split(",");

        try{
            for(String numberOfFile : numberOfFilesArray){
                if(numberOfFile.contains("date_time:")){
                    String data_time = numberOfFile.split("date_time:")[1];
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date node_time_temp = simpleDateFormat.parse(data_time);
                    SimpleDateFormat simpleformat = new SimpleDateFormat("yyyyMMddHHmmss");
                    String date_time = simpleformat.format(node_time_temp);
                    numberOfOpenFilesBean.setDate_time(date_time);
                }
                if(numberOfFile.contains("ip:")){
                    String ip = numberOfFile.split("ip:")[1];
                    numberOfOpenFilesBean.setIp(ip);
                }
                if(numberOfFile.contains("icpu_num:")){
                    String icpu_num = numberOfFile.split("icpu_num:")[1];
                    numberOfOpenFilesBean.setIcpu_num(icpu_num);
                }
                if(numberOfFile.contains("cpu_user:")){
                    String cpu_user = numberOfFile.split("cpu_user:")[1];
                    numberOfOpenFilesBean.setCpu_user(cpu_user);
                }
                if(numberOfFile.contains("cpu_load_1min:")){
                    String cpu_load_1min = numberOfFile.split("cpu_load_1min:")[1];
                    numberOfOpenFilesBean.setCpu_load_1min(cpu_load_1min);
                }
                if(numberOfFile.contains("mem_total:")){
                    String mem_total = numberOfFile.split("mem_total:")[1];
                    numberOfOpenFilesBean.setMem_total(mem_total);
                }
                if(numberOfFile.contains("mem_sys_used:")){
                    String mem_sys_used = numberOfFile.split("mem_sys_used:")[1];
                    numberOfOpenFilesBean.setMem_sys_used(mem_sys_used);
                }
                if(numberOfFile.contains("mem_sys_free:")){
                    String mem_sys_free = numberOfFile.split("mem_sys_free:")[1];
                    numberOfOpenFilesBean.setMem_sys_free(mem_sys_free);
                }
                if(numberOfFile.contains("mem_buffer_used:")){
                    String mem_buffer_used = numberOfFile.split("mem_buffer_used:")[1];
                    numberOfOpenFilesBean.setMem_buffer_used(mem_buffer_used);
                }
                if(numberOfFile.contains("mem_swap_total:")){
                    String mem_swap_total = numberOfFile.split("mem_swap_total:")[1];
                    numberOfOpenFilesBean.setMem_swap_total(mem_swap_total);
                }
                if(numberOfFile.contains("mem_swap_used:")){
                    String mem_swap_used = numberOfFile.split("mem_swap_used:")[1];
                    numberOfOpenFilesBean.setMem_swap_used(mem_swap_used);
                }
                if(numberOfFile.contains("mem_swap_free:")){
                    String mem_swap_free = numberOfFile.split("mem_swap_free:")[1];
                    numberOfOpenFilesBean.setMem_swap_free(mem_swap_free);
                }
                if(numberOfFile.contains("disk_sda_rs:")){
                    String disk_sda_rs = numberOfFile.split("disk_sda_rs:")[1];
                    numberOfOpenFilesBean.setDisk_sda_rs(disk_sda_rs);
                }
                if(numberOfFile.contains("disk_sda_ws:")){
                    String disk_sda_ws = numberOfFile.split("disk_sda_ws:")[1];
                    numberOfOpenFilesBean.setDisk_sda_ws(disk_sda_ws);
                }
                if(numberOfFile.contains("rx:")){
                    String rx = numberOfFile.split("rx:")[1];
                    numberOfOpenFilesBean.setRx(rx);
                }
                if(numberOfFile.contains("tx:")){
                    String tx = numberOfFile.split("tx:")[1];
                    numberOfOpenFilesBean.setTx(tx);
                }
                if(numberOfFile.contains("hbase_open_file:")){
                    String hbase_open_file = numberOfFile.split("hbase_open_file:")[1];
                    numberOfOpenFilesBean.setHbase_open_file(hbase_open_file);
                }
                if(numberOfFile.contains("hdfs_open_file:")){
                    String hdfs_open_file = numberOfFile.split("hdfs_open_file:")[1];
                    numberOfOpenFilesBean.setHdfs_open_file(hdfs_open_file);
                }
                if(numberOfFile.contains("hive_open_file:")){
                    String hive_open_file = numberOfFile.split("hive_open_file:")[1];
                    numberOfOpenFilesBean.setHive_open_file(hive_open_file);
                }
                if(numberOfFile.contains("kafka_open_file:")){
                    String kafka_open_file = numberOfFile.split("kafka_open_file:")[1];
                    numberOfOpenFilesBean.setKafka_open_file(kafka_open_file);
                }
                if(numberOfFile.contains("zookeeper_open_file:")){
                    String zookeeper_open_file = numberOfFile.split("zookeeper_open_file:")[1];
                    numberOfOpenFilesBean.setZookeeper_open_file(zookeeper_open_file);
                }
                if(numberOfFile.contains("es_open_file:")){
                    String es_open_file = numberOfFile.split("es_open_file:")[1];
                    numberOfOpenFilesBean.setEs_open_file(es_open_file);
                }
                if(numberOfFile.contains("oozie_open_file:")){
                    String oozie_open_file = numberOfFile.split("oozie_open_file:")[1];
                    numberOfOpenFilesBean.setOozie_open_file(oozie_open_file);
                }
                if(numberOfFile.contains("mysql_open_file:")){
                    String mysql_open_file = numberOfFile.split("mysql_open_file:")[1];
                    numberOfOpenFilesBean.setMysql_open_file(mysql_open_file);
                }
                if(numberOfFile.contains("path:")){
                    String path = numberOfFile.split("path:")[1];
                    numberOfOpenFilesBean.setPath(path);
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return   numberOfOpenFilesBean;
    }
}
