package com.xl.project.bigdata.service.impl;

import java.util.List;

import com.xl.common.utils.text.Convert;
import com.xl.project.bigdata.domain.MonitorAdminClusterNodesStats;
import com.xl.project.bigdata.mapper.MonitorAdminClusterNodesStatsMapper;
import com.xl.project.bigdata.service.IMonitorAdminClusterNodesStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 节点监控Service业务层处理
 * 
 * @author xl
 * @date 2020-12-09
 */
@Service
public class MonitorAdminClusterNodesStatsServiceImpl implements IMonitorAdminClusterNodesStatsService
{
    @Autowired
    private MonitorAdminClusterNodesStatsMapper monitorAdminClusterNodesStatsMapper;

    /**
     * 查询节点监控
     * 
     * @param pkId 节点监控ID
     * @return 节点监控
     */
    @Override
    public MonitorAdminClusterNodesStats selectMonitorAdminClusterNodesStatsById(Long pkId)
    {
        return monitorAdminClusterNodesStatsMapper.selectMonitorAdminClusterNodesStatsById(pkId);
    }

    /**
     * 查询节点监控列表
     * 
     * @param monitorAdminClusterNodesStats 节点监控
     * @return 节点监控
     */
    @Override
    public List<MonitorAdminClusterNodesStats> selectMonitorAdminClusterNodesStatsList(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats)
    {
        return monitorAdminClusterNodesStatsMapper.selectMonitorAdminClusterNodesStatsList(monitorAdminClusterNodesStats);
    }

    /**
     * 新增节点监控
     * 
     * @param monitorAdminClusterNodesStats 节点监控
     * @return 结果
     */
    @Override
    public int insertMonitorAdminClusterNodesStats(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats)
    {
        return monitorAdminClusterNodesStatsMapper.insertMonitorAdminClusterNodesStats(monitorAdminClusterNodesStats);
    }

    /**
     * 修改节点监控
     * 
     * @param monitorAdminClusterNodesStats 节点监控
     * @return 结果
     */
    @Override
    public int updateMonitorAdminClusterNodesStats(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats)
    {
        return monitorAdminClusterNodesStatsMapper.updateMonitorAdminClusterNodesStats(monitorAdminClusterNodesStats);
    }

    /**
     * 删除节点监控对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMonitorAdminClusterNodesStatsByIds(String ids)
    {
        return monitorAdminClusterNodesStatsMapper.deleteMonitorAdminClusterNodesStatsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除节点监控信息
     * 
     * @param pkId 节点监控ID
     * @return 结果
     */
    @Override
    public int deleteMonitorAdminClusterNodesStatsById(Long pkId)
    {
        return monitorAdminClusterNodesStatsMapper.deleteMonitorAdminClusterNodesStatsById(pkId);
    }
}
