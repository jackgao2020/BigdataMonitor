package com.xl.project.bigdata.util.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @program: 使用curator框架开发Zookeeper程序
 * @description:
 * @author: XL.Gao
 * @create: 2021-05-11 10:07
 **/
public class ZookeeperTools {

    private static CuratorFramework client = null;

    private static volatile ZookeeperTools instance = null;

    public static void main(String[] args) {

    }

    /*
     * 功能描述: 采用内部类的方式实现单例，解决双重锁带来的性能消耗
     *
     * 这里又要提出一种新的模式——Initialization on Demand Holder.
     * 这种方法使用内部类来做到延迟加载对象，在初始化这个内部类的时候，
     * JLS(Java Language Sepcification)会保证这个类的线程安全。
     * 这种写法最大的美在于，完全使用了Java虚拟机的机制进行同步保证，没有一个同步的关键字
     * 
     * @Param: 
     * @Return: 
     * @Author: XL.Gao
     * @Date: 2021/5/11 10:30
     */
    private static class ZookeeperToolsHolder{

        public final static ZookeeperTools instanceHolder = new ZookeeperTools();

    }

    public ZookeeperTools getInstanceHolder(String servers){

        this.getCuratorFramework(servers);

        return ZookeeperToolsHolder.instanceHolder;
    }

    /*
     * 功能描述: 采用双重锁机制，保证线程安全，
     *           加入volatile关键词，volatile禁止了一些重排序
     *
     *           上面的写法一方面实现了Lazy-Load，另一个方面也做到了并发度很好的线程安全，
     *           一切看上很完美。这是，面试官可能会对你的回答满意的点点头。
     *           但是，你此时提出说，其实这种写法还是有问题的！！
     *           问题在哪里？假设线程A执行到了第9行，它判断对象为空，
     *           于是线程A执行到第12行去初始化这个对象，但初始化是需要耗费时间的，
     *           但是这个对象的地址其实已经存在了。此时线程B也执行到了第九行
     *           ，它判断不为空，于是直接跳到15行得到了这个对象。但是，这个对象还没有被完整的初始化！
     *           得到一个没有初始化完全的对象有什么用！！关于这个Double-Checked Lock的讨论有很多，
     *           目前公认这是一个Anti-Pattern，不推荐使用！所以当你的面试官听到你的这番答复，他会不会被Hold住呢？
     *
     *           最好采用内部类的方式
     *
     * @Param: []
     * @Return: com.lexin.engine.zookeeper.ZookeeperTools
     * @Author: XL.Gao
     * @Date: 2021/5/11 10:25
     */
    public static ZookeeperTools getInstance() {

        if (instance == null) {
            synchronized (ZookeeperTools.class) {
                if (instance == null) {
                    instance = new ZookeeperTools();
                }
            }
        } else {
            System.out.println("ZookeeperTools复用中...");
        }

        return instance;
    }

    /*
     * 功能描述: <br>
     *
     * @Param: connectionString 服务器列表，格式host1:2181,host2:2181
     * @Param: retryPolicy 重试策略,内建有四种重试策略,也可以自行实现RetryPolicy接口
     * @Param: sessionTimeoutMs 会话超时时间，单位毫秒，默认60000ms
     * @Param: connectionTimeoutMs 连接创建超时时间，单位毫秒，默认60000ms
     * @Return: org.apache.curator.framework.CuratorFramework
     * @Author: XL.Gao
     * @Date: 2021/5/11 10:49
     */
    public CuratorFramework getCuratorFramework(String servers) {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(
                servers,
                5000,
                3000,
                retryPolicy);

        client.start();

        return client;
    }

    public void closeClient(){
        client.close();
    }

    public String createPath(String path){

        try {
            Stat stat = client.checkExists().forPath(path);

            if(stat == null){
                String s = client.create().forPath(path);

                return s;
            } else {
                System.out.println("创建节点失败，节点已经存在！"+path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

    public boolean createPathAndData(String path, String data){
        try {
            String s = client.create().forPath(path, data.getBytes());

            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public Stat setData(String path, String data){

        try {
            Stat stat = client.setData().forPath(path, data.getBytes());
            return stat;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }



    public String getData(String path){
        try {
            byte[] paths = client.getData().forPath(path);

            return new String(paths);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public List<String> getChildrenPath(String path){
        try {
            List<String> paths = client.getChildren().forPath(path);
            return paths;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
