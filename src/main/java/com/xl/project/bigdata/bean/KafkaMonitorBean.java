package com.xl.project.bigdata.bean;

/**
 * Copyright (C), 2015-2019, 乐信云科技有限公司
 * FileName: CustomerApp
 * Author:   GaoXL
 * Date:     2021/4/13 11:04
 * Description: kafka监控的指标bean
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * GaoXL             2021/4/13 11:04..V1.0.............大数据
 */
public class KafkaMonitorBean {

    private String id = "";

    private String cluster = "";

    private String topic = "";

    private int partitionsSize = 0;

    private int brokersSize = 0;

    private long perMessageSize = 0;

    private long allMessageSize = 0;

    private long curMessageSize = 0;

    private String latestOffset;

    private long maxLatestOffset = 0;

    private long avgLatestOffset = 0;

    private long minLatestOffset = 0;

    private String earliestOffset;

    private long maxEarliestOffset = 0;

    private long avgEarliestOffset = 0;

    private long minEarliestOffset = 0;

    private long insertDate = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatestOffset() {
        return latestOffset;
    }

    public void setLatestOffset(String latestOffset) {
        this.latestOffset = latestOffset;
    }

    public String getEarliestOffset() {
        return earliestOffset;
    }

    public void setEarliestOffset(String earliestOffset) {
        this.earliestOffset = earliestOffset;
    }

    public long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(long insertDate) {
        this.insertDate = insertDate;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPartitionsSize() {
        return partitionsSize;
    }

    public void setPartitionsSize(int partitionsSize) {
        this.partitionsSize = partitionsSize;
    }

    public int getBrokersSize() {
        return brokersSize;
    }

    public void setBrokersSize(int brokersSize) {
        this.brokersSize = brokersSize;
    }

    public long getPerMessageSize() {
        return perMessageSize;
    }

    public void setPerMessageSize(long perMessageSize) {
        this.perMessageSize = perMessageSize;
    }

    public long getAllMessageSize() {
        return allMessageSize;
    }

    public void setAllMessageSize(long allMessageSize) {
        this.allMessageSize = allMessageSize;
    }

    public long getCurMessageSize() {
        return curMessageSize;
    }

    public void setCurMessageSize(long curMessageSize) {
        this.curMessageSize = curMessageSize;
    }

    public long getMaxLatestOffset() {
        return maxLatestOffset;
    }

    public void setMaxLatestOffset(long maxLatestOffset) {
        this.maxLatestOffset = maxLatestOffset;
    }

    public long getAvgLatestOffset() {
        return avgLatestOffset;
    }

    public void setAvgLatestOffset(long avgLatestOffset) {
        this.avgLatestOffset = avgLatestOffset;
    }

    public long getMinLatestOffset() {
        return minLatestOffset;
    }

    public void setMinLatestOffset(long minLatestOffset) {
        this.minLatestOffset = minLatestOffset;
    }

    public long getMaxEarliestOffset() {
        return maxEarliestOffset;
    }

    public void setMaxEarliestOffset(long maxEarliestOffset) {
        this.maxEarliestOffset = maxEarliestOffset;
    }

    public long getAvgEarliestOffset() {
        return avgEarliestOffset;
    }

    public void setAvgEarliestOffset(long avgEarliestOffset) {
        this.avgEarliestOffset = avgEarliestOffset;
    }

    public long getMinEarliestOffset() {
        return minEarliestOffset;
    }

    public void setMinEarliestOffset(long minEarliestOffset) {
        this.minEarliestOffset = minEarliestOffset;
    }
}
