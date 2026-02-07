package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> findAll();

    public void deleteById(Integer id);

    void insert(Dept dept);

    Dept findById(Integer id);

    void updateById(Dept dept);
}
