package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /*
    班级分页条件查询
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("班级条件查询参数：{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /*
    新增班级信息
     */
    @PostMapping
    public Result insert(@RequestBody Clazz clazz){
        log.info("新增班级的信息：{}",clazz);
        clazzService.insert(clazz);
        return Result.success();
    }

    /*
    根据id查询班级信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("id:{}",id);
        Clazz clazz = clazzService.selectById(id);
        return Result.success(clazz);
    }

    /*
    根据id修改班级信息
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("班级信息参数：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

}
