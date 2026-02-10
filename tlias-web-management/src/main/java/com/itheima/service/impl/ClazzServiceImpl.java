package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.exception.DeleteClazzFailedException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    /*
    （带条件）分页查询所有班级信息
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2.执行查询得到所有（符合条件的）记录数
        List<Clazz> list = clazzMapper.list(clazzQueryParam);
        System.out.println(list);
        list.forEach(clazz -> {
            LocalDate beginDate = clazz.getBeginDate();
            LocalDate endDate = clazz.getEndDate();
            LocalDate now = LocalDate.now();
            if (now.isBefore(beginDate)) {
                clazz.setStatus("未开班");
            } else if (now.isAfter(endDate)) {
                clazz.setStatus("已结课");
            } else {
                clazz.setStatus("在读中");
            }
        });
        System.out.println(list);
        //3.解析查询结果，封装结果
        //Page<> 不仅有查询到的数据，还包含分页信息、总记录数
        Page<Clazz> p = (Page<Clazz>) list;
        PageResult<Clazz> pageResult = new PageResult<>(p.getTotal(), p.getResult());
        return pageResult;
    }

    /*
    新增班级信息
     */
    @Override
    public void insert(Clazz clazz) {
        //给创建时间和更新时间赋值
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //调用mapper方法
        clazzMapper.insert(clazz);
    }

    /*
    根据id查找班级信息
     */
    @Override
    public Clazz selectById(Integer id) {
        return clazzMapper.selectById(id);
    }

    /*
    修改班级信息（条件是id）
     */
    @Override
    public void update(Clazz clazz) {
        //将更新时间设置为当前时间
        clazz.setUpdateTime(LocalDateTime.now());
        //调用mapper方法
        clazzMapper.update(clazz);
    }

    /*
    根据班级id删除班级信息
     */
    @Override
    public void deleteById(Integer id) {
        /*
        思路：先调用mapper查询此班级下是否有学生存在，如果存在抛出异常进行处理
        若无学生，则直接删除
         */
        Integer num = clazzMapper.queryStuNumByClazzId(id);
        if (num == 0){
            //没有学生调用mapper方法删除班级
            clazzMapper.deleteById(id);
        }else {
            //有学生，抛出异常
            throw new DeleteClazzFailedException();
        }
    }
}
