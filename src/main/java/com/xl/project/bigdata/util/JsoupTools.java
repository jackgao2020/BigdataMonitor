package com.xl.project.bigdata.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xl.project.bigdata.bean.KafkaOffsetsBean;
import com.xl.project.bigdata.bean.NginxBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @program: IAEngine
 * @description:
 * @author: XL.Gao
 * @create: 2021-05-27 09:40
 **/
public class JsoupTools {


    public static void main(String args[]) throws Exception {
        startMonitorNginx();
//        System.out.println(s2);
    }

    public static void startKafkaOffsets() {

        HttpClient client = HttpClients.createDefault();

        String url = "http://10.155.20.100:8001/group/NewStreamingCusCombin0719001";
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");

        CloseableHttpResponse response2 = null;

        HttpResponse execute = null;
        try {
            execute = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity2 = execute.getEntity();
        String htmlJson = null;
        try {
            htmlJson = EntityUtils.toString(entity2, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonAlarmMsg = JSON.parseObject(htmlJson);
        JSONArray offsets1 = jsonAlarmMsg.getJSONArray("offsets");

        java.util.List<KafkaOffsetsBean> list = new java.util.ArrayList<>();

        String groupAll = "";
        String topicAll = "";
        String partitionAll = "";
        Long offsetAll = 0l;
        Long logSizeAll = 0l;
        Long modifiedAll = 0l;
        long lagAll = 0l;

        for (int i = 0; i < offsets1.size(); i++) {
            JSONObject jsonObject = (JSONObject) offsets1.get(i);

            String group = jsonObject.getString("group");
            String topic = jsonObject.getString("topic");
            String partition = jsonObject.getString("partition");
            Long offset = jsonObject.getLong("offset");
            Long logSize = jsonObject.getLong("logSize");
            Long modified = jsonObject.getLong("modified");

            if ("pre_lecc_customer".equals(topic)) {
                long lag = logSize - offset;
                KafkaOffsetsBean bean = new KafkaOffsetsBean();
                bean.setGroup(group);
                bean.setTopic(topic);
                bean.setPartition(partition);
                bean.setOffset(offset);
                bean.setLogSize(logSize);
                bean.setCreateTime(modified);
                bean.setLag(lag);

                groupAll = group;
                topicAll = topic;
                partitionAll = "all";
                offsetAll = offsetAll + offset;
                logSizeAll = logSizeAll + logSize;
                modifiedAll = modified;
                lagAll = lagAll + lag;

                list.add(bean);
            }

        }

        KafkaOffsetsBean bean = new KafkaOffsetsBean();
        bean.setGroup(groupAll);
        bean.setTopic(topicAll);
        bean.setPartition(partitionAll);
        bean.setOffset(offsetAll);
        bean.setLogSize(logSizeAll);
        bean.setCreateTime(modifiedAll);
        bean.setLag(lagAll);
        list.add(bean);

        list.forEach(b -> {
            System.out.println(b.toString());
        });
    }

    public static NginxBean startMonitorNginx() {

        NginxBean nginxBean = new NginxBean();
        try {
            nginxBean.setTypeName("Nginx-20.10");

            Document document = Jsoup.connect("http://10.155.20.10/Nginxstatus").get();
            String text = document.body().text();
            String[] split = text.split(" ");

            if (split != null && split.length == 16) {
                nginxBean.setActiveConnections(new Integer(split[2]));
                nginxBean.setWaiting(new Integer(split[15]));
            }

            System.out.println(
                    nginxBean.toString()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nginxBean;
    }

    public static boolean startMonitorKafka() {
        try {

            NginxBean nginxBean = new NginxBean();
            nginxBean.setTypeName("Nginx-20.10");

            Document document = Jsoup.connect("http://10.155.20.100:8001/#/group/NewStreamingCusCombin0719001").get();


//            Elements elementsByClass = document.getElementsByClass("topic-row");

            System.out.println(document.select("tbody"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
