package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        */
    /*
        原始分页方法
        Long total = empMapper.getTotal();
        //注意这块传递的参数，sql中第一个参数是start，是起始索引，这里是page页数，传递的起始索引=记录数*（页码-1）
        List<Emp> list = empMapper.list((page - 1) * pageSize, pageSize);
        PageResult<Emp> pageResult = new PageResult<>();
        pageResult.setRows(list);
        pageResult.setTotal(total);
         */
    /*

        //使用pagehelper
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<Emp> list = empMapper.list(name,gender,begin,end);  //这里要把参数传过去！！！
        //3.解析查询结果，封装结果
        Page<Emp> p = (Page<Emp>) list;
        PageResult<Emp> pageResult = new PageResult<>(p.getTotal(),p.getResult());
        return pageResult;
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //使用pagehelper
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> list = empMapper.list(empQueryParam);  //这里要把参数传过去！！！
        //3.解析查询结果，封装结果
        Page<Emp> p = (Page<Emp>) list;
        PageResult<Emp> pageResult = new PageResult<>(p.getTotal(), p.getResult());
        return pageResult;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息,注意要先补全基本字段
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            //在mapper层声明了@Options(useGeneratedKeys = true,keyProperty = "id")，
            // 此时调用insert(emp)会得到得到主键的返回值，他被封装到了emp中的id属性里
            empMapper.insert(emp);


            //2.保存员工工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                //循环遍历集合,把工作经历表的员工id设置为emp中的主键id
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "员工信息：" + emp);
            empLogService.insertLog(empLog);
        }


    }
}
