package com.xl.project.bigdata.service.impl;

import java.util.List;

import com.xl.common.utils.text.Convert;
import com.xl.project.bigdata.domain.MonitorAdminClusterState;
import com.xl.project.bigdata.mapper.MonitorAdminClusterStateMapper;
import com.xl.project.bigdata.service.IMonitorAdminClusterStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ES索引信息表Service业务层处理
 * 
 * @author elf
 * @date 2020-11-19
 */
@Service
public class MonitorAdminClusterStateServiceImpl implements IMonitorAdminClusterStateService
{
    @Autowired
    private MonitorAdminClusterStateMapper monitorAdminClusterStateMapper;

    /**
     * 查询ES索引信息表
     * 
     * @param pkId ES索引信息表ID
     * @return ES索引信息表
     */
    @Override
    public MonitorAdminClusterState selectMonitorAdminClusterStateById(Long pkId)
    {
        return monitorAdminClusterStateMapper.selectMonitorAdminClusterStateById(pkId);
    }

    /**
     * 查询ES索引信息表列表
     * 
     * @param monitorAdminClusterState ES索引信息表
     * @return ES索引信息表
     */
    @Override
    public List<MonitorAdminClusterState> selectMonitorAdminClusterStateList(MonitorAdminClusterState monitorAdminClusterState)
    {
        return monitorAdminClusterStateMapper.selectMonitorAdminClusterStateList(monitorAdminClusterState);
    }

    /**
     * 新增ES索引信息表
     * 
     * @param monitorAdminClusterState ES索引信息表
     * @return 结果
     */
    @Override
    public int insertMonitorAdminClusterState(MonitorAdminClusterState monitorAdminClusterState)
    {
        return monitorAdminClusterStateMapper.insertMonitorAdminClusterState(monitorAdminClusterState);
    }

    /**
     * 修改ES索引信息表
     * 
     * @param monitorAdminClusterState ES索引信息表
     * @return 结果
     */
    @Override
    public int updateMonitorAdminClusterState(MonitorAdminClusterState monitorAdminClusterState)
    {
        return monitorAdminClusterStateMapper.updateMonitorAdminClusterState(monitorAdminClusterState);
    }

    /**
     * 删除ES索引信息表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMonitorAdminClusterStateByIds(String ids)
    {
        return monitorAdminClusterStateMapper.deleteMonitorAdminClusterStateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除ES索引信息表信息
     * 
     * @param pkId ES索引信息表ID
     * @return 结果
     */
    @Override
    public int deleteMonitorAdminClusterStateById(Long pkId)
    {
        return monitorAdminClusterStateMapper.deleteMonitorAdminClusterStateById(pkId);
    }
}
