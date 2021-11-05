package com.xl.project.system.kafka.domain;

import com.xl.framework.aspectj.lang.annotation.Excel;
import com.xl.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 【请填写功能名称】对象 monitor_kafka
 * 
 * @author xl
 * @date 2021-04-27
 */
public class MonitorKafka extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 集群名称 */
    @Excel(name = "集群名称")
    private String cluster;

    /** topic名称 */
    @Excel(name = "topic名称")
    private String topic;

    /** 分区的数量 */
    @Excel(name = "分区的数量")
    private Long partitionssize;

    /** broker的数量 */
    @Excel(name = "broker的数量")
    private Long brokerssize;

    /** 每秒接收信息量 */
    @Excel(name = "每秒接收信息量")
    private Long permessagesize;

    /** kafka接收消息的总量 */
    @Excel(name = "kafka接收消息的总量")
    private Long allmessagesize;

    /** kafka目前保存消息的总量-early */
    @Excel(name = "kafka目前保存消息的总量-early")
    private Long curmessagesize;

    /** 最近一次各个分区的offset */
    @Excel(name = "最近一次各个分区的offset")
    private String latestoffset;

    /** 最近一次接收消息最大分区的量 */
    @Excel(name = "最近一次接收消息最大分区的量")
    private Long maxlatestoffset;

    /** 最近一次消费信息的平均值 */
    @Excel(name = "最近一次消费信息的平均值")
    private Long avglatestoffset;

    /** 最近一次接收消息最少的分区的量 */
    @Excel(name = "最近一次接收消息最少的分区的量")
    private Long minlatestoffset;

    /** kafka最早保存的各个分区的offset */
    @Excel(name = "kafka最早保存的各个分区的offset")
    private String earliestoffset;

    /** kafka最早的最大分区的量 */
    @Excel(name = "kafka最早的最大分区的量")
    private Long maxearliestoffset;

    /** kafka最早的分区的平均量 */
    @Excel(name = "kafka最早的分区的平均量")
    private Long avgearliestoffset;

    /** kafka最早分区的最小消息量 */
    @Excel(name = "kafka最早分区的最小消息量")
    private Long minearliestoffset;

    /** 入库时间 */
    @Excel(name = "入库时间")
    private String insertdate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCluster(String cluster) 
    {
        this.cluster = cluster;
    }

    public String getCluster() 
    {
        return cluster;
    }
    public void setTopic(String topic) 
    {
        this.topic = topic;
    }

    public String getTopic() 
    {
        return topic;
    }
    public void setPartitionssize(Long partitionssize) 
    {
        this.partitionssize = partitionssize;
    }

    public Long getPartitionssize() 
    {
        return partitionssize;
    }
    public void setBrokerssize(Long brokerssize) 
    {
        this.brokerssize = brokerssize;
    }

    public Long getBrokerssize() 
    {
        return brokerssize;
    }
    public void setPermessagesize(Long permessagesize) 
    {
        this.permessagesize = permessagesize;
    }

    public Long getPermessagesize() 
    {
        return permessagesize;
    }
    public void setAllmessagesize(Long allmessagesize) 
    {
        this.allmessagesize = allmessagesize;
    }

    public Long getAllmessagesize() 
    {
        return allmessagesize;
    }
    public void setCurmessagesize(Long curmessagesize) 
    {
        this.curmessagesize = curmessagesize;
    }

    public Long getCurmessagesize() 
    {
        return curmessagesize;
    }
    public void setLatestoffset(String latestoffset) 
    {
        this.latestoffset = latestoffset;
    }

    public String getLatestoffset() 
    {
        return latestoffset;
    }
    public void setMaxlatestoffset(Long maxlatestoffset) 
    {
        this.maxlatestoffset = maxlatestoffset;
    }

    public Long getMaxlatestoffset() 
    {
        return maxlatestoffset;
    }
    public void setAvglatestoffset(Long avglatestoffset) 
    {
        this.avglatestoffset = avglatestoffset;
    }

    public Long getAvglatestoffset() 
    {
        return avglatestoffset;
    }
    public void setMinlatestoffset(Long minlatestoffset) 
    {
        this.minlatestoffset = minlatestoffset;
    }

    public Long getMinlatestoffset() 
    {
        return minlatestoffset;
    }
    public void setEarliestoffset(String earliestoffset) 
    {
        this.earliestoffset = earliestoffset;
    }

    public String getEarliestoffset() 
    {
        return earliestoffset;
    }
    public void setMaxearliestoffset(Long maxearliestoffset) 
    {
        this.maxearliestoffset = maxearliestoffset;
    }

    public Long getMaxearliestoffset() 
    {
        return maxearliestoffset;
    }
    public void setAvgearliestoffset(Long avgearliestoffset) 
    {
        this.avgearliestoffset = avgearliestoffset;
    }

    public Long getAvgearliestoffset() 
    {
        return avgearliestoffset;
    }
    public void setMinearliestoffset(Long minearliestoffset) 
    {
        this.minearliestoffset = minearliestoffset;
    }

    public Long getMinearliestoffset() 
    {
        return minearliestoffset;
    }

    public String getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(String insertdate) {
        this.insertdate = insertdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cluster", getCluster())
            .append("topic", getTopic())
            .append("partitionssize", getPartitionssize())
            .append("brokerssize", getBrokerssize())
            .append("permessagesize", getPermessagesize())
            .append("allmessagesize", getAllmessagesize())
            .append("curmessagesize", getCurmessagesize())
            .append("latestoffset", getLatestoffset())
            .append("maxlatestoffset", getMaxlatestoffset())
            .append("avglatestoffset", getAvglatestoffset())
            .append("minlatestoffset", getMinlatestoffset())
            .append("earliestoffset", getEarliestoffset())
            .append("maxearliestoffset", getMaxearliestoffset())
            .append("avgearliestoffset", getAvgearliestoffset())
            .append("minearliestoffset", getMinearliestoffset())
            .append("insertdate", getInsertdate())
            .toString();
    }
}
