package com.zjz.community.controller;

import com.zjz.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration =  request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        // 请求的http://localhost:8080/community/alpha/http?code=123  设置code为123
        System.out.println(request.getParameter("code"));

        //返回相应数据
        response.setContentType("text/html;charset=utf-8");
        try{
            PrintWriter writer =  response.getWriter();
            writer.write("<h1>牛客网</h1>");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    // get请求
    // /students?current=1&limit=20    当前页面＋每页多少行数据

    // 路径 ＋ 明确只能处理 get请求
    @RequestMapping(path = "/student", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // 路径 ＋ 明确只能处理 get请求
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getOneStudents(@PathVariable("id") int id){
        System.out.println(id);
        return "a students";
    }

    // POST请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, String age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应html数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","Tom");
        mav.addObject("age", "20");
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "北京邮电大学");
        model.addAttribute("age", 80);
        return "/demo/view";
    }

    // 响应json请求
    // Java对象 不兼容 JS对象；； JSON是一种衔接的作用，用于中间的变量 转换
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        map.put("salary", 10000);
        return map;
    }

    // 响应json请求
    // Java对象 不兼容 JS对象；； JSON是一种衔接的作用，用于中间的变量 转换
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        map.put("salary", 10000);
        list.add(map);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "李四");
        map1.put("age", 23);
        map1.put("salary", 8000);
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "王五");
        map2.put("age", 23);
        map2.put("salary", 1000);
        list.add(map2);

        return list;
    }

}
