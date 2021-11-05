package com.xl.project.bigdata.manager;

import com.xl.project.bigdata.bean.*;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class MysqlClient {

//	mysql.url=jdbc:mysql://10.155.6.102:3306/phone?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE
//	mysql.driver=com.mysql.jdbc.Driver
//	mysql.username=root
//	mysql.password=root

    private static String driver = "com.mysql.jdbc.Driver";

    private static String url = "jdbc:mysql://10.155.20.61:3306/monitor?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE";

    private static String username = "root";

    private static String password = "root";

    private static Connection conn;

    public MysqlClient() {

    }

    public static void init() {
        try {

//			driver = Config.MysqlDriver;
//			url = Config.MysqlUrl;
//			username = Config.MysqlUserName;
//			password = Config.MysqlPassword;

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection() {

        try {
            if (conn == null || conn.isClosed()) {
                try {
                    conn = (Connection) DriverManager.getConnection(url, username, password);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;
    }

    public static boolean deleleData(Connection con, String tableName){

        String sql = "delete from "+ tableName;

        boolean result = false;

        Statement stmt= null;//创建Statement对象
        try {
            stmt = conn.createStatement();


            int count = stmt.executeUpdate(sql);//执行sql语句

            if(count > 0){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static boolean deleleKafkaClusterData(Connection con, String tableName, String cluster){

        String sql = "delete from "+ tableName + " where cluster = '"+ cluster + "'";

        boolean result = false;

        Statement stmt= null;//创建Statement对象
        try {
            stmt = conn.createStatement();


            int count = stmt.executeUpdate(sql);//执行sql语句

            if(count > 0){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static void insertBatchMonitorNodesStatsList(Connection con, List<MonitorNodesStatsBean> list){



        long start = System.currentTimeMillis();


        StringBuffer sb = new StringBuffer("insert into monitor_admin_cluster_nodes_stats ");

        // 集群信息 60 45 86
        String clusterInfo = "";
        sb.append("(cluster_info, cluster_name, nodes_total, nodes_ip, nodes_roles, monitor_type, indices_doc_count, ");
        sb.append(" indices_store_size, indices_index_total, indices_index_current, indices_get_total, indices_get_current, indices_query_total, ");
        sb.append(" indices_query_current, indices_scroll_total, indices_scroll_current, indices_merges_current, indices_mergers_docs, indices_refresh_total, ");
        sb.append(" indices_flush_total, indices_query_cache_size, indices_query_cache_total, indices_query_cache_hit_count, indices_query_cache_miss_count, indices_segments_count, ");
        sb.append(" indices_segments_memory_size, indices_segments_memory_terms_size, indices_translog_size, os_cpu_percent, os_mem_total, os_mem_free, ");
        sb.append(" os_mem_used, jvm_heap_used, jvm_heap_max, threads_count, threads_peak_count, gc_young_collection_count, ");
        sb.append(" gc_old_collection_count, gc_classes_total_loaded_count, fs_total, fs_free, transport_server_open, http_current_open, ");
        sb.append(" script_compilations, insert_time) ");

        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
        sb.append("  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
        sb.append("  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
        sb.append("  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
        sb.append("  ?, ?, ?, ?, ?)");

        System.out.println(sb.toString());

        PreparedStatement psts = null;
        try {

            psts = con.prepareStatement(sb.toString());
            con.setAutoCommit(false);

            for(MonitorNodesStatsBean mcsb : list){

                // cluster_info, cluster_name, nodes_total, nodes_ip, nodes_roles, monitorType, indicesDocCount,
                psts.setString(1,mcsb.getClusterInfo());
                psts.setString(2,mcsb.getClusterName());
                psts.setInt(3, mcsb.getNodesTotal());
                psts.setString(4,mcsb.getNodesIP());
                psts.setString(5,mcsb.getNodesRoles());
                psts.setString(6,mcsb.getMonitorType());
                psts.setLong(7,mcsb.getIndicesDocCount());

                // indicesStoreSize, indicesIndexTotal, indicesIndexCurrent, indices_get_total, indices_get_current, indices_query_total,
                psts.setLong(8,mcsb.getIndicesStoreSize());
                psts.setLong(9,mcsb.getIndicesIndexTotal());
                psts.setLong(10,mcsb.getIndicesIndexCurrent());
                psts.setLong(11,mcsb.getIndicesGetTotal());
                psts.setLong(12,mcsb.getIndicesGetCurrent());
                psts.setLong(13,mcsb.getIndicesQueryTotal());


                // indices_query_current, indices_scroll_total, indices_scroll_current, indices_merges_current, indices_mergers_docs, indicesr_refresh_total, ");
                psts.setLong(14,mcsb.getIndicesQueryCurrent());
                psts.setLong(15,mcsb.getIndicesScrollTotal());
                psts.setLong(16,mcsb.getIndicesScrollCurrent());
                psts.setLong(17,mcsb.getIndicesMergesCurrent());
                psts.setLong(18,mcsb.getIndicesMergersDocs());
                psts.setLong(19,mcsb.getIndicesRefreshTotal());
                // indices_flush_total, indices_query_cache_size, indices_query_cache_total, indices_query_cache_hit_count, indices_query_cache_miss_count, indices_segments_count, ");
                psts.setLong(20,mcsb.getIndicesFlushTotal());
                psts.setLong(21,mcsb.getIndicesQueryCacheSize());
                psts.setLong(22,mcsb.getIndicesQueryCacheTotal());
                psts.setLong(23,mcsb.getIndicesQueryCacheHitCount());
                psts.setLong(24,mcsb.getIndicesQueryCacheMissCount());
                psts.setLong(25,mcsb.getIndicesSegmentsCount());
                // indices_segments_memory_size, indices_segments_memory_terms_size, indicest_translog_size, os_cpu_percent, os_mem_total, os_mem_free, ");
                psts.setLong(26,mcsb.getIndicesSegmentsMemorySize());
                psts.setLong(27,mcsb.getIndicesSegmentsMemoryTermsSize());
                psts.setLong(28,mcsb.getIndicesTranslogSize());
                psts.setString(29,mcsb.getOsCpuPercent());
                psts.setLong(30,mcsb.getOsMemTotal());
                psts.setLong(31,mcsb.getOsMemFree());
                // os_mem_used, jvm_heap_used, jvm_heap_max, threads_count, threads_peak_count, gc_young_collection_count, ");
                psts.setLong(32,mcsb.getOsMemUsed());
                psts.setLong(33,mcsb.getJvmHeapUsed());
                psts.setLong(34,mcsb.getJvmHeapMax());
                psts.setLong(35,mcsb.getThreadsCount());
                psts.setLong(36,mcsb.getThreadsPeakCount());
                psts.setLong(37,mcsb.getGcYoungCollectionCount());
                // gc_old_collection_count, gc_classes_total_loaded_count, fs_total, fs_free, transport_server_open, http_current_open, ");
                psts.setLong(38,mcsb.getGcOldCollectionCount());
                psts.setLong(39,mcsb.getGcClassesTotalLoadedCount());
                psts.setLong(40,mcsb.getFsTotal());
                psts.setLong(41,mcsb.getFsFree());
                psts.setLong(42,mcsb.getTransportServerOpen());
                psts.setLong(43,mcsb.getHttpCurrentOpen());
                // script_compilations, insert_time) ");
                psts.setLong(44,mcsb.getScriptCompilations());
                psts.setLong(45,mcsb.getInsertTime());

                psts.addBatch();

            }

            try {
                psts.executeBatch();//执行给定的SQL语句，该语句可能返回多个结果
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psts != null) {
                    psts.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("MysqlClient - 向61mysql-monitor_admin_cluster_nodes_stats表写入数据量 ： " + list.size() + "  花费的时间：" + (end - start));
    }

    public static Map<String, KafkaMonitorBean> searchKafkaMonitorList(Connection con){

        Map<String, KafkaMonitorBean> stringKafkaMonitorBeanHashMap = new HashMap<String, KafkaMonitorBean>();
//        List<KafkaMonitorBean> kafkaMonitorBeanArrayList = new ArrayList<KafkaMonitorBean>();
        String sql = "Select cluster, topic, partitionsSize, brokersSize, perMessageSize, allMessageSize, curMessageSize, latestOffset, \n" +
                "maxLatestOffset, avgLatestOffset, minLatestOffset, earliestOffset, maxEarliestOffset, avgEarliestOffset,\n" +
                "minEarliestOffset,insertDate from monitor_kafka";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                KafkaMonitorBean photoHeartBean = new KafkaMonitorBean();
                photoHeartBean.setCluster(rs.getString("cluster"));
                photoHeartBean.setTopic(rs.getString("topic"));
                photoHeartBean.setPartitionsSize(rs.getInt("partitionsSize"));
                photoHeartBean.setBrokersSize(rs.getInt("brokersSize"));
                photoHeartBean.setPerMessageSize(rs.getLong("perMessageSize"));
                photoHeartBean.setAllMessageSize(rs.getLong("allMessageSize"));
                photoHeartBean.setCurMessageSize(rs.getLong("curMessageSize"));
                photoHeartBean.setLatestOffset(rs.getString("latestOffset"));
                photoHeartBean.setMaxLatestOffset(rs.getLong("maxLatestOffset"));
                photoHeartBean.setAvgLatestOffset(rs.getLong("avgLatestOffset"));
                photoHeartBean.setMinLatestOffset(rs.getLong("minLatestOffset"));
                photoHeartBean.setEarliestOffset(rs.getString("earliestOffset"));
                photoHeartBean.setMaxEarliestOffset(rs.getLong("maxEarliestOffset"));
                photoHeartBean.setAvgEarliestOffset(rs.getLong("avgEarliestOffset"));
                photoHeartBean.setMinEarliestOffset(rs.getLong("minEarliestOffset"));
                photoHeartBean.setInsertDate(rs.getLong("insertDate"));
//                kafkaMonitorBeanArrayList.add(photoHeartBean);
                stringKafkaMonitorBeanHashMap.put(rs.getString("cluster")+rs.getString("topic"), photoHeartBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringKafkaMonitorBeanHashMap;
    }

    public static void insertBatchHbaseMonitorList(Connection con, List<HbaseMonitorBean> list) {

        long start = System.currentTimeMillis();


        StringBuffer sb = new StringBuffer("insert into monitor_hbase ");

        sb.append("(cluster, typeName, serverName, numberOfRegions, readRequestsCount, writeRequestsCount, requestsPerSecond,  insertDate)");

        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

//        System.out.println("KafkaMonitorAPP -- " + sb.toString());

        PreparedStatement psts = null;
        try {

            psts = con.prepareStatement(sb.toString());
            con.setAutoCommit(false);

            for(HbaseMonitorBean mcsb : list){

                psts.setString(1,mcsb.getCluster());

                psts.setString(2,mcsb.getTypeName());

                psts.setString(3,mcsb.getServerName());

                psts.setInt(4,mcsb.getNumberOfRegions());

                psts.setLong(5,mcsb.getReadRequestsCount());

                psts.setLong(6,mcsb.getWriteRequestsCount());

                psts.setLong(7,mcsb.getRequestsPerSecond());

                psts.setLong(8,mcsb.getInsertDate());

                psts.addBatch();

            }

            try {
                psts.executeBatch();//执行给定的SQL语句，该语句可能返回多个结果
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psts != null) {
                    psts.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("MysqlClient - 向mysql-phone表写入数据量 ： " + list.size() + "  花费的时间：" + (end - start));
    }


    public static void insertBatchKafkaMonitorList(Connection con, List<KafkaMonitorBean> list) {

        long start = System.currentTimeMillis();


        StringBuffer sb = new StringBuffer("insert into monitor_kafka ");

        sb.append("(cluster, topic, partitionsSize, brokersSize, perMessageSize, allMessageSize, curMessageSize, latestOffset, maxLatestOffset," +
                " avgLatestOffset, minLatestOffset, earliestOffset, maxEarliestOffset, avgEarliestOffset, minEarliestOffset, insertDate)");

        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

//        System.out.println("KafkaMonitorAPP -- " + sb.toString());

        PreparedStatement psts = null;
        try {

            psts = con.prepareStatement(sb.toString());
            con.setAutoCommit(false);

            for(KafkaMonitorBean mcsb : list){

                // clusterInfo, clusterName, providedName, creationDate, uuid, numberOfShards, numberOfReplicas, refreshInterval, insertTime
                psts.setString(1,mcsb.getCluster());

                psts.setString(2,mcsb.getTopic());

                psts.setInt(3,mcsb.getPartitionsSize());

                psts.setInt(4,mcsb.getBrokersSize());

                psts.setLong(5,mcsb.getPerMessageSize());

                psts.setLong(6,mcsb.getAllMessageSize());

                psts.setLong(7,mcsb.getCurMessageSize());

                psts.setString(8,mcsb.getLatestOffset());

                psts.setLong(9,mcsb.getMaxLatestOffset());

                psts.setLong(10,mcsb.getAvgLatestOffset());

                psts.setLong(11,mcsb.getMinLatestOffset());

                psts.setString(12,mcsb.getEarliestOffset());

                psts.setLong(13,mcsb.getMaxEarliestOffset());

                psts.setLong(14,mcsb.getAvgEarliestOffset());

                psts.setLong(15,mcsb.getMinEarliestOffset());

                psts.setLong(16,mcsb.getInsertDate());

                psts.addBatch();

            }

            try {
                psts.executeBatch();//执行给定的SQL语句，该语句可能返回多个结果
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psts != null) {
                    psts.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("MysqlClient - 向mysql-phone表写入数据量 ： " + list.size() + "  花费的时间：" + (end - start));
    }

    public static void insertBatchMonitorStateList(Connection con, List<MonitorClusterStateBean> list) {

        long start = System.currentTimeMillis();


        StringBuffer sb = new StringBuffer("insert into monitor_admin_cluster_state ");

        sb.append("(cluster_info, cluster_name, provided_name, creation_date, uuid, number_of_shards, number_of_replicas, refresh_interval, insert_time)");

        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        System.out.println(sb.toString());

        PreparedStatement psts = null;
        try {

            psts = con.prepareStatement(sb.toString());
            con.setAutoCommit(false);

            for(MonitorClusterStateBean mcsb : list){

                // clusterInfo, clusterName, providedName, creationDate, uuid, numberOfShards, numberOfReplicas, refreshInterval, insertTime
                psts.setString(1,mcsb.getClusterInfo());

                psts.setString(2,mcsb.getClusterName());

                psts.setString(3,mcsb.getProvidedName());

                psts.setString(4,mcsb.getCreationDate());

                psts.setString(5,mcsb.getUuid());

                psts.setString(6,mcsb.getNumberOfShards());

                psts.setString(7,mcsb.getNumberOfReplicas());

                psts.setString(8,mcsb.getRefreshInterval());

                psts.setLong(9,mcsb.getInsertTime());

                psts.addBatch();

            }

            try {
                psts.executeBatch();//执行给定的SQL语句，该语句可能返回多个结果
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psts != null) {
                    psts.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("MysqlClient - 向mysql-phone表写入数据量 ： " + list.size() + "  花费的时间：" + (end - start));
    }

    public static void insertBatchMonitorStatsMapping(Connection con, List<MonitorStatsBean> list) {

        long start = System.currentTimeMillis();

        String sql = "INSERT INTO monitor_admin_indices_stats (phone, date, content) VALUES (?, ?, ?)";

        StringBuffer sb = new StringBuffer("INSERT INTO monitor_admin_indices_stats ");

        sb.append("(clusterInfo, monitorType, indexName, indexPrimaries, docCount, storeSize, indexTotal, indexCurrent,");
        sb.append(" getTotal, getCurrent, queryTotal, queryCurrent, scrollTotal, scrollCurrent, mergesCurrent, mergersDocs, ");
        sb.append(" refreshTotal, flushTotal, queryCacheSize, queryCacheTotal, queryCacheHitCount, queryCacheMissCount, ");
        sb.append(" segmentsCount, segmentsMemorySize, segmentsMemoryTermsSize, translogSize, insertTime)");

        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?,");
        sb.append(" ?, ?, ?, ?, ?, ?, ?, ?,");
        sb.append(" ?, ?, ?, ?, ?, ?,");
        sb.append(" ?, ?, ?, ?, ?)");

        System.out.println(sb.toString());

        PreparedStatement psts = null;
        try {

            int count = 0;
            psts = con.prepareStatement(sb.toString());
            con.setAutoCommit(false);

            for(MonitorStatsBean msb : list){

                // clusterInfo, monitorType, indexName, indexPrimaries, docCount, storeSize, indexTotal, indexCurrent,
                psts.setString(1,msb.getClusterInfo());

                psts.setString(2,msb.getMonitorType());

                psts.setString(3,msb.getIndexName());

                psts.setString(4,msb.getIndexPrimaries());

                psts.setLong(5,msb.getDocCount());

                psts.setLong(6,msb.getStoreSize());

                psts.setLong(7,msb.getIndexTotal());

                psts.setLong(8,msb.getIndexCurrent());

                // getTotal, getCurrent, queryTotal, queryCurrent, scrollTotal, scrollCurrent, mergesCurrent, mergersDocs

                psts.setLong(9,msb.getGetTotal());

                psts.setLong(10,msb.getGetCurrent());

                psts.setLong(11,msb.getQueryTotal());

                psts.setLong(12,msb.getQueryCurrent());

                psts.setLong(13,msb.getScrollTotal());

                psts.setLong(14,msb.getScrollCurrent());

                psts.setLong(15,msb.getMergesCurrent());

                psts.setLong(16,msb.getMergersDocs());

                // refreshTotal, flushTotal, queryCacheSize, queryCacheTotal, queryCacheHitCount, queryCacheMissCount
                psts.setLong(17,msb.getRefreshTotal());

                psts.setLong(18,msb.getFlushTotal());

                psts.setLong(19,msb.getQueryCacheSize());

                psts.setLong(20,msb.getQueryCacheTotal());

                psts.setLong(21,msb.getQueryCacheHitCount());

                psts.setLong(22,msb.getQueryCacheMissCount());

                // segmentsCount, segmentsMemorySize, segmentsMemoryTermsSize, translogSize, insertTime
                psts.setLong(23,msb.getSegmentsCount());

                psts.setLong(24,msb.getSegmentsMemorySize());

                psts.setLong(25,msb.getSegmentsMemoryTermsSize());

                psts.setLong(26,msb.getTranslogSize());

                psts.setLong(27,msb.getInsertTime());

                psts.addBatch();

            }

            try {
                psts.executeBatch();//执行给定的SQL语句，该语句可能返回多个结果
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psts != null) {
                    psts.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("MysqlClient - 向mysql-phone表写入数据量 ： " + list.size() + "  花费的时间：" + (end - start));
    }



    public static void insertCount(Connection con, int count, String date, String desc) {

        long start = System.currentTimeMillis();

        String sql = "INSERT INTO phone_count (date, count, remark) VALUES (?, ?, ?)";

        PreparedStatement psts = null;
        try {
            psts = con.prepareStatement(sql);
            psts.setString(1, date);
            psts.setInt(2, count);
            psts.setString(3, desc);
            psts.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psts != null) {
                    psts.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("MysqlClient - 向mysql-phone表写入数据量 ： date :" + date + "  count : " + count + "  花费的时间：" + (end - start));
    }

    public static void main(String[] args) throws SQLException {

        // TODO Auto-generated method stub

//		Config.MysqlDriver = "com.mysql.jdbc.Driver";
//		Config.MysqlUrl = "jdbc:mysql://10.155.6.102:3306/phone?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE";
//		Config.MysqlUserName = "root";
//		Config.MysqlPassword = "root";

        // MysqlClient - 向mysql-phone表写入数据量 ： 1607806 花费的时间：642456
        // MysqlClient - 10000 向mysql-phone表写入数据量 ： 1488851 花费的时间：507285
        // MysqlClient - 15000 向mysql-phone表写入数据量 ： 1488851 花费的时间：508987
        // MysqlClient - 1000 向mysql-phone表写入数据量 ： 1488851 花费的时间：510364
        // MysqlClient - 向mysql-phone表写入数据量 ： 1488851 花费的时间：511792
        // MysqlClient - 向mysql-phone表写入数据量 ： 1305774 花费的时间：461161
        // MysqlClient - 向mysql-phone表写入数据量 ： 1607806 花费的时间：643308

//		List<PhoneBean> list = CsvUtil.readCSV("D:\\z", "D:\\A");

        init();
        Connection con = MysqlClient.getConnection();
//		insertBatch(con, list);

    }

}
