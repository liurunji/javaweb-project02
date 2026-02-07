package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result findAll() {
        List<Dept> list = deptService.findAll();
        return Result.success(list);
    }

    @DeleteMapping
    public Result delete(Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }

    /*
    新增部门
     */
    @PostMapping
    public Result insert(@RequestBody Dept dept) {  //@RequestBody注解把前端传过来的json格式数据封装到dept对象里
        //System.out.println("新增的部门名称：" + dept);
        log.info("新增的部门名称：{}", dept);
        deptService.insert(dept);
        return Result.success();
    }


    /*
    根据id查询部门
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {   //@PathVariable把路径中的参数传到形参（实现URL路径变量到方法参数的映射）
        Dept dept = deptService.findById(id);
        //System.out.println("查询的id为：" + id);
        log.info("查询的id为：{}", id);
        return Result.success(dept);
    }

    /*
    根据ID修改数据
     */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        //System.out.println("要更新的部门信息：" + dept);
        log.info("要更新的部门信息：{}" , dept);
        deptService.updateById(dept);
        return Result.success();
    }
}
