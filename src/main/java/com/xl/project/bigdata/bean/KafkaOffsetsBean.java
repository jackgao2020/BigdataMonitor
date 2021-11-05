package com.xl.project.bigdata.bean;

/**
 * Copyright (C), 2015-2019, 乐信云科技有限公司
 * FileName: CustomerApp
 * Author:   GaoXL
 * Date:     2021/6/9 10:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * GaoXL             2021/6/9 10:05..V1.0.............大数据
 */
public class KafkaOffsetsBean {

    private String group = "";

    private String topic = "";

    private String partition = "";

    private long offset = 0;

    private long logSize = 0;

    private long createTime = 0;

    private long lag = 0;

    @Override
    public String toString() {
        return "KafkaOffsetsBean{" +
                "group='" + group + '\'' +
                ", topic='" + topic + '\'' +
                ", partition=" + partition +
                ", offset=" + offset +
                ", logSize=" + logSize +
                ", createTime=" + createTime +
                ", lag=" + lag +
                '}';
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getLogSize() {
        return logSize;
    }

    public void setLogSize(long logSize) {
        this.logSize = logSize;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLag() {
        return lag;
    }

    public void setLag(long lag) {
        this.lag = lag;
    }
}
