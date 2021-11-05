package com.xl.project.bigdata.domain;

import com.xl.framework.aspectj.lang.annotation.Excel;
import com.xl.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 节点监控对象 monitor_admin_cluster_nodes_stats
 * 
 * @author xl
 * @date 2020-12-09
 */
public class MonitorAdminClusterNodesStats extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long pkId;

    /** 集群信息 60 45 86 */
    @Excel(name = "集群信息 60 45 86")
    private String clusterInfo;

    /** 集群名称 lse1 */
    @Excel(name = "集群名称 lse1")
    private String clusterName;

    /** 集群节点数 */
    @Excel(name = "集群节点数")
    private Long nodesTotal;

    /** 集群的节点IP */
    @Excel(name = "集群的节点IP")
    private String nodesIp;

    /** 集群的节点角色 master,data,inges,多个用逗号分隔 */
    @Excel(name = "集群的节点角色 master,data,inges,多个用逗号分隔")
    private String nodesRoles;

    /** 监控的方式 nodesStats */
    private String monitorType;

    /** 索引量 docs count */
    private Long indicesDocCount;

    /** 索引大小 G store size_in_bytes */
    private Long indicesStoreSize;

    /** 索引写入总量 indexing index_total */
    private Long indicesIndexTotal;

    /** 索引写入并发量 indexing index_current */
    private Long indicesIndexCurrent;

    /** 索引Get请求量 get total */
    private Long indicesGetTotal;

    /** 索引Get请求并发量 get current */
    private Long indicesGetCurrent;

    /** 索引Search请求量 search query_total */
    private Long indicesQueryTotal;

    /** 索引Search并发请求量 search query_current */
    private Long indicesQueryCurrent;

    /** 索引Search Scroll滚动请求量 scroll_total */
    private Long indicesScrollTotal;

    /** 索引Search Scroll滚动并发请求量 scroll_current */
    private Long indicesScrollCurrent;

    /** 索引merges并发请求量 merges current */
    private Long indicesMergesCurrent;

    /** 索引merges并发执行doc量 merges current_docs */
    private Long indicesMergersDocs;

    /** 索引refresh刷新量 total */
    private Long indicesRefreshTotal;

    /** 索引flush刷新量  total */
    private Long indicesFlushTotal;

    /** 索引query_cache memory_size_in_bytes */
    private Long indicesQueryCacheSize;

    /** 索引query_cache量 total_count */
    private Long indicesQueryCacheTotal;

    /** 索引query_cache命中量 hit_count */
    private Long indicesQueryCacheHitCount;

    /** 索引query_cache未命中量 miss_count */
    private Long indicesQueryCacheMissCount;

    /** 索引segments段总量 count */
    private Long indicesSegmentsCount;

    /** 索引segments段占用内存的量 memory_in_bytes */
    private Long indicesSegmentsMemorySize;

    /** 索引segments段的词典占用内存的量 terms_memory_in_bytes */
    private Long indicesSegmentsMemoryTermsSize;

    /** 索引translog事务日志的量 size_in_bytes */
    private Long indicesTranslogSize;

    /** 节点CPU使用率 */
    @Excel(name = "节点CPU使用率")
    private Long osCpuPercent;

    /** 节点Mem总量 */
    @Excel(name = "节点Mem总量")
    private Long osMemTotal;

    /** 节点Mem剩余量 */
    @Excel(name = "节点Mem剩余量")
    private Long osMemFree;

    /** 节点Mem使用量 */
    @Excel(name = "节点Mem使用量")
    private Long osMemUsed;

    /** 节点JVM Heap使用量 */
    private Long jvmHeapUsed;

    /** 节点JVM Heap总量 */
    private Long jvmHeapMax;

    /** 节点Threads量 */
    private Long threadsCount;

    /** 节点Threads峰值量 */
    private Long threadsPeakCount;

    /** 节点GC年轻代收集数 */
    private Long gcYoungCollectionCount;

    /** 节点GC老年代收集数 */
    private Long gcOldCollectionCount;

    /** 节点GC加载的类的当前数量 */
    private Long gcClassesTotalLoadedCount;

    /** 节点FS磁盘总量 */
    @Excel(name = "节点FS磁盘总量")
    private Long fsTotal;

    /** 节点FS磁盘剩余量 */
    @Excel(name = "节点FS磁盘剩余量")
    private Long fsFree;

    /** 节点Transport连接数量 */
    private Long transportServerOpen;

    /** 节点Http连接数量 */
    private Long httpCurrentOpen;

    /** 节点Script脚本完成量 */
    private Long scriptCompilations;

    /** 入库时间 */
    private Long insertTime;

    public void setPkId(Long pkId) 
    {
        this.pkId = pkId;
    }

    public Long getPkId() 
    {
        return pkId;
    }
    public void setClusterInfo(String clusterInfo) 
    {
        this.clusterInfo = clusterInfo;
    }

    public String getClusterInfo() 
    {
        return clusterInfo;
    }
    public void setClusterName(String clusterName) 
    {
        this.clusterName = clusterName;
    }

    public String getClusterName() 
    {
        return clusterName;
    }
    public void setNodesTotal(Long nodesTotal) 
    {
        this.nodesTotal = nodesTotal;
    }

    public Long getNodesTotal() 
    {
        return nodesTotal;
    }
    public void setNodesIp(String nodesIp) 
    {
        this.nodesIp = nodesIp;
    }

    public String getNodesIp() 
    {
        return nodesIp;
    }
    public void setNodesRoles(String nodesRoles) 
    {
        this.nodesRoles = nodesRoles;
    }

    public String getNodesRoles() 
    {
        return nodesRoles;
    }
    public void setMonitorType(String monitorType) 
    {
        this.monitorType = monitorType;
    }

    public String getMonitorType() 
    {
        return monitorType;
    }
    public void setIndicesDocCount(Long indicesDocCount) 
    {
        this.indicesDocCount = indicesDocCount;
    }

    public Long getIndicesDocCount() 
    {
        return indicesDocCount;
    }
    public void setIndicesStoreSize(Long indicesStoreSize) 
    {
        this.indicesStoreSize = indicesStoreSize;
    }

    public Long getIndicesStoreSize() 
    {
        return indicesStoreSize;
    }
    public void setIndicesIndexTotal(Long indicesIndexTotal) 
    {
        this.indicesIndexTotal = indicesIndexTotal;
    }

    public Long getIndicesIndexTotal() 
    {
        return indicesIndexTotal;
    }
    public void setIndicesIndexCurrent(Long indicesIndexCurrent) 
    {
        this.indicesIndexCurrent = indicesIndexCurrent;
    }

    public Long getIndicesIndexCurrent() 
    {
        return indicesIndexCurrent;
    }
    public void setIndicesGetTotal(Long indicesGetTotal) 
    {
        this.indicesGetTotal = indicesGetTotal;
    }

    public Long getIndicesGetTotal() 
    {
        return indicesGetTotal;
    }
    public void setIndicesGetCurrent(Long indicesGetCurrent) 
    {
        this.indicesGetCurrent = indicesGetCurrent;
    }

    public Long getIndicesGetCurrent() 
    {
        return indicesGetCurrent;
    }
    public void setIndicesQueryTotal(Long indicesQueryTotal) 
    {
        this.indicesQueryTotal = indicesQueryTotal;
    }

    public Long getIndicesQueryTotal() 
    {
        return indicesQueryTotal;
    }
    public void setIndicesQueryCurrent(Long indicesQueryCurrent) 
    {
        this.indicesQueryCurrent = indicesQueryCurrent;
    }

    public Long getIndicesQueryCurrent() 
    {
        return indicesQueryCurrent;
    }
    public void setIndicesScrollTotal(Long indicesScrollTotal) 
    {
        this.indicesScrollTotal = indicesScrollTotal;
    }

    public Long getIndicesScrollTotal() 
    {
        return indicesScrollTotal;
    }
    public void setIndicesScrollCurrent(Long indicesScrollCurrent) 
    {
        this.indicesScrollCurrent = indicesScrollCurrent;
    }

    public Long getIndicesScrollCurrent() 
    {
        return indicesScrollCurrent;
    }
    public void setIndicesMergesCurrent(Long indicesMergesCurrent) 
    {
        this.indicesMergesCurrent = indicesMergesCurrent;
    }

    public Long getIndicesMergesCurrent() 
    {
        return indicesMergesCurrent;
    }
    public void setIndicesMergersDocs(Long indicesMergersDocs) 
    {
        this.indicesMergersDocs = indicesMergersDocs;
    }

    public Long getIndicesMergersDocs() 
    {
        return indicesMergersDocs;
    }
    public void setIndicesRefreshTotal(Long indicesRefreshTotal) 
    {
        this.indicesRefreshTotal = indicesRefreshTotal;
    }

    public Long getIndicesRefreshTotal() 
    {
        return indicesRefreshTotal;
    }
    public void setIndicesFlushTotal(Long indicesFlushTotal) 
    {
        this.indicesFlushTotal = indicesFlushTotal;
    }

    public Long getIndicesFlushTotal() 
    {
        return indicesFlushTotal;
    }
    public void setIndicesQueryCacheSize(Long indicesQueryCacheSize) 
    {
        this.indicesQueryCacheSize = indicesQueryCacheSize;
    }

    public Long getIndicesQueryCacheSize() 
    {
        return indicesQueryCacheSize;
    }
    public void setIndicesQueryCacheTotal(Long indicesQueryCacheTotal) 
    {
        this.indicesQueryCacheTotal = indicesQueryCacheTotal;
    }

    public Long getIndicesQueryCacheTotal() 
    {
        return indicesQueryCacheTotal;
    }
    public void setIndicesQueryCacheHitCount(Long indicesQueryCacheHitCount) 
    {
        this.indicesQueryCacheHitCount = indicesQueryCacheHitCount;
    }

    public Long getIndicesQueryCacheHitCount() 
    {
        return indicesQueryCacheHitCount;
    }
    public void setIndicesQueryCacheMissCount(Long indicesQueryCacheMissCount) 
    {
        this.indicesQueryCacheMissCount = indicesQueryCacheMissCount;
    }

    public Long getIndicesQueryCacheMissCount() 
    {
        return indicesQueryCacheMissCount;
    }
    public void setIndicesSegmentsCount(Long indicesSegmentsCount) 
    {
        this.indicesSegmentsCount = indicesSegmentsCount;
    }

    public Long getIndicesSegmentsCount() 
    {
        return indicesSegmentsCount;
    }
    public void setIndicesSegmentsMemorySize(Long indicesSegmentsMemorySize) 
    {
        this.indicesSegmentsMemorySize = indicesSegmentsMemorySize;
    }

    public Long getIndicesSegmentsMemorySize() 
    {
        return indicesSegmentsMemorySize;
    }
    public void setIndicesSegmentsMemoryTermsSize(Long indicesSegmentsMemoryTermsSize) 
    {
        this.indicesSegmentsMemoryTermsSize = indicesSegmentsMemoryTermsSize;
    }

    public Long getIndicesSegmentsMemoryTermsSize() 
    {
        return indicesSegmentsMemoryTermsSize;
    }
    public void setIndicesTranslogSize(Long indicesTranslogSize) 
    {
        this.indicesTranslogSize = indicesTranslogSize;
    }

    public Long getIndicesTranslogSize() 
    {
        return indicesTranslogSize;
    }
    public void setOsCpuPercent(Long osCpuPercent) 
    {
        this.osCpuPercent = osCpuPercent;
    }

    public Long getOsCpuPercent() 
    {
        return osCpuPercent;
    }
    public void setOsMemTotal(Long osMemTotal) 
    {
        this.osMemTotal = osMemTotal;
    }

    public Long getOsMemTotal() 
    {
        return osMemTotal;
    }
    public void setOsMemFree(Long osMemFree) 
    {
        this.osMemFree = osMemFree;
    }

    public Long getOsMemFree() 
    {
        return osMemFree;
    }
    public void setOsMemUsed(Long osMemUsed) 
    {
        this.osMemUsed = osMemUsed;
    }

    public Long getOsMemUsed() 
    {
        return osMemUsed;
    }
    public void setJvmHeapUsed(Long jvmHeapUsed) 
    {
        this.jvmHeapUsed = jvmHeapUsed;
    }

    public Long getJvmHeapUsed() 
    {
        return jvmHeapUsed;
    }
    public void setJvmHeapMax(Long jvmHeapMax) 
    {
        this.jvmHeapMax = jvmHeapMax;
    }

    public Long getJvmHeapMax() 
    {
        return jvmHeapMax;
    }
    public void setThreadsCount(Long threadsCount) 
    {
        this.threadsCount = threadsCount;
    }

    public Long getThreadsCount() 
    {
        return threadsCount;
    }
    public void setThreadsPeakCount(Long threadsPeakCount) 
    {
        this.threadsPeakCount = threadsPeakCount;
    }

    public Long getThreadsPeakCount() 
    {
        return threadsPeakCount;
    }
    public void setGcYoungCollectionCount(Long gcYoungCollectionCount) 
    {
        this.gcYoungCollectionCount = gcYoungCollectionCount;
    }

    public Long getGcYoungCollectionCount() 
    {
        return gcYoungCollectionCount;
    }
    public void setGcOldCollectionCount(Long gcOldCollectionCount) 
    {
        this.gcOldCollectionCount = gcOldCollectionCount;
    }

    public Long getGcOldCollectionCount() 
    {
        return gcOldCollectionCount;
    }
    public void setGcClassesTotalLoadedCount(Long gcClassesTotalLoadedCount) 
    {
        this.gcClassesTotalLoadedCount = gcClassesTotalLoadedCount;
    }

    public Long getGcClassesTotalLoadedCount() 
    {
        return gcClassesTotalLoadedCount;
    }
    public void setFsTotal(Long fsTotal) 
    {
        this.fsTotal = fsTotal;
    }

    public Long getFsTotal() 
    {
        return fsTotal;
    }
    public void setFsFree(Long fsFree) 
    {
        this.fsFree = fsFree;
    }

    public Long getFsFree() 
    {
        return fsFree;
    }
    public void setTransportServerOpen(Long transportServerOpen) 
    {
        this.transportServerOpen = transportServerOpen;
    }

    public Long getTransportServerOpen() 
    {
        return transportServerOpen;
    }
    public void setHttpCurrentOpen(Long httpCurrentOpen) 
    {
        this.httpCurrentOpen = httpCurrentOpen;
    }

    public Long getHttpCurrentOpen() 
    {
        return httpCurrentOpen;
    }
    public void setScriptCompilations(Long scriptCompilations) 
    {
        this.scriptCompilations = scriptCompilations;
    }

    public Long getScriptCompilations() 
    {
        return scriptCompilations;
    }
    public void setInsertTime(Long insertTime) 
    {
        this.insertTime = insertTime;
    }

    public Long getInsertTime() 
    {
        return insertTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pkId", getPkId())
            .append("clusterInfo", getClusterInfo())
            .append("clusterName", getClusterName())
            .append("nodesTotal", getNodesTotal())
            .append("nodesIp", getNodesIp())
            .append("nodesRoles", getNodesRoles())
            .append("monitorType", getMonitorType())
            .append("indicesDocCount", getIndicesDocCount())
            .append("indicesStoreSize", getIndicesStoreSize())
            .append("indicesIndexTotal", getIndicesIndexTotal())
            .append("indicesIndexCurrent", getIndicesIndexCurrent())
            .append("indicesGetTotal", getIndicesGetTotal())
            .append("indicesGetCurrent", getIndicesGetCurrent())
            .append("indicesQueryTotal", getIndicesQueryTotal())
            .append("indicesQueryCurrent", getIndicesQueryCurrent())
            .append("indicesScrollTotal", getIndicesScrollTotal())
            .append("indicesScrollCurrent", getIndicesScrollCurrent())
            .append("indicesMergesCurrent", getIndicesMergesCurrent())
            .append("indicesMergersDocs", getIndicesMergersDocs())
            .append("indicesRefreshTotal", getIndicesRefreshTotal())
            .append("indicesFlushTotal", getIndicesFlushTotal())
            .append("indicesQueryCacheSize", getIndicesQueryCacheSize())
            .append("indicesQueryCacheTotal", getIndicesQueryCacheTotal())
            .append("indicesQueryCacheHitCount", getIndicesQueryCacheHitCount())
            .append("indicesQueryCacheMissCount", getIndicesQueryCacheMissCount())
            .append("indicesSegmentsCount", getIndicesSegmentsCount())
            .append("indicesSegmentsMemorySize", getIndicesSegmentsMemorySize())
            .append("indicesSegmentsMemoryTermsSize", getIndicesSegmentsMemoryTermsSize())
            .append("indicesTranslogSize", getIndicesTranslogSize())
            .append("osCpuPercent", getOsCpuPercent())
            .append("osMemTotal", getOsMemTotal())
            .append("osMemFree", getOsMemFree())
            .append("osMemUsed", getOsMemUsed())
            .append("jvmHeapUsed", getJvmHeapUsed())
            .append("jvmHeapMax", getJvmHeapMax())
            .append("threadsCount", getThreadsCount())
            .append("threadsPeakCount", getThreadsPeakCount())
            .append("gcYoungCollectionCount", getGcYoungCollectionCount())
            .append("gcOldCollectionCount", getGcOldCollectionCount())
            .append("gcClassesTotalLoadedCount", getGcClassesTotalLoadedCount())
            .append("fsTotal", getFsTotal())
            .append("fsFree", getFsFree())
            .append("transportServerOpen", getTransportServerOpen())
            .append("httpCurrentOpen", getHttpCurrentOpen())
            .append("scriptCompilations", getScriptCompilations())
            .append("insertTime", getInsertTime())
            .toString();
    }
}
