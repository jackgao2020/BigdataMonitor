package com.xl.project.bigdata.mapper;

import java.util.List;

import com.xl.project.bigdata.domain.MonitorAdminClusterState;

/**
 * ES索引信息表Mapper接口
 * 
 * @author elf
 * @date 2020-11-19
 */
public interface MonitorAdminClusterStateMapper 
{
    /**
     * 查询ES索引信息表
     * 
     * @param pkId ES索引信息表ID
     * @return ES索引信息表
     */
    public MonitorAdminClusterState selectMonitorAdminClusterStateById(Long pkId);

    /**
     * 查询ES索引信息表列表
     * 
     * @param monitorAdminClusterState ES索引信息表
     * @return ES索引信息表集合
     */
    public List<MonitorAdminClusterState> selectMonitorAdminClusterStateList(MonitorAdminClusterState monitorAdminClusterState);

    /**
     * 查询ES索引信息表列表个数
     *
     * @param monitorAdminClusterState ES索引信息表
     * @return ES索引信息表列表个数
     */
    public Integer countMonitorAdminClusterState(MonitorAdminClusterState monitorAdminClusterState);

    /**
     * 新增ES索引信息表
     * 
     * @param monitorAdminClusterState ES索引信息表
     * @return 结果
     */
    public int insertMonitorAdminClusterState(MonitorAdminClusterState monitorAdminClusterState);

    /**
     * 修改ES索引信息表
     * 
     * @param monitorAdminClusterState ES索引信息表
     * @return 结果
     */
    public int updateMonitorAdminClusterState(MonitorAdminClusterState monitorAdminClusterState);

    /**
     * 删除ES索引信息表
     * 
     * @param pkId ES索引信息表ID
     * @return 结果
     */
    public int deleteMonitorAdminClusterStateById(Long pkId);

    /**
     * 批量删除ES索引信息表
     * 
     * @param pkIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMonitorAdminClusterStateByIds(String[] pkIds);
}
