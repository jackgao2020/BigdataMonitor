package com.xl.project.system.kafka.controller;

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

import com.xl.project.system.kafka.domain.MonitorKafka;
import com.xl.project.system.kafka.service.IMonitorKafkaService;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 【请填写功能名称】Controller
 * 
 * @author xl
 * @date 2021-04-27
 */
@Controller  //标识注解这个MonitorKafkaController是一个controller类用于接收页面请求
@RequestMapping("/system/kafka") //映射请求，通过它来指定控制器可以处理哪些URL请求
public class MonitorKafkaController extends BaseController
{
    private String prefix = "system/kafka";

    @Autowired //自动注入   spring容器
    private IMonitorKafkaService monitorKafkaService;

    @RequiresPermissions("system:kafka:view") //要求必须有system权限才能访问view
    @GetMapping()    //get请求方式的
    public String kafka()
    {
        return prefix + "/kafka";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:kafka:list") //要求必须有system权限才能访问list
    @PostMapping("/list")   //post 请求方式的
    @ResponseBody //作用是将controller的方法返回的对象 通过适当的转换器 转换为指定的格式之后，写入到response对象的body区（响应体中）
    public TableDataInfo list(MonitorKafka monitorKafka)
    {
        startPage();
        List<MonitorKafka> list = monitorKafkaService.selectMonitorKafkaList(monitorKafka);
        for (MonitorKafka monitorKafka1:list){
            //取出这个字段的值
            String insertdate = monitorKafka1.getInsertdate();
            //精确到秒
            //insertdate = insertdate/1000;
            //把时间戳转为格式化后的字符串
            String s = this.timeStamp2Date(String.valueOf(insertdate), "yyyy-MM-dd HH:mm:ss");
            //把格式化后的字符串赋值给这个对象中的一个字段
            monitorKafka1.setInsertdate(s);
            //            monitorKafka1.setInsertdate(timeStamp2Date(monitorKafka1.getInsertdate()+"","yyyy-MM-dd HH:mm:ss"));
        }
        return getDataTable(list);
    }
    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }
    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:kafka:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MonitorKafka monitorKafka)
    {
        List<MonitorKafka> list = monitorKafkaService.selectMonitorKafkaList(monitorKafka);
        ExcelUtil<MonitorKafka> util = new ExcelUtil<MonitorKafka>(MonitorKafka.class);
        return util.exportExcel(list, "kafka");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("system:kafka:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MonitorKafka monitorKafka)
    {
        return toAjax(monitorKafkaService.insertMonitorKafka(monitorKafka));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MonitorKafka monitorKafka = monitorKafkaService.selectMonitorKafkaById(id);
        mmap.put("monitorKafka", monitorKafka);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:kafka:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MonitorKafka monitorKafka)
    {
        return toAjax(monitorKafkaService.updateMonitorKafka(monitorKafka));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:kafka:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(monitorKafkaService.deleteMonitorKafkaByIds(ids));
    }
}
