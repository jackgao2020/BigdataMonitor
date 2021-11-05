package com.xl.project.system.report.mapper;

import java.util.List;
import com.xl.project.system.report.domain.WeeklyReport;

/**
 * listMapper接口
 * 
 * @author xl
 * @date 2021-06-09
 */
public interface WeeklyReportMapper 
{
    /**
     * 查询list
     * 
     * @param no listID
     * @return list
     */
    public WeeklyReport selectWeeklyReportById(Long no);

    /**
     * 查询list列表
     * 
     * @param weeklyReport list
     * @return list集合
     */
    public List<WeeklyReport> selectWeeklyReportList(WeeklyReport weeklyReport);

    /**
     * 新增list
     * 
     * @param weeklyReport list
     * @return 结果
     */
    public int insertWeeklyReport(WeeklyReport weeklyReport);

    /**
     * 修改list
     * 
     * @param weeklyReport list
     * @return 结果
     */
    public int updateWeeklyReport(WeeklyReport weeklyReport);

    /**
     * 删除list
     * 
     * @param no listID
     * @return 结果
     */
    public int deleteWeeklyReportById(Long no);

    /**
     * 批量删除list
     * 
     * @param nos 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeeklyReportByIds(String[] nos);
}
