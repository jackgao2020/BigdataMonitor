package com.xl.project.bigdata.controller;

import java.util.List;

import com.xl.common.utils.poi.ExcelUtil;
import com.xl.framework.aspectj.lang.annotation.Log;
import com.xl.framework.aspectj.lang.enums.BusinessType;
import com.xl.framework.web.controller.BaseController;
import com.xl.framework.web.domain.AjaxResult;
import com.xl.framework.web.page.TableDataInfo;
import com.xl.project.bigdata.domain.MonitorAdminClusterNodesStats;
import com.xl.project.bigdata.service.IMonitorAdminClusterNodesStatsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 节点监控Controller
 * 
 * @author xl
 * @date 2020-12-09
 */
@Controller
@RequestMapping("/stats/nodes")
public class MonitorAdminClusterNodesStatsController extends BaseController
{
    private String prefix = "monitor/esmonitor";


    @Autowired
    private IMonitorAdminClusterNodesStatsService monitorAdminClusterNodesStatsService;

    @RequiresPermissions("stats:nodes:view")
    @GetMapping()
    public String nodes()
    {
        return prefix + "/nodes";
    }

    /**
     * 查询节点监控列表
     * 接收查询条件控制请求转发
     *
     */
    @RequiresPermissions("stats:nodes:list")   //控制权限
    @PostMapping("/list") //post get 请求   mqpping 映射请求地址
    @ResponseBody
    public TableDataInfo list(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats)//c查询条件
    {
        startPage();//分页
        List<MonitorAdminClusterNodesStats> list = monitorAdminClusterNodesStatsService.selectMonitorAdminClusterNodesStatsList(monitorAdminClusterNodesStats);
        return getDataTable(list);
    }

    /**
     * 导出节点监控列表
     */
    @RequiresPermissions("stats:nodes:export")
    @Log(title = "节点监控", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats)
    {
        List<MonitorAdminClusterNodesStats> list = monitorAdminClusterNodesStatsService.selectMonitorAdminClusterNodesStatsList(monitorAdminClusterNodesStats);
        ExcelUtil<MonitorAdminClusterNodesStats> util = new ExcelUtil<MonitorAdminClusterNodesStats>(MonitorAdminClusterNodesStats.class);
        return util.exportExcel(list, "nodes");
    }

    /**
     * 新增节点监控
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存节点监控
     */
    @RequiresPermissions("stats:nodes:add")
    @Log(title = "节点监控", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats)
    {
        return toAjax(monitorAdminClusterNodesStatsService.insertMonitorAdminClusterNodesStats(monitorAdminClusterNodesStats));
    }

    /**
     * 修改节点监控
     */
    @GetMapping("/edit/{pkId}")
    public String edit(@PathVariable("pkId") Long pkId, ModelMap mmap)
    {
        MonitorAdminClusterNodesStats monitorAdminClusterNodesStats = monitorAdminClusterNodesStatsService.selectMonitorAdminClusterNodesStatsById(pkId);
        mmap.put("monitorAdminClusterNodesStats", monitorAdminClusterNodesStats);
        return prefix + "/edit";
    }

    /**
     * 修改保存节点监控
     */
    @RequiresPermissions("stats:nodes:edit")
    @Log(title = "节点监控", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MonitorAdminClusterNodesStats monitorAdminClusterNodesStats)
    {
        return toAjax(monitorAdminClusterNodesStatsService.updateMonitorAdminClusterNodesStats(monitorAdminClusterNodesStats));
    }

    /**2
     * 删除节点监控
     */
    @RequiresPermissions("stats:nodes:remove")
    @Log(title = "节点监控", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(monitorAdminClusterNodesStatsService.deleteMonitorAdminClusterNodesStatsByIds(ids));
    }
}
