package com.xl.project.bigdata.bean;

/**
 * @program: IAEngine
 * @description:
 * @author: XL.Gao
 * @create: 2021-05-31 16:37
 **/
public class HbaseMonitorBean {

    // 集群名称
    private String cluster = "";

    // Hbase
    private String typeName = "";

    // regionServer的名称
    private String serverName = "";

    // regionServer的region个数
    private int numberOfRegions = 0;

    // 读请求量
    private long readRequestsCount = 0;

    // 写请求量
    private long writeRequestsCount = 0;

    // 每秒请求量
    private long requestsPerSecond = 0;

    private  long insertDate = 0;

    public long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(long insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HbaseMonitorBean{");
        sb.append("cluster='").append(cluster).append('\'');
        sb.append(", typeName='").append(typeName).append('\'');
        sb.append(", serverName='").append(serverName).append('\'');
        sb.append(", numberOfRegions=").append(numberOfRegions);
        sb.append(", readRequestsCount=").append(readRequestsCount);
        sb.append(", writeRequestsCount=").append(writeRequestsCount);
        sb.append(", requestsPerSecond=").append(requestsPerSecond);
        sb.append('}');
        return sb.toString();
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getNumberOfRegions() {
        return numberOfRegions;
    }

    public void setNumberOfRegions(int numberOfRegions) {
        this.numberOfRegions = numberOfRegions;
    }

    public long getReadRequestsCount() {
        return readRequestsCount;
    }

    public void setReadRequestsCount(long readRequestsCount) {
        this.readRequestsCount = readRequestsCount;
    }

    public long getWriteRequestsCount() {
        return writeRequestsCount;
    }

    public void setWriteRequestsCount(long writeRequestsCount) {
        this.writeRequestsCount = writeRequestsCount;
    }

    public long getRequestsPerSecond() {
        return requestsPerSecond;
    }

    public void setRequestsPerSecond(long requestsPerSecond) {
        this.requestsPerSecond = requestsPerSecond;
    }
}
