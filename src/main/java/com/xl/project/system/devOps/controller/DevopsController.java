package com.xl.project.system.devOps.controller;


import com.xl.framework.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 【请填写功能名称】Controller
 * 
 * @author xl
 * @date 2021-04-27
 */
@Controller  //标识注解这个MonitorKafkaController是一个controller类用于接收页面请求
@RequestMapping("/system/devOps") //映射请求，通过它来指定控制器可以处理哪些URL请求
public class DevopsController extends BaseController
{
    private String prefix = "system/devOps";
    @GetMapping()    //get请求方式的
    public String devOps()
    {
        return prefix + "/devOps";
    }

    @GetMapping("/devOps2")    //get请求方式的
    public String devOps2()
    {
        return prefix + "/devOps2";
    }

    @GetMapping("/devOps3")    //get请求方式的
    public String devOps3()
    {
        return prefix + "/devOps3";
    }

    @GetMapping("/devOps4")    //get请求方式的
    public String devOps4()
    {
        return prefix + "/devOps4";
    }
    @GetMapping("/devOps5")    //get请求方式的
    public String devOps5()
    {
        return prefix + "/devOps5";
    }
    @GetMapping("/devOps6")    //get请求方式的
    public String devOps6()
    {
        return prefix + "/devOps6";
    }
}
