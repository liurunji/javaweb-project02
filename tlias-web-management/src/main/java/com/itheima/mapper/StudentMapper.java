package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    /*
    查询所有学生信息及所在班级的名称
     */
    List<Student> getInfo(StudentQueryParam studentQueryParam);
}
