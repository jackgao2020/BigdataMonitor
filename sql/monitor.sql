/*
Navicat MySQL Data Transfer

Source Server         : Mysql-审图-10.155.20.61
Source Server Version : 50564
Source Host           : 10.155.20.61:3306
Source Database       : monitor

Target Server Type    : MYSQL
Target Server Version : 50564
File Encoding         : 65001

Date: 2020-11-16 11:18:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `monitor_admin_indices_stats`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_admin_indices_stats`;
CREATE TABLE `monitor_admin_indices_stats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clusterInfo` varchar(255) NOT NULL COMMENT '集群信息 60 45 86',
  `monitorType` varchar(255) DEFAULT NULL COMMENT '监控的方式',
  `indexName` varchar(255) DEFAULT NULL COMMENT '索引名称',
  `indexPrimaries` varchar(255) DEFAULT NULL COMMENT '主和all区分 默认=primaries total',
  `docCount` bigint(20) DEFAULT NULL COMMENT '索引量',
  `storeSize` bigint(20) DEFAULT NULL COMMENT '索引大小 G',
  `indexTotal` bigint(20) DEFAULT NULL COMMENT '索引写入总量',
  `indexCurrent` bigint(20) DEFAULT NULL COMMENT '索引写入并发量',
  `getTotal` bigint(20) DEFAULT NULL COMMENT '索引Get请求量',
  `getCurrent` bigint(20) DEFAULT NULL COMMENT '索引Get请求并发量',
  `queryTotal` bigint(20) DEFAULT NULL COMMENT '索引Search请求量',
  `queryCurrent` bigint(20) DEFAULT NULL COMMENT '索引Search并发请求量',
  `scrollTotal` bigint(20) DEFAULT NULL COMMENT '索引Search Scroll滚动请求量',
  `scrollCurrent` bigint(20) DEFAULT NULL COMMENT '索引Search Scroll滚动并发请求量',
  `mergesCurrent` bigint(20) DEFAULT NULL COMMENT '索引merges并发请求量',
  `mergersDocs` bigint(20) DEFAULT NULL COMMENT '索引merges并发执行doc量',
  `refreshTotal` bigint(20) DEFAULT NULL COMMENT '索引refresh刷新量',
  `flushTotal` bigint(20) DEFAULT NULL COMMENT '索引flush刷新量',
  `queryCacheSize` bigint(20) DEFAULT NULL COMMENT '索引query_cache memory_size_in_bytes',
  `queryCacheTotal` bigint(20) DEFAULT NULL COMMENT '索引query_cache量',
  `queryCacheHitCount` bigint(20) DEFAULT NULL COMMENT '索引query_cache命中量',
  `queryCacheMissCount` bigint(20) DEFAULT NULL COMMENT '索引query_cache未命中量',
  `segmentsCount` bigint(20) DEFAULT NULL COMMENT '索引segments段总量',
  `segmentsMemorySize` bigint(20) DEFAULT NULL COMMENT '索引segments段占用内存的量',
  `segmentsMemoryTermsSize` bigint(20) DEFAULT NULL COMMENT '索引segments段的词典占用内存的量',
  `translogSize` bigint(20) DEFAULT NULL COMMENT '索引translog事务日志的量',
  `insertTime` bigint(20) DEFAULT NULL COMMENT '查询时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2041 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of monitor_admin_indices_stats
-- ----------------------------
INSERT INTO `monitor_admin_indices_stats` VALUES ('1', '60', 'stats', 'all', 'total', '4578931844', '5453372580773', '15975003584', '1', '2288489631', '0', '9601416224', '2', '862099370', '10', '1', '1737', '6931273685', '1516662', '447824', '15970831', '3065733270', '22625988585', '12012', '12040066012', '10235110084', '29523937842', '20201116092427');
INSERT INTO `monitor_admin_indices_stats` VALUES ('2', '60', 'stats', 'all', 'primaries', '2333890402', '2773021552852', '9257204625', '0', '1421652556', '0', '5023247064', '2', '459509017', '7', '0', '0', '3636094319', '816650', '221133', '8221507', '1615811811', '11784127973', '6458', '6135206147', '5216803964', '14779057388', '20201116092427');
