package com.xl.project.bigdata.util;/**
 * @program: IAEngine
 * @description:
 * @author: Mr.Wang
 * @create: 2020-11-10 14:29
 **/

import com.alibaba.fastjson.JSON;
import com.xl.project.bigdata.bean.MonitorClusterStateBean;
import com.xl.project.bigdata.bean.MonitorNodesStatsBean;
import com.xl.project.bigdata.bean.MonitorStatsBean;
import com.xl.project.bigdata.manager.ES60Client;
import com.xl.project.bigdata.manager.MysqlClient;
import org.elasticsearch.action.admin.cluster.node.stats.NodeStats;
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsRequest;
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsResponse;
import org.elasticsearch.action.admin.cluster.state.ClusterStateRequest;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.stats.CommonStats;
import org.elasticsearch.action.admin.indices.stats.IndexStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: IAEngine
 * @description:
 * @author: XL.Gao
 * @create: 2020-11-10 14:29
 **/
public class ESMonitorApp {

    private static TransportClient client86 = null;

    private static TransportClient client60 = null;

    private static String clusterInfo = "60";

//    static {
//        client60 = ES60Client.getClient();
//    }

    public static void main(String[] args) {

//        client86 = ES86Client.getClient();

        client60 = ES60Client.getClient();

        startMonitorClusterNodesStats();

        client60.close();
//        client86.close();
        System.exit(1);


        // http://10.155.20.60:9200/_nodes/stats
//        NodesStatsRequest nodesStatsRequest = new NodesStatsRequest();
//        nodesStatsRequest.indices();
//        nodesStatsRequest.fs(true);
//        nodesStatsRequest.http(true);
//        nodesStatsRequest.jvm(true);
//        nodesStatsRequest.transport(true);
//        NodesStatsResponse nodesStatsResponse = client60.admin().cluster().nodesStats(nodesStatsRequest).actionGet();
//        System.out.println(nodesStatsResponse.toString());

        // http://10.155.20.60:9200/_cluster/stats?human&pretty
//        client60.admin().cluster().clusterStats()

//        nodesStatsResponse.

        // http://10.155.20.60:9200/_cat/health?v
//                client60.admin().cluster().health()


        // http://10.155.20.60:9200/_cluster/stats?human&pretty
//        ClusterStatsResponse clusterStatsResponse =  client.admin().cluster().clusterStats(new ClusterStatsRequest()).actionGet();
//        System.out.println(clusterStatsResponse.toString());

        // http://10.155.20.60:9200/_stats
//        IndicesStatsResponse indicesStatsResponse = client.admin().indices().stats(new IndicesStatsRequest()).actionGet();
//        System.out.println(indicesStatsResponse.toString());

        int i = 1;
        while (i < 100) {

            System.out.println("running-****************");

            startMonitorIndicesStats();

            i++;

            try {
                Thread.sleep(5 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("running-执行次数%s", i);

            System.out.println("ending-****************");
        }
//        System.out.println(indicesStatsResponse.getTotal().toString());


        client60.close();
        client86.close();
    }

    /*
     * 功能描述: <br>
     * 
     * @Param: 
     * @Return:
     * @Author: XL.Gao
     * @Date: 2020/11/18 11:06
     */
    public static int startMonitorClusterState(){

        List<MonitorClusterStateBean> list = new ArrayList<>();

        ClusterStateRequest clusterStateRequest = new ClusterStateRequest();
        clusterStateRequest.metaData(true);
        clusterStateRequest.customs(false);
        clusterStateRequest.nodes(false);

        ClusterStateResponse clusterStateResponse = ES60Client.getClient().admin().cluster().state(clusterStateRequest).actionGet();

        String clusterName = clusterStateResponse.getClusterName().value();

        Iterator<IndexMetaData> iterable= clusterStateResponse.getState().getMetaData().iterator();
        while(iterable.hasNext()){

            MonitorClusterStateBean monitorClusterStateBean = new MonitorClusterStateBean();

            IndexMetaData indexMetaData = iterable.next();

            Map<String, String> settings =indexMetaData.getSettings().getAsMap();
            String refresh_interval = settings.get("index.refresh_interval");
            String number_of_shards = settings.get("index.number_of_shards");
            String number_of_replicas = settings.get("index.number_of_replicas");
            String uuid = settings.get("index.uuid");
            String creation_date = settings.get("index.creation_date");

            if(creation_date != null){
                long crDate = Long.parseLong(creation_date);
                Date date = new Date(crDate);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String dateYY = sdf.format(date);
                monitorClusterStateBean.setCreationDate(dateYY);

            }

            String provided_name = settings.get("index.provided_name");

            monitorClusterStateBean.setClusterInfo(clusterInfo);
            monitorClusterStateBean.setClusterName(clusterName);
            monitorClusterStateBean.setRefreshInterval(refresh_interval);
            monitorClusterStateBean.setNumberOfShards(number_of_shards);
            monitorClusterStateBean.setNumberOfReplicas(number_of_replicas);
            monitorClusterStateBean.setUuid(uuid);
            monitorClusterStateBean.setProvidedName(provided_name);

            // 查询时间
            String insertTimeStr = DateUtil.getNowDate();
            long insertTime = Long.parseLong(insertTimeStr);
            monitorClusterStateBean.setInsertTime(insertTime);

            list.add(monitorClusterStateBean);
        }

        Connection conn = MysqlClient.getConnection();
        boolean deleteResult = MysqlClient.deleleData(conn, "monitor_admin_cluster_state");

        MysqlClient.insertBatchMonitorStateList(conn, list);

        System.out.println("monitor_admin_cluster_state写入数据量："+list.size());

        return list.size();
    }

    public static MonitorNodesStatsBean getMonitorNodesStatsBean(NodeStats nodeStats){

        MonitorNodesStatsBean monitorNodesStatsBean = new MonitorNodesStatsBean();

        // 集群的节点名称
        String nodesName = nodeStats.getNode().getName();
        monitorNodesStatsBean.setNodesName(nodesName);

        // 集群的节点IP
        String nodesIP = nodeStats.getNode().getAddress().getAddress();
        monitorNodesStatsBean.setNodesIP(nodesIP);

        // 集群的节点角色 master,data,ingest
        String nodesRoles = nodeStats.getNode().getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.joining(","));
        monitorNodesStatsBean.setNodesRoles(nodesRoles);

        // 索引量 docs count
        long indicesDocCount = nodeStats.getIndices().getDocs().getCount();
        monitorNodesStatsBean.setIndicesDocCount(indicesDocCount);

        // 索引大小 G store size_in_bytes
        long indicesStoreSize = nodeStats.getIndices().getStore().getSizeInBytes();
        monitorNodesStatsBean.setIndicesStoreSize(indicesStoreSize);

        // 索引写入总量 indexing index_total
        long indicesIndexTotal = nodeStats.getIndices().getIndexing().getTotal().getIndexCount();
        monitorNodesStatsBean.setIndicesIndexTotal(indicesIndexTotal);

        // 索引写入并发量 indexing index_current
        long indicesIndexCurrent = nodeStats.getIndices().getIndexing().getTotal().getIndexCurrent();
        monitorNodesStatsBean.setIndicesIndexCurrent(indicesIndexCurrent);

        // 索引Get请求量 get total
        long indicesGetTotal = nodeStats.getIndices().getGet().getCount();
        monitorNodesStatsBean.setIndicesGetTotal(indicesGetTotal);

        // 索引Get请求并发量 get current
        long indicesGetCurrent = nodeStats.getIndices().getGet().current();
        monitorNodesStatsBean.setIndicesGetCurrent(indicesGetCurrent);

        // 索引Search请求量 search query_total
        long indicesQueryTotal = nodeStats.getIndices().getSearch().getTotal().getQueryCount();
        monitorNodesStatsBean.setIndicesQueryTotal(indicesQueryTotal);

        // 索引Search并发请求量 search query_current
        long indicesQueryCurrent = nodeStats.getIndices().getSearch().getTotal().getQueryCurrent();
        monitorNodesStatsBean.setIndicesQueryCurrent(indicesQueryCurrent);

        // 索引Search Scroll滚动请求量 scroll_total
        long indicesScrollTotal = nodeStats.getIndices().getSearch().getTotal().getScrollCount();
        monitorNodesStatsBean.setIndicesScrollTotal(indicesScrollTotal);

        // 索引Search Scroll滚动并发请求量 scroll_current
        long indicesScrollCurrent = nodeStats.getIndices().getSearch().getTotal().getScrollCurrent();
        monitorNodesStatsBean.setIndicesScrollCurrent(indicesScrollCurrent);

        // 索引merges并发请求量 merges current
        long indicesMergesCurrent = nodeStats.getIndices().getMerge().getCurrent();
        monitorNodesStatsBean.setIndicesMergesCurrent(indicesMergesCurrent);

        // 索引merges并发执行doc量 merges current_docs
        long indicesMergersDocs = nodeStats.getIndices().getMerge().getCurrentNumDocs();
        monitorNodesStatsBean.setIndicesMergersDocs(indicesMergersDocs);

        // 索引refresh刷新量 total
        long indicesrRefreshTotal = nodeStats.getIndices().getRefresh().getTotal();
        monitorNodesStatsBean.setIndicesRefreshTotal(indicesrRefreshTotal);

        // 索引flush刷新量  total
        long indicesFlushTotal = nodeStats.getIndices().getFlush().getTotal();
        monitorNodesStatsBean.setIndicesFlushTotal(indicesFlushTotal);

        // 索引query_cache memory_size_in_bytes
        long indicesQueryCacheSize = nodeStats.getIndices().getQueryCache().getMemorySizeInBytes();
        monitorNodesStatsBean.setIndicesQueryCacheSize(indicesQueryCacheSize);

        // 索引query_cache量 total_count
        long indicesQueryCacheTotal = nodeStats.getIndices().getQueryCache().getTotalCount();
        monitorNodesStatsBean.setIndicesQueryCacheTotal(indicesQueryCacheTotal);

        // 索引query_cache命中量 hit_count
        long indicesQueryCacheHitCount = nodeStats.getIndices().getQueryCache().getHitCount();
        monitorNodesStatsBean.setIndicesQueryCacheHitCount(indicesQueryCacheHitCount);

        // 索引query_cache未命中量 miss_count
        long indicesQueryCacheMissCount = nodeStats.getIndices().getQueryCache().getMissCount();
        monitorNodesStatsBean.setIndicesQueryCacheMissCount(indicesQueryCacheMissCount);

        // 索引segments段总量 count
        long indicesSegmentsCount = nodeStats.getIndices().getSegments().getCount();
        monitorNodesStatsBean.setIndicesSegmentsCount(indicesSegmentsCount);

        // 索引segments段占用内存的量 memory_in_bytes
        long indicesSegmentsMemorySize = nodeStats.getIndices().getSegments().getMemoryInBytes();
        monitorNodesStatsBean.setIndicesSegmentsMemorySize(indicesSegmentsMemorySize);

        // 索引segments段的词典占用内存的量 terms_memory_in_bytes
        long indicesSegmentsMemoryTermsSize = nodeStats.getIndices().getSegments().getTermsMemoryInBytes();
        monitorNodesStatsBean.setIndicesSegmentsMemoryTermsSize(indicesSegmentsMemoryTermsSize);

        // 索引translog事务日志的量 size_in_bytes
        long indicestTranslogSize = 0;
        monitorNodesStatsBean.setIndicesTranslogSize(indicestTranslogSize);

        // 节点CPU使用率
        String osCpuPercent = nodeStats.getOs().getCpu().getPercent() + "";
        monitorNodesStatsBean.setOsCpuPercent(osCpuPercent);

        // 节点Mem总量
        long osMemTotal = nodeStats.getOs().getMem().getTotal().getBytes();
        monitorNodesStatsBean.setOsMemTotal(osMemTotal);

        // 节点Mem剩余量
        long osMemFree = nodeStats.getOs().getMem().getFree().getBytes();
        monitorNodesStatsBean.setOsMemFree(osMemFree);

        // 节点Mem使用量
        long osMemUsed = nodeStats.getOs().getMem().getUsed().getBytes();
        monitorNodesStatsBean.setOsMemUsed(osMemUsed);

        // 节点JVM Heap使用量
        long jvmHeapUsed = nodeStats.getJvm().getMem().getHeapUsed().getBytes();
        monitorNodesStatsBean.setJvmHeapUsed(jvmHeapUsed);

        // 节点JVM Heap总量
        long jvmHeapMax = nodeStats.getJvm().getMem().getHeapMax().getBytes();
        monitorNodesStatsBean.setJvmHeapMax(jvmHeapMax);

        // 节点Threads量
        long threadsCount = nodeStats.getJvm().getThreads().getCount();
        monitorNodesStatsBean.setThreadsCount(threadsCount);

        // 节点Threads峰值量
        long threadsPeakCount = nodeStats.getJvm().getThreads().getPeakCount();
        monitorNodesStatsBean.setThreadsPeakCount(threadsPeakCount);


        nodeStats.getJvm().getGc().forEach(garbageCollector -> {
            String name = garbageCollector.getName();

            if("young".equals(name)){
                // 节点GC年轻代收集数
                long gcYoungCollectionCount = garbageCollector.getCollectionCount();

                monitorNodesStatsBean.setGcYoungCollectionCount(gcYoungCollectionCount);
            }

            if("old".equals(name)){
                // 节点GC老年代收集数
                long gcOldCollectionCount = garbageCollector.getCollectionCount();

                monitorNodesStatsBean.setGcOldCollectionCount(gcOldCollectionCount);
            }
        });

        // 节点GC加载的类的当前数量
        long gcClassesTotalLoadedCount = 0;
        monitorNodesStatsBean.setGcClassesTotalLoadedCount(gcClassesTotalLoadedCount);

        // 节点FS磁盘总量
        long fsTotal = nodeStats.getFs().getTotal().getTotal().getBytes();
        monitorNodesStatsBean.setFsTotal(fsTotal);

        // 节点FS磁盘剩余量
        long fsFree = nodeStats.getFs().getTotal().getFree().getBytes();
        monitorNodesStatsBean.setFsFree(fsFree);

        // 节点Transport连接数量
        long transportServerOpen = nodeStats.getTransport().getServerOpen();
        monitorNodesStatsBean.setTransportServerOpen(transportServerOpen);

        // 节点Http连接数量
        long httpCurrentOpen = nodeStats.getHttp().getServerOpen();
        monitorNodesStatsBean.setHttpCurrentOpen(httpCurrentOpen);

        // 节点Script脚本完成量
        long  scriptCompilations = nodeStats.getScriptStats().getCompilations();
        monitorNodesStatsBean.setScriptCompilations(scriptCompilations);

        // 查询时间
        String insertTimeStr = DateUtil.getNowDate();
        long insertTime = Long.parseLong(insertTimeStr);
        monitorNodesStatsBean.setInsertTime(insertTime);

        return monitorNodesStatsBean;
    }

    /**
     * 功能描述: <br>
     * http://10.155.20.60:9200/_nodes/stats
     *
     * @Param: []
     * @Return: boolean
     * @Author: XL.Gao
     * @Date: 2020/11/16 15:57
     */
    public static int startMonitorClusterNodesStats() {

        client60 = ES60Client.getClient();
        // http://10.155.20.60:9200/_nodes/stats
        NodesStatsRequest nodesStatsRequest = new NodesStatsRequest();
        nodesStatsRequest.fs(true);
        nodesStatsRequest.http(true);
        nodesStatsRequest.jvm(true);
        nodesStatsRequest.transport(true);
        nodesStatsRequest.os(true);
        nodesStatsRequest.script(true);
        NodesStatsResponse nodesStatsResponse = ES60Client.getClient().admin().cluster().nodesStats(nodesStatsRequest).actionGet();

        List<NodeStats> nodeStatsList = nodesStatsResponse.getNodes();

        if(nodeStatsList == null){
            return 0;
        }

        List<MonitorNodesStatsBean> monitorNodesStatsBeanList = new ArrayList<>();
        for(NodeStats nodeStats : nodeStatsList){

            MonitorNodesStatsBean monitorNodesStatsBean =  getMonitorNodesStatsBean(nodeStats);

            // 集群信息 60 45 86
            monitorNodesStatsBean.setClusterInfo(clusterInfo);

            // 集群名称 lse1
            String clusterName = nodesStatsResponse.getClusterName().value();
            monitorNodesStatsBean.setClusterName(clusterName);

            monitorNodesStatsBean.setMonitorType("/_nodes/stats");

            // 集群的节点数量
            int nodesTotal = nodeStatsList.size();
            monitorNodesStatsBean.setNodesTotal(nodesTotal);

            monitorNodesStatsBeanList.add(monitorNodesStatsBean);

        }

        Connection conn = MysqlClient.getConnection();

        MysqlClient.insertBatchMonitorNodesStatsList(conn, monitorNodesStatsBeanList);

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return monitorNodesStatsBeanList.size();
    }

    public static MonitorStatsBean getMonitorBean(CommonStats cs, String inputType) {

        MonitorStatsBean mb = new MonitorStatsBean();

        mb.setClusterInfo(clusterInfo);

        // 监控的方式
        mb.setMonitorType(inputType);

        // 索引名称
        String indexName = cs.indexing.toString();
        mb.setIndexName(indexName);


        // 索引量 docs count
        long docCount = cs.docs.getCount();
        mb.setDocCount(docCount);

        // 索引大小 G store size_in_bytes
        long storeSize = cs.store.getSizeInBytes();
        mb.setStoreSize(storeSize);


        // 索引写入总量 indexing index_total
        long indexTotal = cs.indexing.getTotal().getIndexCount();
        mb.setIndexTotal(indexTotal);

        // 索引写入并发量 indexing index_current
        long indexCurrent = cs.indexing.getTotal().getIndexCurrent();
        mb.setIndexCurrent(indexCurrent);

        // 索引Get请求量 get total
        long getTotal = cs.get.getCount();
        mb.setGetTotal(getTotal);

        // 索引Get请求并发量 get current
        long getCurrent = cs.get.current();
        mb.setGetCurrent(getCurrent);

        // 索引Search请求量 search query_total
        long queryTotal = cs.getSearch().getTotal().getQueryCount();
        mb.setQueryTotal(queryTotal);

        // 索引Search并发请求量 search query_current
        long queryCurrent = cs.getSearch().getTotal().getQueryCurrent();
        mb.setQueryCurrent(queryCurrent);

        // 索引Search Scroll滚动请求量 scroll_total
        long scrollTotal = cs.getSearch().getTotal().getScrollCount();
        mb.setScrollTotal(scrollTotal);

        // 索引Search Scroll滚动并发请求量 scroll_current
        long scrollCurrent = cs.getSearch().getTotal().getScrollCurrent();
        mb.setScrollCurrent(scrollCurrent);

        // 索引merges并发请求量 merges current
        long mergesCurrent = cs.merge.getCurrent();
        mb.setMergesCurrent(mergesCurrent);

        // 索引merges并发执行doc量 merges current_docs
        long mergersDocs = cs.merge.getCurrentNumDocs();
        mb.setMergersDocs(mergersDocs);

        // 索引refresh刷新量 total
        long refreshTotal = cs.refresh.getTotal();
        mb.setRefreshTotal(refreshTotal);

        // 索引flush刷新量  total
        long flushTotal = cs.flush.getTotal();
        mb.setFlushTotal(flushTotal);

        // 索引query_cache memory_size_in_bytes
        long queryCacheSize = cs.queryCache.getCacheSize();
        mb.setQueryCacheSize(queryCacheSize);

        // 索引query_cache量 total_count
        long queryCacheTotal = cs.queryCache.getCacheCount();
        mb.setQueryCacheTotal(queryCacheTotal);

        // 索引query_cache命中量 hit_count
        long queryCacheHitCount = cs.queryCache.getHitCount();
        mb.setQueryCacheHitCount(queryCacheHitCount);

        // 索引query_cache未命中量 miss_count
        long queryCacheMissCount = cs.queryCache.getMissCount();
        mb.setQueryCacheMissCount(queryCacheMissCount);

        // 索引segments段总量 count
        long segmentsCount = cs.segments.getCount();
        mb.setSegmentsCount(segmentsCount);

        // 索引segments段占用内存的量 memory_in_bytes
        long segmentsMemorySize = cs.segments.getMemoryInBytes();
        mb.setSegmentsMemorySize(segmentsMemorySize);

        // 索引segments段的词典占用内存的量 terms_memory_in_bytes
        long segmentsMemoryTermsSize = cs.segments.getTermsMemoryInBytes();
        mb.setSegmentsMemoryTermsSize(segmentsMemoryTermsSize);

        // 索引translog事务日志的量 size_in_bytes
        long translogSize = cs.translog.getTranslogSizeInBytes();
        mb.setTranslogSize(translogSize);

        String insertTimeStr = DateUtil.getNowDate();
        long insertTime = Long.parseLong(insertTimeStr);
        mb.setInsertTime(insertTime);

        return mb;
    }


    /**
     * 功能描述: <br>
     * ①：通过http://10.155.20.60:9200/_stats获取监控信息
     * ②：解析Json
     * ③：写入ES
     *
     * @Param: rateTime 执行的频率单位秒
     * @Return: boolean
     * @Author: XL.Gao
     * @Date: 2020/11/12 11:19
     */
    public static int startMonitorIndicesStats() {

        List<MonitorStatsBean> monitorStatsBeanList = new ArrayList<>();

        long startGet = System.currentTimeMillis();

        IndicesStatsResponse indicesStatsResponse = ES60Client.getClient().admin().indices().stats(new IndicesStatsRequest()).actionGet();

        long endGet = System.currentTimeMillis();

//        System.out.println(indicesStatsResponse.toString());

        CommonStats totalCommonStats = indicesStatsResponse.getTotal();
        MonitorStatsBean totalCommonStatsBean = getMonitorBean(totalCommonStats, "stats");
        totalCommonStatsBean.setIndexPrimaries("total");
        totalCommonStatsBean.setIndexName("all");
        monitorStatsBeanList.add(totalCommonStatsBean);

//        System.out.println(totalCommonStatsBean.toString());

        CommonStats primariesCommonStats = indicesStatsResponse.getPrimaries();
        MonitorStatsBean primariesCommonStatsBean = getMonitorBean(primariesCommonStats, "stats");
        primariesCommonStatsBean.setIndexPrimaries("primaries");
        primariesCommonStatsBean.setIndexName("all");
        monitorStatsBeanList.add(primariesCommonStatsBean);

        Map<String, IndexStats> indexMap = indicesStatsResponse.getIndices();
        for (Map.Entry<String, IndexStats> entry : indexMap.entrySet()) {
            String indexName = entry.getKey();

            CommonStats primariesIndicesCommonStats = entry.getValue().getPrimaries();
            MonitorStatsBean primariesIndicesCommonStatsBean = getMonitorBean(primariesIndicesCommonStats, "stats");
            primariesIndicesCommonStatsBean.setIndexPrimaries("primaries");
            primariesIndicesCommonStatsBean.setIndexName(indexName);
            monitorStatsBeanList.add(primariesIndicesCommonStatsBean);

            CommonStats totalIndicesCommonStats = entry.getValue().getTotal();
            MonitorStatsBean totalIndicesCommonStatsBean = getMonitorBean(totalIndicesCommonStats, "stats");
            totalIndicesCommonStatsBean.setIndexPrimaries("total");
            totalIndicesCommonStatsBean.setIndexName(indexName);
            monitorStatsBeanList.add(totalIndicesCommonStatsBean);

        }

        long endChange = System.currentTimeMillis();

        Map<String, String> map = new HashMap<>();
        for (MonitorStatsBean msb : monitorStatsBeanList) {
            String json = JSON.toJSONString(msb);
            String id = UUID.randomUUID().toString();
            map.put(id, json);
        }
//        ES86Client.batchInsertEs(map, client86, "monitor_admin_indices_stats", "info");


        Connection conn = MysqlClient.getConnection();

        MysqlClient.insertBatchMonitorStatsMapping(conn, monitorStatsBeanList);

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long endInsert = System.currentTimeMillis();

        System.out.printf("running-es查询时间%s(ms)-解析时间%s(ms)-写入时间%s(ms)", (endGet - startGet), (endChange - endGet), (endInsert - endChange));
        return monitorStatsBeanList.size();
    }

    /*

     */
    public static void m() {

    }
}
