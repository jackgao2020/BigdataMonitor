package com.xl.project.system.report.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xl.common.utils.poi.ExcelUtil;
import com.xl.framework.aspectj.lang.annotation.Log;
import com.xl.framework.aspectj.lang.enums.BusinessType;
import com.xl.framework.web.controller.BaseController;
import com.xl.framework.web.domain.AjaxResult;
import com.xl.framework.web.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xl.project.system.report.domain.WeeklyReport;
import com.xl.project.system.report.service.IWeeklyReportService;

/**
 * listController
 * 
 * @author xl
 * @date 2021-06-09
 */
@Controller
@RequestMapping("/system/report")
public class WeeklyReportController extends BaseController
{
    private String prefix = "system/report";

    @Autowired
    private IWeeklyReportService weeklyReportService;

    @RequiresPermissions("system:report:view")
    @GetMapping()
    public String report()
    {
        return prefix + "/report";
    }

    /**
     * 查询list列表
     */
    @RequiresPermissions("system:report:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeeklyReport weeklyReport)  {
        startPage();
        List<WeeklyReport> list = weeklyReportService.selectWeeklyReportList(weeklyReport);
        for (WeeklyReport weeklyReport1:list) {
            //取出这个字段的值
            Date StartDate = weeklyReport1.getStartDate();
            Date StopDate =  weeklyReport1.getStopDate();
//            String s = "";
//            try {
//                 s = this.strToDateFormat(StartDate);
//            }catch (ParseException e){
//                e.printStackTrace();
//            }

            String s =this.toString(StartDate);
            String d =this.toString(StopDate);
            //把格式化后的字符串赋值给这个对象中的一个字段
            weeklyReport1.setBeginDate(s);
            weeklyReport1.setEndDate(s);
        }
        return getDataTable(list);
    }
    public static String strToDateFormat(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        Date newDate= formatter.parse(date);
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(newDate);
    }
    /**
     * 导出list列表
     */
    @RequiresPermissions("system:report:export")
    @Log(title = "list", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeeklyReport weeklyReport)
    {
        List<WeeklyReport> list = weeklyReportService.selectWeeklyReportList(weeklyReport);
        ExcelUtil<WeeklyReport> util = new ExcelUtil<WeeklyReport>(WeeklyReport.class);
        return util.exportExcel(list, "report");
    }

    /**
     * 新增list
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存list
     */
    @RequiresPermissions("system:report:add")
    @Log(title = "list", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WeeklyReport weeklyReport)
    {
        return toAjax(weeklyReportService.insertWeeklyReport(weeklyReport));
    }

    /**
     * 修改list
     */
    @GetMapping("/edit/{no}")
    public String edit(@PathVariable("no") Long no, ModelMap mmap)
    {
        WeeklyReport weeklyReport = weeklyReportService.selectWeeklyReportById(no);
        mmap.put("weeklyReport", weeklyReport);
        return prefix + "/edit";
    }

    /**
     * 修改保存list
     */
    @RequiresPermissions("system:report:edit")
    @Log(title = "list", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WeeklyReport weeklyReport)
    {
        return toAjax(weeklyReportService.updateWeeklyReport(weeklyReport));
    }

    /**
     * 删除list
     */
    @RequiresPermissions("system:report:remove")
    @Log(title = "list", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weeklyReportService.deleteWeeklyReportByIds(ids));
    }

    /**
     * 将Date类型时间转换为字符串
     * @param date
     * @return
     */
    public static String toString(Date date) {
        String time;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        time = formater.format(date);
        return time;
    }
}
