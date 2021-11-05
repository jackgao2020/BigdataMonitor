package com.xl.project.bigdata.manager;

import com.xl.project.bigdata.util.DateUtil;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.network.InetAddresses;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者： GaoXL
 * @version V1.0
 * @Description: 单例模式连接集群
 * @copyright 公司名称:lexin
 */
@SuppressWarnings("unchecked")
public class ES60Client {

    // 集群名称
    public static final String CLUSTER_NAME = "lse1";
    private static final String IP = "10.155.20.60";
    // 端口
    private static final int PORT = 9300;

    private static Settings settings = Settings.builder().put("cluster.name", CLUSTER_NAME)
            .put("client.transport.sniff", true).build();
    // 创建私有对象
    public static volatile TransportClient client;

//    static {
//
//        synchronized (client){
//            if (client == null) {
//
//                client = new PreBuiltTransportClient(settings)
//                        .addTransportAddress(new InetSocketTransportAddress(InetAddresses.forString(IP), PORT));
//            } else {
//                System.out.println("client重用..");
//            }
//        }
//
//    }

    // 获取连接
    public static synchronized TransportClient getClient(String threadName) {

        try {

            if (client == null) {
                client = new PreBuiltTransportClient(settings)
                        .addTransportAddress(new InetSocketTransportAddress(InetAddresses.forString(IP), PORT));
                System.out.println("running-" + DateUtil.getNowDate() + "-" + threadName + "-ES60Client - 获取client连接成功...");
            } else {
                System.out.println("running-" + DateUtil.getNowDate() + "-" + threadName + "ES60Client - 重复使用中...");
            }

        } catch (Exception e) {

            System.out.println("running" + DateUtil.getNowDate() + "-" + threadName + "TransportClient初期化失败...");
        }

        return client;
    }

    // 获取连接
    public static synchronized TransportClient getClient() {

        try {

            if (client == null) {
                client = new PreBuiltTransportClient(settings)
                        .addTransportAddress(new InetSocketTransportAddress(InetAddresses.forString(IP), PORT));
                System.out.println("ES60Client - 获取client连接成功...");
            } else {
                System.out.println("ES60Client - 重复使用中...");
            }

        } catch (Exception e) {

            System.out.println("TransportClient初期化失败...");

            client.close();
        }

        return client;
    }

    public static boolean bulkIndexs(String index, Map<String, String> jsonMap, boolean isCreatId){


        BulkRequestBuilder bulk = client.prepareBulk();

        for(String id : jsonMap.keySet()){
            bulk.add(client.prepareIndex(index, "info").setSource(jsonMap.get(id), XContentType.JSON));
        }

        bulk.execute().actionGet();


        bulk = null;

        return true;
    }

    // 为集群添加新的节点
    public static synchronized void addNode(String name) {
        try {
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(name), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    // 删除集群中的某个节点
    public static synchronized void removeNode(String name) {
        try {
            client.removeTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(name), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static List<String> searchTags(List<String> ids) {

        List<String> list = new ArrayList<>();

        MultiGetResponse responses = client.prepareMultiGet().add("wo_feedback", "info", ids).get();

        for (MultiGetItemResponse itemResponse : responses) {

            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                list.add(response.getSourceAsString());
            }
        }


        return list;

    }

    public static String SearchWDX(TransportClient client, String index, String type, String phone) {


        if (index == null || type == null || phone == null) {
            return null;
        }
        //来获取查询数据信息
        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, phone);
        GetResponse getResponse = getRequestBuilder.execute().actionGet();
        //这里也有指定的时间获取返回值的信息，如有特殊需求可以

        return getResponse.getSourceAsString();

    }

    public static void batchInsertEs(Map<String, String> map, TransportClient client, String index, String type) {

        BulkRequestBuilder bulk = client.prepareBulk();

        int count = 0;
        int num = 0;

        for (String id : map.keySet()) {

            bulk.add(client.prepareIndex(index, type, id).setSource(map.get(id), XContentType.JSON));

            if (count == 3000) {
                count = 0;
                bulk.execute().actionGet();
            }
            count++;
        }
        if (count > 0)
            bulk.execute().actionGet();
    }

    public static Map<String, String> searchDaiBao() {

        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<String, String>();
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();

        QueryBuilder qb1 = QueryBuilders.termQuery("phoneTime", 20190730);

        QueryBuilder qb2 = QueryBuilders.termQuery("isVip", 9);

        QueryBuilder qb3 = QueryBuilders.termQuery("complaintProbabilityPre", 0);
        bqb.must(qb1);
        bqb.must(qb2);
        bqb.mustNot(qb3);

        SearchResponse sr = client.prepareSearch("phone_service_forecast_log").setTypes("info").setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(bqb).setSize(10000).get();

        SearchHits hits = sr.getHits();

        for (SearchHit hit : hits) {
//            list.add(hit.getSourceAsString());
            map.put(hit.getId(), hit.getSourceAsString());
        }

        return map;

    }

    public static List<String> searchVip(String type) {

        List<String> list = new ArrayList<>();
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();

        QueryBuilder qb1 = QueryBuilders.termQuery("vipType", type);

        QueryBuilder qb2 = QueryBuilders.termQuery("isVip", 1);
        bqb.must(qb1);
        bqb.must(qb2);

        SearchResponse sr = client.prepareSearch("phone_service_forecast").setTypes("info").setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(bqb).setSize(10000).get();

        SearchHits hits = sr.getHits();

        for (SearchHit hit : hits) {
            list.add(hit.getSourceAsString());
        }

        return list;
    }

    public static List<String> searchWorkOrderIds(List<String> ids) {

        List<String> list = new ArrayList<>();
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();

        for (String id : ids) {

            QueryBuilder qb1 = QueryBuilders.termQuery("worklistwoid", id);
            bqb.should(qb1);

        }

        SearchResponse sr = client.prepareSearch("layout_workorder_info").setTypes("info").setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(bqb).setSize(1000).get();

        SearchHits hits = sr.getHits();

        for (SearchHit hit : hits) {
            list.add(hit.getSourceAsString());
        }

        return list;
    }

    // 关闭Client
    public static synchronized void close() {

        try {

            if (client != null) {

                client.close();

            }
        } catch (Exception e) {

            System.out.println("关闭客户端失败...");

            e.printStackTrace();

        }
    }

}
