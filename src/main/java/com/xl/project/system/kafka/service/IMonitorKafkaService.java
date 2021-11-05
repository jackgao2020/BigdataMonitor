package com.xl.project.system.kafka.service;

import java.util.List;
import com.xl.project.system.kafka.domain.MonitorKafka;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author xl
 * @date 2021-04-27
 */
public interface IMonitorKafkaService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public MonitorKafka selectMonitorKafkaById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param monitorKafka 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MonitorKafka> selectMonitorKafkaList(MonitorKafka monitorKafka);

    /**
     * 新增【请填写功能名称】
     * 
     * @param monitorKafka 【请填写功能名称】
     * @return 结果
     */
    public int insertMonitorKafka(MonitorKafka monitorKafka);

    /**
     * 修改【请填写功能名称】
     * 
     * @param monitorKafka 【请填写功能名称】
     * @return 结果
     */
    public int updateMonitorKafka(MonitorKafka monitorKafka);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMonitorKafkaByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteMonitorKafkaById(Long id);
}
