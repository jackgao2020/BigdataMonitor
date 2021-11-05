package com.xl.project.bigdata.domain;

import com.xl.framework.aspectj.lang.annotation.Excel;
import com.xl.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ES索引信息表对象 monitor_admin_cluster_state
 * 
 * @author elf
 * @date 2020-11-19
 */
public class MonitorAdminClusterState extends BaseEntity
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

    /** 索引名称 */
    @Excel(name = "索引名称")
    private String providedName;

    /** 索引创建时间 */
    private String creationDate;

    /** 索引唯一UUID */
    private String uuid;

    /** 索引分片数量 */
    @Excel(name = "索引分片数量")
    private String numberOfShards;

    /** 索引副本数量 */
    @Excel(name = "索引副本数量")
    private String numberOfReplicas;

    /** 索引刷新时间 */
    @Excel(name = "索引刷新时间")
    private String refreshInterval;

    /** 查询时间 */
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
    public void setProvidedName(String providedName) 
    {
        this.providedName = providedName;
    }

    public String getProvidedName() 
    {
        return providedName;
    }
    public void setCreationDate(String creationDate) 
    {
        this.creationDate = creationDate;
    }

    public String getCreationDate() 
    {
        return creationDate;
    }
    public void setUuid(String uuid) 
    {
        this.uuid = uuid;
    }

    public String getUuid() 
    {
        return uuid;
    }
    public void setNumberOfShards(String numberOfShards) 
    {
        this.numberOfShards = numberOfShards;
    }

    public String getNumberOfShards() 
    {
        return numberOfShards;
    }
    public void setNumberOfReplicas(String numberOfReplicas) 
    {
        this.numberOfReplicas = numberOfReplicas;
    }

    public String getNumberOfReplicas() 
    {
        return numberOfReplicas;
    }
    public void setRefreshInterval(String refreshInterval) 
    {
        this.refreshInterval = refreshInterval;
    }

    public String getRefreshInterval() 
    {
        return refreshInterval;
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
            .append("providedName", getProvidedName())
            .append("creationDate", getCreationDate())
            .append("uuid", getUuid())
            .append("numberOfShards", getNumberOfShards())
            .append("numberOfReplicas", getNumberOfReplicas())
            .append("refreshInterval", getRefreshInterval())
            .append("insertTime", getInsertTime())
            .toString();
    }
}
