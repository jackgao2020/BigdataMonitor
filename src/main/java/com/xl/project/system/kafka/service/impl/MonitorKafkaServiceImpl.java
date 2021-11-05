package com.xl.project.system.kafka.service.impl;

import java.util.List;

import com.xl.common.utils.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xl.project.system.kafka.mapper.MonitorKafkaMapper;
import com.xl.project.system.kafka.domain.MonitorKafka;
import com.xl.project.system.kafka.service.IMonitorKafkaService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author xl
 * @date 2021-04-27
 */
@Service
public class MonitorKafkaServiceImpl implements IMonitorKafkaService 
{
    @Autowired
    private MonitorKafkaMapper monitorKafkaMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public MonitorKafka selectMonitorKafkaById(Long id)
    {
        return monitorKafkaMapper.selectMonitorKafkaById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param monitorKafka 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<MonitorKafka> selectMonitorKafkaList(MonitorKafka monitorKafka)
    {

        return monitorKafkaMapper.selectMonitorKafkaList(monitorKafka);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param monitorKafka 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertMonitorKafka(MonitorKafka monitorKafka)
    {
        return monitorKafkaMapper.insertMonitorKafka(monitorKafka);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param monitorKafka 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateMonitorKafka(MonitorKafka monitorKafka)
    {
        return monitorKafkaMapper.updateMonitorKafka(monitorKafka);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMonitorKafkaByIds(String ids)
    {
        return monitorKafkaMapper.deleteMonitorKafkaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteMonitorKafkaById(Long id)
    {
        return monitorKafkaMapper.deleteMonitorKafkaById(id);
    }
}
