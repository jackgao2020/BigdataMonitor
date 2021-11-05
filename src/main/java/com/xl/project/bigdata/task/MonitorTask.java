package com.xl.project.bigdata.task;

import com.xl.common.utils.StringUtils;
import com.xl.project.bigdata.bean.HbaseMonitorBean;
import com.xl.project.bigdata.bean.KafkaMonitorBean;
import com.xl.project.bigdata.bean.KafkaOffsetsBean;
import com.xl.project.bigdata.bean.NginxBean;
import com.xl.project.bigdata.manager.ES60Client;
import com.xl.project.bigdata.manager.HbaseClient;
import com.xl.project.bigdata.manager.MysqlClient;
import com.xl.project.bigdata.util.*;
import com.xl.project.bigdata.util.dingding.DingApp;
import com.xl.project.bigdata.util.zookeeper.ZookeeperApp;
import com.xl.project.bigdata.util.zookeeper.ZookeeperTools;
import org.elasticsearch.client.transport.TransportClient;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.Document;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("monitorTask")
@RequestMapping("/monitorTask")
public class MonitorTask {

    private static final Logger LOG = LoggerFactory
            .getLogger(MonitorTask.class);

    @Value("${xl.name}")
    private String xlName;

    @Value("${zookeeper.jq45.ip}")
    private String zookeeper45Ip;

    @Value("${zookeeper.jq45.customerPath}")
    private String zookeeper45CustomerPath;

    @Value("${kafka.jq45.brokersIp}")
    private String brokers45Ip;

    @Value("${kafka.jq45.topic}")
    private String kafka45Topic;

    @Value("${kafka.jq60.cluster}")
    private String kafka60Cluster;

    @Value("${kafka.jq60.brokersIp}")
    private String kafka60BrokersIp;

    @Value("${kafka.jq60.brokers}")
    private String kafka60Brokers;

    @Value("${monitor.mysql.tableName}")
    private String monitorMysqlTableName;

    private Map<String, Long> timeMap = new HashMap<>();

    public static void main(String[] args) {

//        MonitorTask.startMonitorClusterStateTask();
        new MonitorTask().startMonitorClusterStateTask();
        LOG.info("\n任务启动...");
//        int i = 0;
//        while (true){
//
//            new MonitorTask().startMonitorKafkaOpenFileCount60();
//            i++;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if(i >= 10){
//                break;
//            }
//        }

    }

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() {

//        int count = ESMonitorApp.startMonitorClusterState();

//        System.out.println("定时任务_monitor_admin_cluster_state_写入数据量：" + count);
    }

    /**
     * 功能描述：监控HBase的读写请求
     */
    public void startHbaseMonitor86() {

        // 监控测试集群的hbase
        HbaseMonitorApp.startHbaseMonitor("lx-cs-04,lx-cs-05", "86");

    }

