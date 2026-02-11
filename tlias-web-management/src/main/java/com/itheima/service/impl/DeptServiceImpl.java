package com.itheima.service.impl;

import com.itheima.exception.DeleteDeptFailedException;
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

    /*
    删除部门
    - 删除部门时：如果部门下有员工，则不允许删除该部门，并给前端提示错误信息：对不起，当前部门下有员工，不能直接删除！
     */
    @Override
    public void deleteById(Integer id) {
        Integer num = deptMapper.findDeptEmpNum(id);
        if (num == 0){
            //该部门下没有员工，执行删除操作
            deptMapper.deleteById(id);
        }else {
            //有员工，不能删除，抛出异常
            throw new DeleteDeptFailedException();
        }

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
