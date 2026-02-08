package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

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
            }else {
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
}
