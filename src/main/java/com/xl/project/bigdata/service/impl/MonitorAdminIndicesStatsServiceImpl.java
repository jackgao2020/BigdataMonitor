package com.xl.project.bigdata.service.impl;

import com.xl.common.utils.text.Convert;
import com.xl.framework.aspectj.lang.annotation.DataSource;
import com.xl.framework.aspectj.lang.enums.DataSourceType;
import com.xl.project.bigdata.domain.MonitorAdminClusterState;
import com.xl.project.bigdata.domain.MonitorAdminIndicesStats;
import com.xl.project.bigdata.mapper.MonitorAdminClusterStateMapper;
import com.xl.project.bigdata.mapper.MonitorAdminIndicesStatsMapper;
import com.xl.project.bigdata.service.IMonitorAdminIndicesStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * es索引监控Service业务层处理
 * 
 * @author elf
 * @date 2020-11-16
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class MonitorAdminIndicesStatsServiceImpl implements IMonitorAdminIndicesStatsService
{
    @Autowired
    private MonitorAdminIndicesStatsMapper monitorAdminIndicesStatsMapper;
    @Autowired
    private MonitorAdminClusterStateMapper monitorAdminClusterStateMapper;
    /**
     * 查询es索引监控
     * 
     * @param id es索引监控ID
     * @return es索引监控
     */
    @Override
    public MonitorAdminIndicesStats selectMonitorAdminIndicesStatsById(Long id)
    {
        return monitorAdminIndicesStatsMapper.selectMonitorAdminIndicesStatsById(id);
    }

    /**
     * 查询es索引监控列表
     * 
     * @param monitorAdminIndicesStats es索引监控
     * @return es索引监控
     */
    @Override
    public List<MonitorAdminIndicesStats> selectMonitorAdminIndicesStatsList(MonitorAdminIndicesStats monitorAdminIndicesStats)
    {
        MonitorAdminClusterState monitorAdminClusterState=new MonitorAdminClusterState();
        monitorAdminClusterState.setClusterInfo(monitorAdminIndicesStats.getClusterinfo());
        monitorAdminClusterState.setProvidedName(monitorAdminIndicesStats.getIndexname());
        //先去MonitorAdminClusterState表查满足查询条件的索引个数
        Integer size = monitorAdminClusterStateMapper.countMonitorAdminClusterState(monitorAdminClusterState);
        monitorAdminIndicesStats.setEsindexcount(size*2);
        List <MonitorAdminIndicesStats> list = monitorAdminIndicesStatsMapper.selectMonitorAdminIndicesStatsList(monitorAdminIndicesStats);
        //取最新记录并计算并发量
        Map<String,MonitorAdminIndicesStats> map = new HashMap<>();
        List<MonitorAdminIndicesStats> result = new ArrayList<>();
        for (MonitorAdminIndicesStats monitor : list){
            //查询时要保证已经排序
            if(!map.containsKey(monitor.getIndexname())){//最新记录
                map.put(monitor.getIndexname(),monitor);
            }else{
                MonitorAdminIndicesStats latestMonitor=map.get(monitor.getIndexname());
                if (null!=latestMonitor.getQueryconcurrency()){
                    continue;//已经计算了最新的并发
                }
                Long latestInserTime=latestMonitor.getInserttime();
                Long insertTime=monitor.getInserttime();
                if (null!=latestInserTime&&null!=insertTime&&latestInserTime>insertTime){
                    //计算并发量
                    Long interval = latestInserTime-insertTime;
                    Long queryConcurrency=(latestMonitor.getQuerytotal()-monitor.getQuerytotal())/interval;
                    Long indexConcurrency=(latestMonitor.getIndextotal()-monitor.getIndextotal())/interval;
                    latestMonitor.setQueryconcurrency(queryConcurrency);
                    latestMonitor.setIndexconcurrency(indexConcurrency);
                    String searchTime=latestMonitor.getInserttime()+"";
                    latestMonitor.setSearchtime(searchTime.substring(0,4)+"-"+searchTime.substring(4,6)+"-"+searchTime.substring(6,8)+" "+searchTime.substring(8,10)+":"+searchTime.substring(10,12)+":"+searchTime.substring(12,14));
                    result.add(latestMonitor);
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * 新增es索引监控
     * 
     * @param monitorAdminIndicesStats es索引监控
     * @return 结果
     */
    @Override
    public int insertMonitorAdminIndicesStats(MonitorAdminIndicesStats monitorAdminIndicesStats)
    {
        return monitorAdminIndicesStatsMapper.insertMonitorAdminIndicesStats(monitorAdminIndicesStats);
    }

    /**
     * 修改es索引监控
     * 
     * @param monitorAdminIndicesStats es索引监控
     * @return 结果
     */
    @Override
    public int updateMonitorAdminIndicesStats(MonitorAdminIndicesStats monitorAdminIndicesStats)
    {
        return monitorAdminIndicesStatsMapper.updateMonitorAdminIndicesStats(monitorAdminIndicesStats);
    }

    /**
     * 删除es索引监控对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMonitorAdminIndicesStatsByIds(String ids)
    {
        return monitorAdminIndicesStatsMapper.deleteMonitorAdminIndicesStatsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除es索引监控信息
     * 
     * @param id es索引监控ID
     * @return 结果
     */
    @Override
    public int deleteMonitorAdminIndicesStatsById(Long id)
    {
        return monitorAdminIndicesStatsMapper.deleteMonitorAdminIndicesStatsById(id);
    }
}
