package com.xl.project.system.report.service.impl;

import java.util.List;

import com.xl.common.utils.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xl.project.system.report.mapper.WeeklyReportMapper;
import com.xl.project.system.report.domain.WeeklyReport;
import com.xl.project.system.report.service.IWeeklyReportService;


/**
 * listService业务层处理
 * 
 * @author xl
 * @date 2021-06-09
 */
@Service
public class WeeklyReportServiceImpl implements IWeeklyReportService 
{
    /**
     *
     */
    @Autowired
    private WeeklyReportMapper weeklyReportMapper;

    /**
     * 查询list
     * 
     * @param no listID
     * @return list
     */
    @Override
    public WeeklyReport selectWeeklyReportById(Long no)
    {
        return weeklyReportMapper.selectWeeklyReportById(no);
    }

    /**
     * 查询list列表
     * 
     * @param weeklyReport list
     * @return list
     */
    @Override
    public List<WeeklyReport> selectWeeklyReportList(WeeklyReport weeklyReport)
    {
        return weeklyReportMapper.selectWeeklyReportList(weeklyReport);
    }

    /**
     * 新增list
     * 
     * @param weeklyReport list
     * @return 结果
     */
    @Override
    public int insertWeeklyReport(WeeklyReport weeklyReport)
    {
        return weeklyReportMapper.insertWeeklyReport(weeklyReport);
    }

    /**
     * 修改list
     * 
     * @param weeklyReport list
     * @return 结果
     */
    @Override
    public int updateWeeklyReport(WeeklyReport weeklyReport)
    {
        return weeklyReportMapper.updateWeeklyReport(weeklyReport);
    }

    /**
     * 删除list对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeeklyReportByIds(String ids)
    {
        return weeklyReportMapper.deleteWeeklyReportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除list信息
     * 
     * @param no listID
     * @return 结果
     */
    @Override
    public int deleteWeeklyReportById(Long no)
    {
        return weeklyReportMapper.deleteWeeklyReportById(no);
    }
}
