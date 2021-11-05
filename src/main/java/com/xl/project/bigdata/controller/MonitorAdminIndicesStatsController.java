package com.xl.project.bigdata.controller;

import com.xl.common.utils.poi.ExcelUtil;
import com.xl.framework.aspectj.lang.annotation.Log;
import com.xl.framework.aspectj.lang.enums.BusinessType;
import com.xl.framework.web.controller.BaseController;
import com.xl.framework.web.domain.AjaxResult;
import com.xl.framework.web.page.TableDataInfo;
import com.xl.project.bigdata.domain.MonitorAdminIndicesStats;
import com.xl.project.bigdata.service.IMonitorAdminIndicesStatsService;
import com.xl.project.bigdata.task.MonitorTask;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * es索引监控Controller
 * 
 * @author elf
 * @date 2020-11-16
 */
@Controller
@RequestMapping("/esmonitor/indexstatus")
public class MonitorAdminIndicesStatsController extends BaseController
{
    private String prefix = "monitor/esmonitor";

    @Autowired
    private IMonitorAdminIndicesStatsService monitorAdminIndicesStatsService;

    @RequiresPermissions("esmonitor:indexstatus:view")
    @GetMapping()
    public String indexstatus()
    {
        return prefix + "/esmonitor";
    }

    /**
     * 查询es索引监控列表
     */
    @RequiresPermissions("esmonitor:indexstatus:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MonitorAdminIndicesStats monitorAdminIndicesStats)
    {
        startPage();
        List<MonitorAdminIndicesStats> list = monitorAdminIndicesStatsService.selectMonitorAdminIndicesStatsList(monitorAdminIndicesStats);

      /*  TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(list.size());
        tableDataInfo.setRows(list);
        return tableDataInfo;*/
        return getDataTable(list);
    }

    /**
     * 导出es索引监控列表
     */
    @RequiresPermissions("esmonitor:indexstatus:export")
    @Log(title = "es索引监控", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MonitorAdminIndicesStats monitorAdminIndicesStats)
    {
        List<MonitorAdminIndicesStats> list = monitorAdminIndicesStatsService.selectMonitorAdminIndicesStatsList(monitorAdminIndicesStats);
        ExcelUtil<MonitorAdminIndicesStats> util = new ExcelUtil<MonitorAdminIndicesStats>(MonitorAdminIndicesStats.class);
        return util.exportExcel(list, "indexstatus");
    }

}