    /**
     * 功能描述：监控HBase的读写请求
     */
    public void startHbaseMonitor60() {

        // 监控测试集群的hbase

        // 监控测试集群的hbase
        long start = System.currentTimeMillis();
        long conNum = 0;
        List<HbaseMonitorBean> hbaseMonitorBeans =
                HbaseMonitorApp.startHbaseMonitor("lx-es-08,lx-es-09", "60");
        for(HbaseMonitorBean bean : hbaseMonitorBeans){
            if(bean != null && "all".equals(bean.getServerName())){
                conNum = bean.getRequestsPerSecond();
            }
        }

        if(true){

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String format = dateTimeFormatter.format(LocalDateTime.now());
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
            String hour = sdf.format(new java.util.Date());
            long startZK = System.currentTimeMillis();
            String textMsg = "{ \"msgtype\": \"text\", " +
                    "\"text\": {\"content\": \"【监控】" + format + " -- Hbase-60集群监控：活动连接数量 - " + conNum + "\"}," +
                    "\"at\":{\"atMobiles\":[\"18561808500\"],\"isAtAll\":true}}";
            boolean b = false;
            try {

                if (conNum > 4000) {
                    b = DingApp.sendDingDing(textMsg);
                } else if ("0730".equals(hour)) {
                    b = DingApp.sendDingDing(textMsg);
                } else if ("1730".equals(hour)) {
                    b = DingApp.sendDingDing(textMsg);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            long end = System.currentTimeMillis();

            System.out.println("HbaseMonitorApp  -- 监控Hbase-60集群连接数量 -- "
                    + format + " -- 花费时间(ms) : " + (end - start)
                    + " -- 获取Hbase时间(ms)" + (startZK - start) + " -- 发送钉钉时间(ms)" + (end - startZK)+" -- 活动连接数量 ： "+ conNum);

            try {
                Thread.sleep(10 * 1000);
                System.out.println("监控Hbase60集群任务强制休眠10s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 功能描述：监控HBase的读写请求
     */
    public void startHbaseMonitor45() {

        // 监控测试集群的hbase
        long start = System.currentTimeMillis();
        long conNum = 0;
        List<HbaseMonitorBean> hbaseMonitorBeans = HbaseMonitorApp.startHbaseMonitor("lx-cdh-04,lx-cdh-05", "45");
        for(HbaseMonitorBean bean : hbaseMonitorBeans){
            if(bean != null && "all".equals(bean.getServerName())){
                conNum = bean.getRequestsPerSecond();
            }
        }

        if(true){


            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String format = dateTimeFormatter.format(LocalDateTime.now());
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
            String hour = sdf.format(new java.util.Date());
            long startZK = System.currentTimeMillis();
            String textMsg = "{ \"msgtype\": \"text\", " +
                    "\"text\": {\"content\": \"【监控】" + format + " -- Hbase-45集群监控：活动连接数量 - " + conNum + "\"}," +
                    "\"at\":{\"atMobiles\":[\"18561808500\"],\"isAtAll\":true}}";
            boolean b = false;
            try {

                if (conNum > 4000) {
                    b = DingApp.sendDingDing(textMsg);
                } else if ("0730".equals(hour)) {
                    b = DingApp.sendDingDing(textMsg);
                } else if ("1730".equals(hour)) {
                    b = DingApp.sendDingDing(textMsg);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            long end = System.currentTimeMillis();

            System.out.println("HbaseMonitorApp  -- 监控Hbase-45集群连接数量 -- "
                    + format + " -- 花费时间(ms) : " + (end - start)
                    + " -- 获取Hbase时间(ms)" + (startZK - start) + " -- 发送钉钉时间(ms)" + (end - startZK)+" -- 活动连接数量 ： "+ conNum);

            try {
                Thread.sleep(10 * 1000);
                System.out.println("监控Hbase45集群任务强制休眠10s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean startMonitorKafkaOpenFileCount60() {
        long start = System.currentTimeMillis();
//        NginxBean nginxBean = JsoupTools.startMonitorNginx();
        String kafka = KafkaMonitorAPP.kafkaOpenFileCountMonitor();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(LocalDateTime.now());
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String hour = sdf.format(new java.util.Date());
        long startZK = System.currentTimeMillis();
        String textMsg = "{ \"msgtype\": \"text\", " +
                "\"text\": {\"content\": \"【监控】" + format + " -- Kafka文件打开数量-60集群监控： - " + kafka + "\"}," +
                "\"at\":{\"atMobiles\":[\"18561808500\"],\"isAtAll\":true}}";
        boolean b = false;
        try {

            if ("0730".equals(hour)) {
                b = DingApp.sendDingDing(textMsg);
            } else if ("1730".equals(hour)) {
                b = DingApp.sendDingDing(textMsg);
            } else {
                if (!"".equals(kafka)) {
                    b = DingApp.sendDingDing(textMsg);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("KafkaMonitorApp  -- 监控Kafka打开文件数量 -- " + format + " -- 花费时间(ms) : " + (end - start) + " -- 获取监控Kafka时间(ms)" + (startZK - start) + " -- 发送钉钉时间(ms)" + (end - startZK));

        try {
            Thread.sleep(10 * 1000);
            System.out.println("监控startMonitorKafkaOpenFileCount60任务强制休眠10s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean startMonitorNginx() {
        long start = System.currentTimeMillis();
        NginxBean nginxBean = JsoupTools.startMonitorNginx();
        int active = nginxBean.getActiveConnections();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(LocalDateTime.now());
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String hour = sdf.format(new java.util.Date());
        long startZK = System.currentTimeMillis();
        String textMsg = "{ \"msgtype\": \"text\", " +
                "\"text\": {\"content\": \"【监控】" + format + " -- Nginx-10服务器监控：活动连接数量 - " + active + "\"}," +
                "\"at\":{\"atMobiles\":[\"18561808500\"],\"isAtAll\":true}}";
        boolean b = false;
        try {

            if (active > 300) {
                b = DingApp.sendDingDing(textMsg);
            } else if ("0730".equals(hour)) {
                b = DingApp.sendDingDing(textMsg);
            } else if ("1730".equals(hour)) {
                b = DingApp.sendDingDing(textMsg);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("KafkaMonitorApp  -- 监控Nginx连接数量 -- " + format + " -- 花费时间(ms) : " + (end - start) + " -- 获取Nginx时间(ms)" + (startZK - start) + " -- 发送钉钉时间(ms)" + (end - startZK)+" -- 活动连接数量 ： "+ active);

        try {
            Thread.sleep(10 * 1000);
            System.out.println("监控kafka任务强制休眠10s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 功能描述: 监控45集群
     * 监控kafka的消费数据是否积压
     * 如果积压超过阈值的50%，发送钉钉预警消息。
     *
     * @Param:
     * @Return:
     * @Author: XL.Gao
     * @Date: 2021/5/17 15:03
     */
    public void startKafkaConsumerLagMonitor() {

        String connectionInfo = zookeeper45Ip;

        String brokers = brokers45Ip;

        String offset = zookeeper45CustomerPath;

        String topic = kafka45Topic;
        ZookeeperTools zookeeperTools = null;

        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String hour = sdf.format(new java.util.Date());


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long start = System.currentTimeMillis();
        List<KafkaOffsetsBean> kafkaOffsetsBeans = KafkaMonitorAPP.kafkaOffsetsMonitor();
        long lag = 0;
        for (KafkaOffsetsBean k : kafkaOffsetsBeans) {
            if ("all".equals(k.getPartition())) {
                lag = k.getLag();
            }
            System.out.println(k.toString());
        }
        String format = dateTimeFormatter.format(LocalDateTime.now());
        long startZK = System.currentTimeMillis();
        String textMsg = "{ \"msgtype\": \"text\", " +
                "\"text\": {\"content\": \"【监控】" + format + " -- Kafka积压监控：用户合并程序积压数量条数-" + lag + "\"}," +
                "\"at\":{\"atMobiles\":[\"18561808500\"],\"isAtAll\":true}}";
        boolean b = false;
        try {

            if (lag > 1500) {
                b = DingApp.sendDingDing(textMsg);
            } else if ("0730".equals(hour)) {
                b = DingApp.sendDingDing(textMsg);
            } else if ("1730".equals(hour)) {
                b = DingApp.sendDingDing(textMsg);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("KafkaMonitorApp  -- 监控kafka的消费是否积压 -- " + format + " -- 花费时间(ms) : " + (end - start) + " -- 获取ZK时间(ms)" + (startZK - start) + " -- 发送钉钉时间(ms)" + (end - startZK)+" -- 当前积压 ： "+ lag);

        try {
            Thread.sleep(10 * 1000);
            System.out.println("监控kafka任务强制休眠10s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述: 监控60集群
     * <p>
     * 监控kafka集群<br>
     *
     * @Param: []
     * @Return: void
     * @Author: XL.Gao
     * @Date: 2020/11/18 15:45
     */
    public void startKafkaMonitorTask() {

        try {

            String kafkaServers60 = kafka60Brokers; //"lx-es-08:9092";
            String kafkaServerIps60 = kafka60BrokersIp; //"lx-es-08";
            String kafkaCluster60 = kafka60Cluster; //"kafka-60";
            String tableName = monitorMysqlTableName; //"monitor_kafka";


            Connection conn = MysqlClient.getConnection();
            Map<String, KafkaMonitorBean> stringKafkaMonitorBeanHashMap = MysqlClient.searchKafkaMonitorList(conn);
            boolean deleteResult = MysqlClient.deleleKafkaClusterData(conn, tableName, kafkaCluster60);

            List<KafkaMonitorBean> list = KafkaMonitorAPP.kafkaMoniter(kafkaServers60, kafkaServerIps60, kafkaCluster60);

            if (stringKafkaMonitorBeanHashMap != null && !stringKafkaMonitorBeanHashMap.isEmpty())
                list.forEach(bean -> {

                    KafkaMonitorBean beanSearch = stringKafkaMonitorBeanHashMap.get(bean.getCluster() + bean.getTopic());
                    if (beanSearch == null) {
                        System.out.println("error:" + bean.getCluster() + bean.getTopic());
                    } else {

                        long timeMs = (bean.getInsertDate() - beanSearch.getInsertDate()) / 1000;
                        long offset = bean.getAllMessageSize() - beanSearch.getAllMessageSize();

                        long perMessage = new Double(offset / timeMs).longValue();

                        bean.setPerMessageSize(perMessage);

                    }
                });

            MysqlClient.insertBatchKafkaMonitorList(conn, list);

            System.out.println("KafkaMonitorAPP  -- 监控kafkaTopic的基本信息 -- " + DateUtil.getNowDate() + "_定时任务_monitor_kafka_写入数据量：" + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 功能描述: <br>
     *
     * @Param: []
     * @Return: void
     * @Author: XL.Gao
     * @Date: 2020/11/18 15:45
     */
    public void startMonitorClusterStateTask() {

        try {

            int count = ESMonitorApp.startMonitorClusterState();

            System.out.println(DateUtil.getNowDate() + "_定时任务_monitor_admin_cluster_state_写入数据量：" + count);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 功能描述: <br>
     *
     * @Param: []
     * @Return: void
     * @Author: XL.Gao
     * @Date: 2020/11/18 15:47
     */
    @PostMapping("/startMonitorIndicesStatsTask")
    @ResponseBody
    public void startMonitorIndicesStatsTask() {

        try {

            int count = ESMonitorApp.startMonitorIndicesStats();

            System.out.println(DateUtil.getNowDate() + "_定时任务_monitor_admin_indices_stats_写入数据量：" + count);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 功能描述: <br>
     *
     * @Param: []
     * @Return: void
     * @Author: XL.Gao
     * @Date: 2020/11/18 15:47
     */
    @PostMapping("/startMonitorClusterNodesStatsTask")
    @ResponseBody
    public void startMonitorClusterNodesStatsTask() {

        try {

            int count = ESMonitorApp.startMonitorClusterNodesStats();

            System.out.println(DateUtil.getNowDate() + "_定时任务_monitor_admin_cluster_nodes_stats_写入数据量：" + count);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
