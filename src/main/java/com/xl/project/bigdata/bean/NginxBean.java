package com.xl.project.bigdata.bean;

/**
 * @program: IAEngine
 * @description:
 * @author: XL.Gao
 * @create: 2021-05-27 09:50
 **/
public class NginxBean {

    private String typeName = "";

    private int activeConnections = 0;

    private int waiting = 0;

    private long insertTime = 0;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getActiveConnections() {
        return activeConnections;
    }

    public void setActiveConnections(int activeConnections) {
        this.activeConnections = activeConnections;
    }

    public int getWaiting() {
        return waiting;
    }

    public void setWaiting(int waiting) {
        this.waiting = waiting;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "NginxBean{" +
                "typeName='" + typeName + '\'' +
                ", activeConnections=" + activeConnections +
                ", waiting=" + waiting +
                ", insertTime=" + insertTime +
                '}';
    }
}
