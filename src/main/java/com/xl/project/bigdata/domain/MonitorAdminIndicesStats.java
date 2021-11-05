package com.xl.project.bigdata.domain;

import com.xl.framework.aspectj.lang.annotation.Excel;
import com.xl.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * es索引监控对象 monitor_admin_indices_stats
 * 
 * @author elf
 * @date 2020-11-16
 */
public class MonitorAdminIndicesStats extends BaseEntity implements  Comparable<MonitorAdminIndicesStats>
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 集群信息 60 45 86 */
    @Excel(name = "集群信息 60 45 86")
    private String clusterinfo;

    /** 监控的方式 */
    private String monitortype;

    /** 索引名称 */
    @Excel(name = "索引名称")
    private String indexname;

    /** 主和all区分 默认=primaries total */
    private String indexprimaries;

    /** 索引量 */
    @Excel(name = "索引量")
    private Long doccount;

    /** 查询时间 */
    @Excel(name = "查询时间")
    private String searchtime;

    /** 索引大小 G */
    @Excel(name = "索引大小 G")
    private Long storesize;

    /** 索引写入总量 */
    @Excel(name = "索引写入总量")
    private Long indextotal;

    /** 索引写入并发量 */
    @Excel(name = "索引写并发量")
    private Long indexcurrent;

    /** 索引Get请求量 */
    private Long gettotal;

    /** 索引Get请求并发量 */
    private Long getcurrent;

    /** 索引Search请求量 */
    @Excel(name = "索引Search请求量")
    private Long querytotal;

    /** 索引Search并发请求量 */
    @Excel(name = "索引读并发量")
    private Long querycurrent;

    /** 索引Search Scroll滚动请求量 */
    private Long scrolltotal;

    /** 索引Search Scroll滚动并发请求量 */
    private Long scrollcurrent;

    /** 索引merges并发请求量 */
    private Long mergescurrent;

    /** 索引merges并发执行doc量 */
    private Long mergersdocs;

    /** 索引refresh刷新量 */
    private Long refreshtotal;

    /** 索引flush刷新量 */
    private Long flushtotal;

    /** 索引query_cache memory_size_in_bytes */
    private Long querycachesize;

    /** 索引query_cache量 */
    private Long querycachetotal;

    /** 索引query_cache命中量 */
    private Long querycachehitcount;

    /** 索引query_cache未命中量 */
    private Long querycachemisscount;

    /** 索引segments段总量 */
    @Excel(name = "索引segments段总量")
    private Long segmentscount;

    /** 索引segments段占用内存的量 */
    private Long segmentsmemorysize;

    /** 索引segments段的词典占用内存的量 */
    private Long segmentsmemorytermssize;

    /** 索引translog事务日志的量 */
    private Long translogsize;

    /** 查询时间 */
    private Long inserttime;

    /** 索引读并发量 */
    private Long queryconcurrency;

    /** 索引写并发量 */
    private Long indexconcurrency;

    /** 索引数量 */
    private Integer esindexcount;

    public Integer getEsindexcount() {
        return esindexcount;
    }

    public void setEsindexcount(Integer esindexcount) {
        this.esindexcount = esindexcount;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setClusterinfo(String clusterinfo) 
    {
        this.clusterinfo = clusterinfo;
    }

    public String getClusterinfo() 
    {
        return clusterinfo;
    }
    public void setMonitortype(String monitortype) 
    {
        this.monitortype = monitortype;
    }

    public String getMonitortype() 
    {
        return monitortype;
    }
    public void setIndexname(String indexname) 
    {
        this.indexname = indexname;
    }

    public String getIndexname() 
    {
        return indexname;
    }
    public void setIndexprimaries(String indexprimaries) 
    {
        this.indexprimaries = indexprimaries;
    }

    public String getIndexprimaries() 
    {
        return indexprimaries;
    }
    public void setDoccount(Long doccount) 
    {
        this.doccount = doccount;
    }

    public Long getDoccount() 
    {
        return doccount;
    }

    public String getSearchtime() {
        return searchtime;
    }

    public void setSearchtime(String searchtime) {
        this.searchtime = searchtime;
    }

    public void setStoresize(Long storesize)
    {
        this.storesize = storesize;
    }

    public Long getStoresize()
    {
        return storesize;
    }
    public void setIndextotal(Long indextotal) 
    {
        this.indextotal = indextotal;
    }

    public Long getIndextotal() 
    {
        return indextotal;
    }
    public void setIndexcurrent(Long indexcurrent) 
    {
        this.indexcurrent = indexcurrent;
    }

    public Long getIndexcurrent() 
    {
        return indexcurrent;
    }
    public void setGettotal(Long gettotal) 
    {
        this.gettotal = gettotal;
    }

    public Long getGettotal() 
    {
        return gettotal;
    }
    public void setGetcurrent(Long getcurrent) 
    {
        this.getcurrent = getcurrent;
    }

    public Long getGetcurrent() 
    {
        return getcurrent;
    }
    public void setQuerytotal(Long querytotal) 
    {
        this.querytotal = querytotal;
    }

    public Long getQuerytotal() 
    {
        return querytotal;
    }
    public void setQuerycurrent(Long querycurrent) 
    {
        this.querycurrent = querycurrent;
    }

    public Long getQuerycurrent() 
    {
        return querycurrent;
    }
    public void setScrolltotal(Long scrolltotal) 
    {
        this.scrolltotal = scrolltotal;
    }

    public Long getScrolltotal() 
    {
        return scrolltotal;
    }
    public void setScrollcurrent(Long scrollcurrent) 
    {
        this.scrollcurrent = scrollcurrent;
    }

    public Long getScrollcurrent() 
    {
        return scrollcurrent;
    }
    public void setMergescurrent(Long mergescurrent) 
    {
        this.mergescurrent = mergescurrent;
    }

    public Long getMergescurrent() 
    {
        return mergescurrent;
    }
    public void setMergersdocs(Long mergersdocs) 
    {
        this.mergersdocs = mergersdocs;
    }

    public Long getMergersdocs() 
    {
        return mergersdocs;
    }
    public void setRefreshtotal(Long refreshtotal) 
    {
        this.refreshtotal = refreshtotal;
    }

    public Long getRefreshtotal() 
    {
        return refreshtotal;
    }
    public void setFlushtotal(Long flushtotal) 
    {
        this.flushtotal = flushtotal;
    }

    public Long getFlushtotal() 
    {
        return flushtotal;
    }
    public void setQuerycachesize(Long querycachesize) 
    {
        this.querycachesize = querycachesize;
    }

    public Long getQuerycachesize() 
    {
        return querycachesize;
    }
    public void setQuerycachetotal(Long querycachetotal) 
    {
        this.querycachetotal = querycachetotal;
    }

    public Long getQuerycachetotal() 
    {
        return querycachetotal;
    }
    public void setQuerycachehitcount(Long querycachehitcount) 
    {
        this.querycachehitcount = querycachehitcount;
    }

    public Long getQuerycachehitcount() 
    {
        return querycachehitcount;
    }
    public void setQuerycachemisscount(Long querycachemisscount) 
    {
        this.querycachemisscount = querycachemisscount;
    }

    public Long getQuerycachemisscount() 
    {
        return querycachemisscount;
    }
    public void setSegmentscount(Long segmentscount) 
    {
        this.segmentscount = segmentscount;
    }

    public Long getSegmentscount() 
    {
        return segmentscount;
    }
    public void setSegmentsmemorysize(Long segmentsmemorysize) 
    {
        this.segmentsmemorysize = segmentsmemorysize;
    }

    public Long getSegmentsmemorysize() 
    {
        return segmentsmemorysize;
    }
    public void setSegmentsmemorytermssize(Long segmentsmemorytermssize) 
    {
        this.segmentsmemorytermssize = segmentsmemorytermssize;
    }

    public Long getSegmentsmemorytermssize() 
    {
        return segmentsmemorytermssize;
    }
    public void setTranslogsize(Long translogsize) 
    {
        this.translogsize = translogsize;
    }

    public Long getTranslogsize() 
    {
        return translogsize;
    }
    public void setInserttime(Long inserttime) 
    {
        this.inserttime = inserttime;
    }

    public Long getInserttime() 
    {
        return inserttime;
    }

    public Long getQueryconcurrency() {
        return queryconcurrency;
    }

    public void setQueryconcurrency(Long queryconcurrency) {
        this.queryconcurrency = queryconcurrency;
    }

    public Long getIndexconcurrency() {
        return indexconcurrency;
    }

    public void setIndexconcurrency(Long indexconcurrency) {
        this.indexconcurrency = indexconcurrency;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("clusterinfo", getClusterinfo())
            .append("monitortype", getMonitortype())
            .append("indexname", getIndexname())
            .append("indexprimaries", getIndexprimaries())
            .append("doccount", getDoccount())
            .append("storesize", getStoresize())
            .append("indextotal", getIndextotal())
            .append("indexcurrent", getIndexcurrent())
            .append("gettotal", getGettotal())
            .append("getcurrent", getGetcurrent())
            .append("querytotal", getQuerytotal())
            .append("querycurrent", getQuerycurrent())
            .append("scrolltotal", getScrolltotal())
            .append("scrollcurrent", getScrollcurrent())
            .append("mergescurrent", getMergescurrent())
            .append("mergersdocs", getMergersdocs())
            .append("refreshtotal", getRefreshtotal())
            .append("flushtotal", getFlushtotal())
            .append("querycachesize", getQuerycachesize())
            .append("querycachetotal", getQuerycachetotal())
            .append("querycachehitcount", getQuerycachehitcount())
            .append("querycachemisscount", getQuerycachemisscount())
            .append("segmentscount", getSegmentscount())
            .append("segmentsmemorysize", getSegmentsmemorysize())
            .append("segmentsmemorytermssize", getSegmentsmemorytermssize())
            .append("translogsize", getTranslogsize())
            .append("inserttime", getInserttime())
            .toString();
    }
    @Override
    public int compareTo(MonitorAdminIndicesStats m) {
        if  (m.getQueryconcurrency()==null){
            return -1;
        }
        if (this.getQueryconcurrency()==null){
            return 1;
        }
        return m.getQueryconcurrency().compareTo(this.getQueryconcurrency());
    }
}
