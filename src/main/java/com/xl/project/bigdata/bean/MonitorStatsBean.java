package com.xl.project.bigdata.bean;

/**
 * @program: IAEngine
 *
 * @description:
 *
 * @author: XL.Gao
 *
 * @create: 2020-11-10 18:05
 **/
public class MonitorStatsBean {



    // 集群信息 60 45 86
    String clusterInfo = "";

    // 监控的方式
    String monitorType = "";

    // 索引名称
    String indexName = "";

    // 主和all区分 默认=primaries total
    String indexPrimaries = "";

    // 索引量 docs count
    long docCount = 0;

    // 索引大小 G store size_in_bytes
    long storeSize = 0;

    // 索引写入总量 indexing index_total
    long indexTotal = 0;

    // 索引写入并发量 indexing index_current
    long indexCurrent = 0;

    // 索引Get请求量 get total
    long getTotal = 0;

    // 索引Get请求并发量 get current
    long getCurrent = 0;

    // 索引Search请求量 search query_total
    long queryTotal = 0;

    // 索引Search并发请求量 search query_current
    long queryCurrent = 0;

    // 索引Search Scroll滚动请求量 scroll_total
    long scrollTotal = 0;

    // 索引Search Scroll滚动并发请求量 scroll_current
    long scrollCurrent = 0;

    // 索引merges并发请求量 merges current
    long mergesCurrent = 0;

    // 索引merges并发执行doc量 merges current_docs
    long mergersDocs = 0;

    // 索引refresh刷新量 total
    long refreshTotal = 0;

    // 索引flush刷新量  total
    long flushTotal = 0;

    // 索引query_cache memory_size_in_bytes
    long queryCacheSize = 0;

    // 索引query_cache量 total_count
    long queryCacheTotal = 0;

    // 索引query_cache命中量 hit_count
    long queryCacheHitCount = 0;

    // 索引query_cache未命中量 miss_count
    long queryCacheMissCount = 0;

    // 索引segments段总量 count
    long segmentsCount = 0;

    // 索引segments段占用内存的量 memory_in_bytes
    long segmentsMemorySize = 0;

    // 索引segments段的词典占用内存的量 terms_memory_in_bytes
    long segmentsMemoryTermsSize = 0;

    // 索引translog事务日志的量 size_in_bytes
    long translogSize = 0;

    // 查询时间
    long insertTime = 0;

    public String getClusterInfo() {
        return clusterInfo;
    }

