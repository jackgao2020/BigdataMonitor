package com.xl.project.bigdata.controller;

import java.util.List;

import com.xl.common.utils.poi.ExcelUtil;
import com.xl.framework.aspectj.lang.annotation.Log;
import com.xl.framework.aspectj.lang.enums.BusinessType;
import com.xl.framework.web.controller.BaseController;
import com.xl.framework.web.domain.AjaxResult;
import com.xl.framework.web.page.TableDataInfo;
import com.xl.project.bigdata.domain.MonitorAdminClusterState;
import com.xl.project.bigdata.service.IMonitorAdminClusterStateService;
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
 * ES索引信息表Controller
 * 
 * @author elf
 * @date 2020-11-19
 */
@Controller
@RequestMapping("/system/state")
public class MonitorAdminClusterStateController extends BaseController
{
    private String prefix = "esmonitor/esmonitor";

    @Autowired
    private IMonitorAdminClusterStateService monitorAdminClusterStateService;

    @RequiresPermissions("system:state:view")
    @GetMapping()
    public String state()
    {
        return prefix + "/state";
    }

    /**
     * 查询ES索引信息表列表
     */
    @RequiresPermissions("system:state:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MonitorAdminClusterState monitorAdminClusterState)
    {
        startPage();
        List<MonitorAdminClusterState> list = monitorAdminClusterStateService.selectMonitorAdminClusterStateList(monitorAdminClusterState);
        return getDataTable(list);
    }

    /**
     * 导出ES索引信息表列表
     */
    @RequiresPermissions("system:state:export")
    @Log(title = "ES索引信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MonitorAdminClusterState monitorAdminClusterState)
    {
        List<MonitorAdminClusterState> list = monitorAdminClusterStateService.selectMonitorAdminClusterStateList(monitorAdminClusterState);
        ExcelUtil<MonitorAdminClusterState> util = new ExcelUtil<MonitorAdminClusterState>(MonitorAdminClusterState.class);
        return util.exportExcel(list, "state");
    }

    /**
     * 新增ES索引信息表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存ES索引信息表
     */
    @RequiresPermissions("system:state:add")
    @Log(title = "ES索引信息表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MonitorAdminClusterState monitorAdminClusterState)
    {
        return toAjax(monitorAdminClusterStateService.insertMonitorAdminClusterState(monitorAdminClusterState));
    }

    /**
     * 修改ES索引信息表
     */
    @GetMapping("/edit/{pkId}")
    public String edit(@PathVariable("pkId") Long pkId, ModelMap mmap)
    {
        MonitorAdminClusterState monitorAdminClusterState = monitorAdminClusterStateService.selectMonitorAdminClusterStateById(pkId);
        mmap.put("monitorAdminClusterState", monitorAdminClusterState);
        return prefix + "/edit";
    }

    /**
     * 修改保存ES索引信息表
     */
    @RequiresPermissions("system:state:edit")
    @Log(title = "ES索引信息表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MonitorAdminClusterState monitorAdminClusterState)
    {
        return toAjax(monitorAdminClusterStateService.updateMonitorAdminClusterState(monitorAdminClusterState));
    }

    /**
     * 删除ES索引信息表
     */
    @RequiresPermissions("system:state:remove")
    @Log(title = "ES索引信息表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(monitorAdminClusterStateService.deleteMonitorAdminClusterStateByIds(ids));
    }
}
