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

    /*
    插入一条学生信息
     */
    void insert(Student student);

    /*
    根据id查询学生信息
     */
    Student queryById(Integer id);

    /*
    修改学生信息
     */
    void update(Student student);

    /*
    删除学生信息
     */
    void deleteByIds(List<Integer> ids);

    /*
    处理违纪：违纪处理一次，需要将违纪次数+1，违纪扣分+前端输入的分数。
    注意也要更新最后更新时间字段
     */
    void updateViolation(Integer id, Integer score);
}
