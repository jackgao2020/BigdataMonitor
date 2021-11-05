package com.xl.project.system.database_info.controller;

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

import com.xl.project.system.database_info.domain.DatabaseInfo;
import com.xl.project.system.database_info.service.IDatabaseInfoService;

/**
 * listController
 * 
 * @author xl
 * @date 2021-06-22
 */
@Controller
@RequestMapping("/system/database_info")
public class DatabaseInfoController extends BaseController
{
    private String prefix = "system/database_info";

    @Autowired
    private IDatabaseInfoService databaseInfoService;

    @RequiresPermissions("system:database_info:view")
    @GetMapping()
    public String database_info()
    {
        return prefix + "/database_info";
    }

    /**
     * 查询list列表
     */
    @RequiresPermissions("system:database_info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DatabaseInfo databaseInfo)
    {
        startPage();
        List<DatabaseInfo> list = databaseInfoService.selectDatabaseInfoList(databaseInfo);
        return getDataTable(list);
    }

    /**
     * 导出list列表
     */
    @RequiresPermissions("system:database_info:export")
    @Log(title = "list", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DatabaseInfo databaseInfo)
    {
        List<DatabaseInfo> list = databaseInfoService.selectDatabaseInfoList(databaseInfo);
        ExcelUtil<DatabaseInfo> util = new ExcelUtil<DatabaseInfo>(DatabaseInfo.class);
        return util.exportExcel(list, "database_info");
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
    @RequiresPermissions("system:database_info:add")
    @Log(title = "list", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DatabaseInfo databaseInfo)
    {
        return toAjax(databaseInfoService.insertDatabaseInfo(databaseInfo));
    }

    /**
     * 修改list
     */
    @GetMapping("/edit/{databaseIp}")
    public String edit(@PathVariable("databaseIp") String databaseIp, ModelMap mmap)
    {
        DatabaseInfo databaseInfo = databaseInfoService.selectDatabaseInfoById(databaseIp);
        mmap.put("databaseInfo", databaseInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存list
     */
    @RequiresPermissions("system:database_info:edit")
    @Log(title = "list", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DatabaseInfo databaseInfo)
    {
        return toAjax(databaseInfoService.updateDatabaseInfo(databaseInfo));
    }

    /**
     * 删除list
     */
    @RequiresPermissions("system:database_info:remove")
    @Log(title = "list", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(databaseInfoService.deleteDatabaseInfoByIds(ids));
    }
}
