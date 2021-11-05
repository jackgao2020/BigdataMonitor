package com.xl.project.bigdata.bean;

/**
 * @program: IAEngine
 *
 * @description: http://10.155.20.60:9200/_nodes/stats
 *
 * @author: XL.Gao
 *
 * @create: 2020-11-10 18:05
 **/
public class MonitorNodesStatsBean {

    // 集群信息 60 45 86
    String clusterInfo = "";

    // 集群名称 lse1
    String clusterName = "";

    // 集群的节点数量
    int nodesTotal = 0;

    // 集群的节点名称
    String nodesName = "";

    // 集群的节点IP
    String nodesIP = "";

    // 集群的节点角色 master,data,ingest
    String nodesRoles = "";

    // 监控的方式 nodesStats
    String monitorType = "";

    // 索引量 docs count
    long indicesDocCount = 0;

    // 索引大小 G store size_in_bytes
    long indicesStoreSize = 0;

    // 索引写入总量 indexing index_total
    long indicesIndexTotal = 0;

    // 索引写入并发量 indexing index_current
    long indicesIndexCurrent = 0;

    // 索引Get请求量 get total
    long indicesGetTotal = 0;

    // 索引Get请求并发量 get current
    long indicesGetCurrent = 0;

    // 索引Search请求量 search query_total
    long indicesQueryTotal = 0;

    // 索引Search并发请求量 search query_current
    long indicesQueryCurrent = 0;

    // 索引Search Scroll滚动请求量 scroll_total
    long indicesScrollTotal = 0;

    // 索引Search Scroll滚动并发请求量 scroll_current
    long indicesScrollCurrent = 0;

    // 索引merges并发请求量 merges current
    long indicesMergesCurrent = 0;

    // 索引merges并发执行doc量 merges current_docs
    long indicesMergersDocs = 0;

    // 索引refresh刷新量 total
    long indicesRefreshTotal = 0;

    // 索引flush刷新量  total
    long indicesFlushTotal = 0;

    // 索引query_cache memory_size_in_bytes
    long indicesQueryCacheSize = 0;

    // 索引query_cache量 total_count
    long indicesQueryCacheTotal = 0;

    // 索引query_cache命中量 hit_count
    long indicesQueryCacheHitCount = 0;

    // 索引query_cache未命中量 miss_count
    long indicesQueryCacheMissCount = 0;

    // 索引segments段总量 count
    long indicesSegmentsCount = 0;

    // 索引segments段占用内存的量 memory_in_bytes
    long indicesSegmentsMemorySize = 0;

    // 索引segments段的词典占用内存的量 terms_memory_in_bytes
    long indicesSegmentsMemoryTermsSize = 0;

    // 索引translog事务日志的量 size_in_bytes
    long indicesTranslogSize = 0;

    // 节点CPU使用率
    String osCpuPercent = "";

    // 节点Mem总量
    long osMemTotal = 0;

    // 节点Mem剩余量
    long osMemFree = 0;

    // 节点Mem使用量
    long osMemUsed = 0;

    // 节点JVM Heap使用量
    long jvmHeapUsed = 0;

    // 节点JVM Heap总量
    long jvmHeapMax = 0;

    // 节点Threads量
    long threadsCount = 0;

    // 节点Threads峰值量
    long threadsPeakCount = 0;

    // 节点GC年轻代收集数
    long gcYoungCollectionCount = 0;

    // 节点GC老年代收集数
    long gcOldCollectionCount = 0;

    // 节点GC加载的类的当前数量
    long gcClassesTotalLoadedCount = 0;

    // 节点FS磁盘总量
    long fsTotal = 0;

    // 节点FS磁盘剩余量
    long fsFree = 0;

    // 节点Transport连接数量
    long transportServerOpen = 0;

    // 节点Http连接数量
    long httpCurrentOpen = 0;

    // 节点Script脚本完成量
    long  scriptCompilations = 0;

    // 入库时间
    long insertTime = 0;

    public String getClusterInfo() {
        return clusterInfo;
    }

