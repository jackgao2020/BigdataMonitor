package com.xl.project.bigdata.bean;

/**
 * @program: IAEngine
 *
 * @description: http://10.155.20.60:9200/_cluster/state?human&pretty
 *
 * @author: XL.Gao
 *
 * @create: 2020-11-10 18:05
 **/
public class MonitorClusterStateBean {

    // 集群信息 60 45 86
    String clusterInfo = "";

    // 集群名称 lse1
    String clusterName = "";

    // 索引名称
    String providedName = "";

    // 索引创建时间
    String creationDate = "";

    // 索引唯一UUID
    String uuid = "";

    // 索引分片数量
    String numberOfShards = "";

    // 索引副本数量
    String numberOfReplicas = "";

    // 索引刷新时间
    String refreshInterval = "";

    // 查询时间
    long insertTime = 0;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MonitorClusterStateBean{");
        sb.append("clusterInfo='").append(clusterInfo).append('\'');
        sb.append(", clusterName='").append(clusterName).append('\'');
        sb.append(", providedName='").append(providedName).append('\'');
        sb.append(", creationDate='").append(creationDate).append('\'');
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append(", numberOfShards='").append(numberOfShards).append('\'');
        sb.append(", numberOfReplicas='").append(numberOfReplicas).append('\'');
        sb.append(", refreshInterval='").append(refreshInterval).append('\'');
        sb.append(", insertTime=").append(insertTime);
        sb.append('}');
        return sb.toString();
    }

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

    public String getProvidedName() {
        return providedName;
    }

    public void setProvidedName(String providedName) {
        this.providedName = providedName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNumberOfShards() {
        return numberOfShards;
    }

    public void setNumberOfShards(String numberOfShards) {
        this.numberOfShards = numberOfShards;
    }

    public String getNumberOfReplicas() {
        return numberOfReplicas;
    }

    public void setNumberOfReplicas(String numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
    }

    public String getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(String refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }
}
