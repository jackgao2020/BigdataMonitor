package com.xl.project.bigdata.util;

import com.xl.project.bigdata.bean.HbaseMonitorBean;
import com.xl.project.bigdata.manager.HbaseClient;
import com.xl.project.bigdata.manager.MysqlClient;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.ServerLoad;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: IAEngine
 * 监控HBASE的各项指标
 * @description:
 * @author: XL.Gao
 * @create: 2021-05-19 13:23
 **/
public class HbaseMonitorApp {

    public static  List<HbaseMonitorBean> startHbaseMonitor(String zk, String clusterName){

        List<HbaseMonitorBean> list = new ArrayList<>();

        // TODO Auto-generated method stub
        try {
            // lx-cdh-04,lx-cdh-05,lx-cdh-06"
            // hicslave1,hicslave2,hicslave3
            HbaseClient.init(zk);

            Connection conn = HbaseClient.getConnection();

            Admin admin = conn.getAdmin();
            ClusterStatus clusterStatus = admin.getClusterStatus();

            int numberOfRegionsAll = 0;
            long readRequestsCountAll = 0;
            long writeRequestsCountAll = 0;
            long requestsPerSecondAll = 0;

            // 获取regionserver
            Collection<ServerName> clusterStatusServers = clusterStatus.getServers();
            for (ServerName serverName : clusterStatusServers) {

                HbaseMonitorBean hbaseMonitorBean = new HbaseMonitorBean();
                hbaseMonitorBean.setCluster(clusterName);
                hbaseMonitorBean.setTypeName("Hbase");
                hbaseMonitorBean.setServerName(serverName.getServerName());

                ServerLoad serverLoad = clusterStatus.getLoad(serverName);
                int numberOfRegions = serverLoad.getNumberOfRegions();
                hbaseMonitorBean.setNumberOfRegions(numberOfRegions);
                numberOfRegionsAll = numberOfRegionsAll + numberOfRegions;

                long readRequestsCount = serverLoad.getReadRequestsCount();
                hbaseMonitorBean.setReadRequestsCount(readRequestsCount);
                readRequestsCountAll = readRequestsCountAll + readRequestsCount;

                long writeRequestsCount = serverLoad.getWriteRequestsCount();
                hbaseMonitorBean.setWriteRequestsCount(writeRequestsCount);
                writeRequestsCountAll = writeRequestsCountAll + writeRequestsCount;

                double requestsPerSecond = serverLoad.getRequestsPerSecond();
                hbaseMonitorBean.setRequestsPerSecond(new Double(requestsPerSecond).longValue());
                requestsPerSecondAll = requestsPerSecondAll + new Double(requestsPerSecond).longValue();
                System.out.println(hbaseMonitorBean.toString());

                hbaseMonitorBean.setInsertDate(System.currentTimeMillis());
                list.add(hbaseMonitorBean);

            }

            HbaseMonitorBean hbaseMonitorBean = new HbaseMonitorBean();
            hbaseMonitorBean.setCluster(clusterName);
            hbaseMonitorBean.setTypeName("Hbase");
            hbaseMonitorBean.setServerName("all");
            hbaseMonitorBean.setNumberOfRegions(numberOfRegionsAll);
            hbaseMonitorBean.setRequestsPerSecond(requestsPerSecondAll);
            hbaseMonitorBean.setWriteRequestsCount(writeRequestsCountAll);
            hbaseMonitorBean.setReadRequestsCount(readRequestsCountAll);
            hbaseMonitorBean.setInsertDate(System.currentTimeMillis());
            System.out.println(hbaseMonitorBean.toString());
            list.add(hbaseMonitorBean);

            if(list != null && list.size() > 0){
                java.sql.Connection connMysql = MysqlClient.getConnection();
                MysqlClient.insertBatchHbaseMonitorList(connMysql, list);
//                MysqlClient.
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {

        startHbaseMonitor("lx-cs-04,lx-cs-05","86");

    }

}
