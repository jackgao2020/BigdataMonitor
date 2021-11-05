package com.xl.project.bigdata.service;

import com.xl.project.bigdata.domain.MonitorAdminClusterNodesStats;

import java.util.List;

/**
 * 节点监控Service接口
 * 
 * @author xl
 * @date 2020-12-09
 */
public interface IMonitorAdminClusterNodesStatsService 
{
    /**
     * 查询节点监控
     * 
     * @param pkId 节点监控ID
     * @return 节点监控
     */
    public MonitorAdminClusterNodesStats selectMonitorAdminClusterNodesStatsById(Long pkId);

    /**
     * 查询节点监控列表
     * 
     * @param monitorAdminClusterNodesStats 节点监控
     * @return 节点监控集合
     */
    public List<MonitorAdminClusterNodesStats> selectMonitorAdminClusterNodesStatsList(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats);

    /**
     * 新增节点监控
     * 
     * @param monitorAdminClusterNodesStats 节点监控
     * @return 结果
     */
    public int insertMonitorAdminClusterNodesStats(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats);

    /**
     * 修改节点监控
     * 
     * @param monitorAdminClusterNodesStats 节点监控
     * @return 结果
     */
    public int updateMonitorAdminClusterNodesStats(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats);

    /**
     * 批量删除节点监控
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMonitorAdminClusterNodesStatsByIds(String ids);

    /**
     * 删除节点监控信息
     * 
     * @param pkId 节点监控ID
     * @return 结果
     */
    public int deleteMonitorAdminClusterNodesStatsById(Long pkId);
}
