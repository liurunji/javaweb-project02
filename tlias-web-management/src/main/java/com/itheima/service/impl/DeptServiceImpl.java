package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
         deptMapper.deleteById(id);
    }

    @Override
    public void insert(Dept dept) {
        //1.封装其余属性：createTime,updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper
        deptMapper.insert(dept);
    }

    @Override
    public Dept findById(Integer id) {
        Dept dept = deptMapper.findById(id);
        return dept;
    }

    @Override
    public void updateById(Dept dept) {
        //封装updateTime
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dept);
    }
}