    public void setClusterInfo(String clusterInfo) {
        this.clusterInfo = clusterInfo;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public int getNodesTotal() {
        return nodesTotal;
    }

    public void setNodesTotal(int nodesTotal) {
        this.nodesTotal = nodesTotal;
    }

    public String getNodesName() {
        return nodesName;
    }

    public void setNodesName(String nodesName) {
        this.nodesName = nodesName;
    }

    public String getNodesIP() {
        return nodesIP;
    }

    public void setNodesIP(String nodesIP) {
        this.nodesIP = nodesIP;
    }

    public String getNodesRoles() {
        return nodesRoles;
    }

    public void setNodesRoles(String nodesRoles) {
        this.nodesRoles = nodesRoles;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public long getIndicesDocCount() {
        return indicesDocCount;
    }

    public void setIndicesDocCount(long indicesDocCount) {
        this.indicesDocCount = indicesDocCount;
    }

    public long getIndicesStoreSize() {
        return indicesStoreSize;
    }

    public void setIndicesStoreSize(long indicesStoreSize) {
        this.indicesStoreSize = indicesStoreSize;
    }

    public long getIndicesIndexTotal() {
        return indicesIndexTotal;
    }

    public void setIndicesIndexTotal(long indicesIndexTotal) {
        this.indicesIndexTotal = indicesIndexTotal;
    }

    public long getIndicesIndexCurrent() {
        return indicesIndexCurrent;
    }

    public void setIndicesIndexCurrent(long indicesIndexCurrent) {
        this.indicesIndexCurrent = indicesIndexCurrent;
    }

    public long getIndicesGetTotal() {
        return indicesGetTotal;
    }

    public void setIndicesGetTotal(long indicesGetTotal) {
        this.indicesGetTotal = indicesGetTotal;
    }

    public long getIndicesGetCurrent() {
        return indicesGetCurrent;
    }

    public void setIndicesGetCurrent(long indicesGetCurrent) {
        this.indicesGetCurrent = indicesGetCurrent;
    }

    public long getIndicesQueryTotal() {
        return indicesQueryTotal;
    }

    public void setIndicesQueryTotal(long indicesQueryTotal) {
        this.indicesQueryTotal = indicesQueryTotal;
    }

    public long getIndicesQueryCurrent() {
        return indicesQueryCurrent;
    }

    public void setIndicesQueryCurrent(long indicesQueryCurrent) {
        this.indicesQueryCurrent = indicesQueryCurrent;
    }

    public long getIndicesScrollTotal() {
        return indicesScrollTotal;
    }

    public void setIndicesScrollTotal(long indicesScrollTotal) {
        this.indicesScrollTotal = indicesScrollTotal;
    }

    public long getIndicesScrollCurrent() {
        return indicesScrollCurrent;
    }

    public void setIndicesScrollCurrent(long indicesScrollCurrent) {
        this.indicesScrollCurrent = indicesScrollCurrent;
    }

    public long getIndicesMergesCurrent() {
        return indicesMergesCurrent;
    }

    public void setIndicesMergesCurrent(long indicesMergesCurrent) {
        this.indicesMergesCurrent = indicesMergesCurrent;
    }

    public long getIndicesMergersDocs() {
        return indicesMergersDocs;
    }

    public void setIndicesMergersDocs(long indicesMergersDocs) {
        this.indicesMergersDocs = indicesMergersDocs;
    }

    public long getIndicesRefreshTotal() {
        return indicesRefreshTotal;
    }

    public void setIndicesRefreshTotal(long indicesRefreshTotal) {
        this.indicesRefreshTotal = indicesRefreshTotal;
    }

    public long getIndicesFlushTotal() {
        return indicesFlushTotal;
    }

    public void setIndicesFlushTotal(long indicesFlushTotal) {
        this.indicesFlushTotal = indicesFlushTotal;
    }

    public long getIndicesQueryCacheSize() {
        return indicesQueryCacheSize;
    }

    public void setIndicesQueryCacheSize(long indicesQueryCacheSize) {
        this.indicesQueryCacheSize = indicesQueryCacheSize;
    }

    public long getIndicesQueryCacheTotal() {
        return indicesQueryCacheTotal;
    }

    public void setIndicesQueryCacheTotal(long indicesQueryCacheTotal) {
        this.indicesQueryCacheTotal = indicesQueryCacheTotal;
    }

    public long getIndicesQueryCacheHitCount() {
        return indicesQueryCacheHitCount;
    }

    public void setIndicesQueryCacheHitCount(long indicesQueryCacheHitCount) {
        this.indicesQueryCacheHitCount = indicesQueryCacheHitCount;
    }

    public long getIndicesQueryCacheMissCount() {
        return indicesQueryCacheMissCount;
    }

    public void setIndicesQueryCacheMissCount(long indicesQueryCacheMissCount) {
        this.indicesQueryCacheMissCount = indicesQueryCacheMissCount;
    }

    public long getIndicesSegmentsCount() {
        return indicesSegmentsCount;
    }

    public void setIndicesSegmentsCount(long indicesSegmentsCount) {
        this.indicesSegmentsCount = indicesSegmentsCount;
    }

    public long getIndicesSegmentsMemorySize() {
        return indicesSegmentsMemorySize;
    }

    public void setIndicesSegmentsMemorySize(long indicesSegmentsMemorySize) {
        this.indicesSegmentsMemorySize = indicesSegmentsMemorySize;
    }

    public long getIndicesSegmentsMemoryTermsSize() {
        return indicesSegmentsMemoryTermsSize;
    }

    public void setIndicesSegmentsMemoryTermsSize(long indicesSegmentsMemoryTermsSize) {
        this.indicesSegmentsMemoryTermsSize = indicesSegmentsMemoryTermsSize;
    }

    public long getIndicesTranslogSize() {
        return indicesTranslogSize;
    }

    public void setIndicesTranslogSize(long indicesTranslogSize) {
        this.indicesTranslogSize = indicesTranslogSize;
    }

    public String getOsCpuPercent() {
        return osCpuPercent;
    }

    public void setOsCpuPercent(String osCpuPercent) {
        this.osCpuPercent = osCpuPercent;
    }

    public long getOsMemTotal() {
        return osMemTotal;
    }

    public void setOsMemTotal(long osMemTotal) {
        this.osMemTotal = osMemTotal;
    }

    public long getOsMemFree() {
        return osMemFree;
    }

    public void setOsMemFree(long osMemFree) {
        this.osMemFree = osMemFree;
    }

    public long getOsMemUsed() {
        return osMemUsed;
    }

    public void setOsMemUsed(long osMemUsed) {
        this.osMemUsed = osMemUsed;
    }

    public long getJvmHeapUsed() {
        return jvmHeapUsed;
    }

    public void setJvmHeapUsed(long jvmHeapUsed) {
        this.jvmHeapUsed = jvmHeapUsed;
    }

    public long getJvmHeapMax() {
        return jvmHeapMax;
    }

    public void setJvmHeapMax(long jvmHeapMax) {
        this.jvmHeapMax = jvmHeapMax;
    }

    public long getThreadsCount() {
        return threadsCount;
    }

    public void setThreadsCount(long threadsCount) {
        this.threadsCount = threadsCount;
    }

    public long getThreadsPeakCount() {
        return threadsPeakCount;
    }

    public void setThreadsPeakCount(long threadsPeakCount) {
        this.threadsPeakCount = threadsPeakCount;
    }

    public long getGcYoungCollectionCount() {
        return gcYoungCollectionCount;
    }

    public void setGcYoungCollectionCount(long gcYoungCollectionCount) {
        this.gcYoungCollectionCount = gcYoungCollectionCount;
    }

    public long getGcOldCollectionCount() {
        return gcOldCollectionCount;
    }

    public void setGcOldCollectionCount(long gcOldCollectionCount) {
        this.gcOldCollectionCount = gcOldCollectionCount;
    }

    public long getGcClassesTotalLoadedCount() {
        return gcClassesTotalLoadedCount;
    }

    public void setGcClassesTotalLoadedCount(long gcClassesTotalLoadedCount) {
        this.gcClassesTotalLoadedCount = gcClassesTotalLoadedCount;
    }

    public long getFsTotal() {
        return fsTotal;
    }

    public void setFsTotal(long fsTotal) {
        this.fsTotal = fsTotal;
    }

    public long getFsFree() {
        return fsFree;
    }

    public void setFsFree(long fsFree) {
        this.fsFree = fsFree;
    }

    public long getTransportServerOpen() {
        return transportServerOpen;
    }

    public void setTransportServerOpen(long transportServerOpen) {
        this.transportServerOpen = transportServerOpen;
    }

    public long getHttpCurrentOpen() {
        return httpCurrentOpen;
    }

    public void setHttpCurrentOpen(long httpCurrentOpen) {
        this.httpCurrentOpen = httpCurrentOpen;
    }

    public long getScriptCompilations() {
        return scriptCompilations;
    }

    public void setScriptCompilations(long scriptCompilations) {
        this.scriptCompilations = scriptCompilations;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MonitorNodesStatsBean{");
        sb.append("clusterInfo='").append(clusterInfo).append('\'');
        sb.append(", clusterName='").append(clusterName).append('\'');
        sb.append(", nodesTotal=").append(nodesTotal);
        sb.append(", nodesName='").append(nodesName).append('\'');
        sb.append(", nodesIP='").append(nodesIP).append('\'');
        sb.append(", nodesRoles='").append(nodesRoles).append('\'');
        sb.append(", monitorType='").append(monitorType).append('\'');
        sb.append(", indicesDocCount=").append(indicesDocCount);
        sb.append(", indicesStoreSize=").append(indicesStoreSize);
        sb.append(", indicesIndexTotal=").append(indicesIndexTotal);
        sb.append(", indicesIndexCurrent=").append(indicesIndexCurrent);
        sb.append(", indicesGetTotal=").append(indicesGetTotal);
        sb.append(", indicesGetCurrent=").append(indicesGetCurrent);
        sb.append(", indicesQueryTotal=").append(indicesQueryTotal);
        sb.append(", indicesQueryCurrent=").append(indicesQueryCurrent);
        sb.append(", indicesScrollTotal=").append(indicesScrollTotal);
        sb.append(", indicesScrollCurrent=").append(indicesScrollCurrent);
        sb.append(", indicesMergesCurrent=").append(indicesMergesCurrent);
        sb.append(", indicesMergersDocs=").append(indicesMergersDocs);
        sb.append(", indicesRefreshTotal=").append(indicesRefreshTotal);
        sb.append(", indicesFlushTotal=").append(indicesFlushTotal);
        sb.append(", indicesQueryCacheSize=").append(indicesQueryCacheSize);
        sb.append(", indicesQueryCacheTotal=").append(indicesQueryCacheTotal);
        sb.append(", indicesQueryCacheHitCount=").append(indicesQueryCacheHitCount);
        sb.append(", indicesQueryCacheMissCount=").append(indicesQueryCacheMissCount);
        sb.append(", indicesSegmentsCount=").append(indicesSegmentsCount);
        sb.append(", indicesSegmentsMemorySize=").append(indicesSegmentsMemorySize);
        sb.append(", indicesSegmentsMemoryTermsSize=").append(indicesSegmentsMemoryTermsSize);
        sb.append(", indicesTranslogSize=").append(indicesTranslogSize);
        sb.append(", osCpuPercent='").append(osCpuPercent).append('\'');
        sb.append(", osMemTotal=").append(osMemTotal);
        sb.append(", osMemFree=").append(osMemFree);
        sb.append(", osMemUsed=").append(osMemUsed);
        sb.append(", jvmHeapUsed=").append(jvmHeapUsed);
        sb.append(", jvmHeapMax=").append(jvmHeapMax);
        sb.append(", threadsCount=").append(threadsCount);
        sb.append(", threadsPeakCount=").append(threadsPeakCount);
        sb.append(", gcYoungCollectionCount=").append(gcYoungCollectionCount);
        sb.append(", gcOldCollectionCount=").append(gcOldCollectionCount);
        sb.append(", gcClassesTotalLoadedCount=").append(gcClassesTotalLoadedCount);
        sb.append(", fsTotal=").append(fsTotal);
        sb.append(", fsFree=").append(fsFree);
        sb.append(", transportServerOpen=").append(transportServerOpen);
        sb.append(", httpCurrentOpen=").append(httpCurrentOpen);
        sb.append(", scriptCompilations=").append(scriptCompilations);
        sb.append(", insertTime=").append(insertTime);
        sb.append('}');
        return sb.toString();
    }
}
