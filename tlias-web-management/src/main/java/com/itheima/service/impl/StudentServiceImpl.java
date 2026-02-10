package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /*
    获取所有学生信息
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1.设置分页参数 （必须要先设置，容易忘记）
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //2.执行查询
        List<Student> list = studentMapper.getInfo(studentQueryParam);

        //3.解析查询结果并封装返回
        Page<Student> p = (Page<Student>) list;
        //输出list：  Page{count=true, pageNum=1, pageSize=20, startRow=0, endRow=20, total=8, pages=1, reasonable=false, pageSizeZero=false}[Student(id=5, name=阿朱, no=2022000005, gender=2, phone=18800160002, idCard=110120000300200005, isCollege=1, address=北京市昌平区建材城西路5号, degree=4, graduationDate=2020-07-01, clazzId=1, violationCount=0, violationScore=0, createTime=2024-11-14T21:22:19, updateTime=2024-11-14T21:22:19, clazzName=JavaEE就业163期), Student(id=6, name=阿紫, no=2022000006, gender=2, phone=18800000034, idCard=110120000300200006, isCollege=1, address=北京市昌平区建材城西路6号, degree=4, graduationDate=2021-07-01, clazzId=2, violationCount=0, violationScore=0, createTime=2024-11-14T21:22:19, updateTime=2024-11-14T21:22:19, clazzName=前端就业90期), Student(id=7, name=游坦之, no=2022000007, gender=1, phone=18800000067, idCard=110120000300200007, isCollege=1, address=北京市昌平区建材城西路7号, degree=4, graduationDate=2022-07-01, clazzId=2, violationCount=0, violationScore=0, createTime=2024-11-14T21:22:19, updateTime=2024-11-14T21:22:19, clazzName=前端就业90期), Student(id=11, name=钟万仇, no=2022000011, gender=1, phone=18800000391, idCard=110120000300200011, isCollege=1, address=北京市昌平区建材城西路11号, degree=4, graduationDate=2021-07-01, clazzId=1, violationCount=0, violationScore=0, createTime=2024-11-14T21:22:19, updateTime=2024-11-15T16:21:24, clazzName=JavaEE就业163期), Student(id=12, name=崔百泉, no=2022000012, gender=1, phone=18800000781, idCard=110120000300200018, isCollege=1, address=北京市昌平区建材城西路12号, degree=4, graduationDate=2022-07-05, clazzId=3, violationCount=6, violationScore=17, createTime=2024-11-14T21:22:19, updateTime=2024-12-13T14:33:58, clazzName=JavaEE就业165期), Student(id=13, name=耶律洪基, no=2022000013, gender=1, phone=18800008901, idCard=110120000300200013, isCollege=1, address=北京市昌平区建材城西路13号, degree=4, graduationDate=2024-07-01, clazzId=2, violationCount=0, violationScore=0, createTime=2024-11-14T21:22:19, updateTime=2024-11-15T16:21:21, clazzName=前端就业90期), Student(id=14, name=天山童姥, no=2022000014, gender=2, phone=18800009201, idCard=110120000300200014, isCollege=1, address=北京市昌平区建材城西路14号, degree=4, graduationDate=2024-07-01, clazzId=1, violationCount=0, violationScore=0, createTime=2024-11-14T21:22:19, updateTime=2024-11-15T16:21:17, clazzName=JavaEE就业163期), Student(id=16, name=李春来, no=2022000016, gender=1, phone=18800008501, idCard=110120000300200016, isCollege=1, address=北京市昌平区建材城西路16号, degree=4, graduationDate=2021-07-01, clazzId=4, violationCount=0, violationScore=0, createTime=2024-11-14T21:22:19, updateTime=2024-11-14T21:22:19, clazzName=JavaEE就业166期)]

        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
