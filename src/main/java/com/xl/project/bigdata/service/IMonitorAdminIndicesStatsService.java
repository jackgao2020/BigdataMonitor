package com.xl.project.bigdata.service;


import com.xl.project.bigdata.domain.MonitorAdminIndicesStats;

import java.util.List;

/**
 * es索引监控Service接口
 * 
 * @author elf
 * @date 2020-11-16
 */
public interface IMonitorAdminIndicesStatsService 
{
    /**
     * 查询es索引监控
     * 
     * @param id es索引监控ID
     * @return es索引监控
     */
    public MonitorAdminIndicesStats selectMonitorAdminIndicesStatsById(Long id);

    /**
     * 查询es索引监控列表
     * 
     * @param monitorAdminIndicesStats es索引监控
     * @return es索引监控集合
     */
    public List<MonitorAdminIndicesStats> selectMonitorAdminIndicesStatsList(MonitorAdminIndicesStats monitorAdminIndicesStats);

    /**
     * 新增es索引监控
     * 
     * @param monitorAdminIndicesStats es索引监控
     * @return 结果
     */
    public int insertMonitorAdminIndicesStats(MonitorAdminIndicesStats monitorAdminIndicesStats);

    /**
     * 修改es索引监控
     * 
     * @param monitorAdminIndicesStats es索引监控
     * @return 结果
     */
    public int updateMonitorAdminIndicesStats(MonitorAdminIndicesStats monitorAdminIndicesStats);

    /**
     * 批量删除es索引监控
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMonitorAdminIndicesStatsByIds(String ids);

    /**
     * 删除es索引监控信息
     * 
     * @param id es索引监控ID
     * @return 结果
     */
    public int deleteMonitorAdminIndicesStatsById(Long id);
}
