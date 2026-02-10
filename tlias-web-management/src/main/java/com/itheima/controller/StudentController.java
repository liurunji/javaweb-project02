package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*
    分页条件查询学生信息
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("条件分页查询的参数：{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /*
    添加学生信息
     */
    @PostMapping
    public Result insert(@RequestBody Student student) {
        log.info("学生信息：{}", student);
        studentService.insert(student);
        return Result.success();
    }

    /*
    根据id查询学生信息并回显
     */
    @GetMapping("/{id}")
    public Result query(@PathVariable Integer id) {
        log.info("id:{}",id);
        Student student = studentService.queryById(id);
        return Result.success(student);
    }

    /*
    修改学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("学生信息：{}",student);
        studentService.update(student);
        return Result.success();
    }

    /*
    删除学生信息
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("要删除的id：{}",ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /*
     违纪处理：该接口用于修改学员的数据信息
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("id:{},score:{}",id,score);
        studentService.violation(id,score);
        return Result.success();
    }

}
