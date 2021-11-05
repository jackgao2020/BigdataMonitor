package com.xl.project.bigdata.mapper;

import com.xl.project.bigdata.domain.MonitorAdminClusterNodesStats;

import java.util.List;

/**
 * 节点监控Mapper接口
 * 
 * @author xl
 * @date 2020-12-09
 */
public interface MonitorAdminClusterNodesStatsMapper 
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
     * 删除节点监控
     * 
     * @param pkId 节点监控ID
     * @return 结果
     */
    public int deleteMonitorAdminClusterNodesStatsById(Long pkId);

    /**
     * 批量删除节点监控
     * 
     * @param pkIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMonitorAdminClusterNodesStatsByIds(String[] pkIds);
}
