package com.xl.project.bigdata.manager;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.*;

/**
 * @author 01502636
 */
public class HbaseClient {

	private static Configuration conf = null;
	private static Connection conn = null;

	public static void main(String[] args) {

		System.out.println(Class.class.getClass().getResource("/").getPath());
		System.out.println(System.getProperty("user.dir"));
//		System.exit(0);
		// TODO Auto-generated method stub
		try {
		    // lx-cdh-04,lx-cdh-05,lx-cdh-06"
            // hicslave1,hicslave2,hicslave3
			init("lx-cs-04,lx-cs-05,lx-cs-06");


//			Put p = new Put(Bytes.toBytes("123456789"));
//			p.addColumn(Bytes.toBytes("t"), Bytes.toBytes("a"), Bytes.toBytes("12"));
//
//			htable.put(p);
//			htable.flushCommits();
//            getResultScann("di_wdx", "00000E0550C59454830B8781FC16C5F7_(","00000E0550C59454830B8781FC16C5F7_}");

			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 初始化连接
	 * 
	 * @throws IOException
	 */
	public static void init(String zk) throws IOException {
		try {
			if (conf == null) {
				conf = HBaseConfiguration.create();
				conf.set("hbase.zookeeper.quorum", zk);
				conf.set("zookeeper.znode.parent", "/hbase");
			} else {
				System.out.println("HbaseClient conf重复使用中");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



	/**
	 * 获得链接
	 * 
	 * @return
	 */
	public static synchronized Connection getConnection() {

		try {
			if (conn == null || conn.isClosed()) {
				conn = ConnectionFactory.createConnection(conf);
				System.out.println("HbaseClient - 获取hbase连接成功...");
			} else {
				System.out.println("HbaseClient - 重复使用hbase连接...");
			}
			// System.out.println("---------- " + conn.hashCode());
		} catch (IOException e) {
			System.out.println("HbaseClient - 获取hbase连接失败...");
			e.printStackTrace();
		}

		return conn;

	}

	/**
	 * 关闭连接
	 *
	 * @param conn
	 * @throws IOException
	 */
	public static void closeConnect(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("");
				e.printStackTrace();
			}
		}
	}

    /**
     * 关闭连接
     * @throws IOException
     */
    public static void closeConnect() {
        if (null != conn) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("");
                e.printStackTrace();
            }
        }
    }
}
