package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /*@GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("条件查询的参数：{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageResult);
    }*/

    /*
    条件分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {  //把查询的参数封装到了一个实体类当中
        log.info("条件查询的参数：{},{},{},{},{},{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*
    新增员工信息
     */
    @PostMapping
    public Result insert(@RequestBody Emp emp) {   //@RequestBody注解把前端传过来的json格式数据封装到emp对象里
        log.info("接收到的参数：{}，{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /*
    （批量）删除员工信息
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {   //前端传过来的是数组，后端要封装到list集合当中必须要用@RequestParam注解声明
        log.info("删除的员工id：{}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /*
    查询单个员工信息进行回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) { //@PathVariable把路径中的参数传到形参（实现URL路径变量到方法参数的映射）
        log.info("id:{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /*
    更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }

    /*
    查询全部班主任员工，主要用于班级管理模块添加班级信息设置班主任
     */
    @GetMapping("/list")
    public Result EmpList(){
        log.info("查询所有班主任列表");
        List<Emp> list = empService.getEmpList();
        return Result.success(list);
    }


}