    public void setClusterInfo(String clusterInfo) {
        this.clusterInfo = clusterInfo;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexPrimaries() {
        return indexPrimaries;
    }

    public void setIndexPrimaries(String indexPrimaries) {
        this.indexPrimaries = indexPrimaries;
    }

    public long getDocCount() {
        return docCount;
    }

    public void setDocCount(long docCount) {
        this.docCount = docCount;
    }

    public long getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(long storeSize) {
        this.storeSize = storeSize;
    }

    public long getIndexTotal() {
        return indexTotal;
    }

    public void setIndexTotal(long indexTotal) {
        this.indexTotal = indexTotal;
    }

    public long getIndexCurrent() {
        return indexCurrent;
    }

    public void setIndexCurrent(long indexCurrent) {
        this.indexCurrent = indexCurrent;
    }

    public long getGetTotal() {
        return getTotal;
    }

    public void setGetTotal(long getTotal) {
        this.getTotal = getTotal;
    }

    public long getGetCurrent() {
        return getCurrent;
    }

    public void setGetCurrent(long getCurrent) {
        this.getCurrent = getCurrent;
    }

    public long getQueryTotal() {
        return queryTotal;
    }

    public void setQueryTotal(long queryTotal) {
        this.queryTotal = queryTotal;
    }

    public long getQueryCurrent() {
        return queryCurrent;
    }

    public void setQueryCurrent(long queryCurrent) {
        this.queryCurrent = queryCurrent;
    }

    public long getScrollTotal() {
        return scrollTotal;
    }

    public void setScrollTotal(long scrollTotal) {
        this.scrollTotal = scrollTotal;
    }

    public long getScrollCurrent() {
        return scrollCurrent;
    }

    public void setScrollCurrent(long scrollCurrent) {
        this.scrollCurrent = scrollCurrent;
    }

    public long getMergesCurrent() {
        return mergesCurrent;
    }

    public void setMergesCurrent(long mergesCurrent) {
        this.mergesCurrent = mergesCurrent;
    }

    public long getMergersDocs() {
        return mergersDocs;
    }

    public void setMergersDocs(long mergersDocs) {
        this.mergersDocs = mergersDocs;
    }

    public long getRefreshTotal() {
        return refreshTotal;
    }

    public void setRefreshTotal(long refreshTotal) {
        this.refreshTotal = refreshTotal;
    }

    public long getFlushTotal() {
        return flushTotal;
    }

    public void setFlushTotal(long flushTotal) {
        this.flushTotal = flushTotal;
    }

    public long getQueryCacheSize() {
        return queryCacheSize;
    }

    public void setQueryCacheSize(long queryCacheSize) {
        this.queryCacheSize = queryCacheSize;
    }

    public long getQueryCacheTotal() {
        return queryCacheTotal;
    }

    public void setQueryCacheTotal(long queryCacheTotal) {
        this.queryCacheTotal = queryCacheTotal;
    }

    public long getQueryCacheHitCount() {
        return queryCacheHitCount;
    }

    public void setQueryCacheHitCount(long queryCacheHitCount) {
        this.queryCacheHitCount = queryCacheHitCount;
    }

    public long getQueryCacheMissCount() {
        return queryCacheMissCount;
    }

    public void setQueryCacheMissCount(long queryCacheMissCount) {
        this.queryCacheMissCount = queryCacheMissCount;
    }

    public long getSegmentsCount() {
        return segmentsCount;
    }

    public void setSegmentsCount(long segmentsCount) {
        this.segmentsCount = segmentsCount;
    }

    public long getSegmentsMemorySize() {
        return segmentsMemorySize;
    }

    public void setSegmentsMemorySize(long segmentsMemorySize) {
        this.segmentsMemorySize = segmentsMemorySize;
    }

    public long getSegmentsMemoryTermsSize() {
        return segmentsMemoryTermsSize;
    }

    public void setSegmentsMemoryTermsSize(long segmentsMemoryTermsSize) {
        this.segmentsMemoryTermsSize = segmentsMemoryTermsSize;
    }

    public long getTranslogSize() {
        return translogSize;
    }

    public void setTranslogSize(long translogSize) {
        this.translogSize = translogSize;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MonitorStatsBean{");
        sb.append("monitorType='").append(monitorType).append('\'');
        sb.append(", indexName='").append(indexName).append('\'');
        sb.append(", indexPrimaries='").append(indexPrimaries).append('\'');
        sb.append(", docCount=").append(docCount);
        sb.append(", storeSize=").append(storeSize);
        sb.append(", indexTotal=").append(indexTotal);
        sb.append(", indexCurrent=").append(indexCurrent);
        sb.append(", getTotal=").append(getTotal);
        sb.append(", getCurrent=").append(getCurrent);
        sb.append(", queryTotal=").append(queryTotal);
        sb.append(", queryCurrent=").append(queryCurrent);
        sb.append(", scrollTotal=").append(scrollTotal);
        sb.append(", scrollCurrent=").append(scrollCurrent);
        sb.append(", mergesCurrent=").append(mergesCurrent);
        sb.append(", mergersDocs=").append(mergersDocs);
        sb.append(", refreshTotal=").append(refreshTotal);
        sb.append(", flushTotal=").append(flushTotal);
        sb.append(", queryCacheSize=").append(queryCacheSize);
        sb.append(", queryCacheTotal=").append(queryCacheTotal);
        sb.append(", queryCacheHitCount=").append(queryCacheHitCount);
        sb.append(", queryCacheMissCount=").append(queryCacheMissCount);
        sb.append(", segmentsCount=").append(segmentsCount);
        sb.append(", segmentsMemorySize=").append(segmentsMemorySize);
        sb.append(", segmentsMemoryTermsSize=").append(segmentsMemoryTermsSize);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", translogSize=").append(translogSize);
        sb.append('}');
        return sb.toString();
    }
}
