package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /*
    分页条件查询班级信息
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /*
    新增班级信息
     */
    void insert(Clazz clazz);

    /*
    根据ID查询班级信息
     */
    Clazz selectById(Integer id);

    /*
    根据id更新班级信息
     */
    void update(Clazz clazz);

    /*
    通过班级id查询对应的班级学生人数
     */
    Integer queryStuNumByClazzId(Integer id);

    /*
    根据id删除班级信息
     */
    void deleteById(Integer id);

    /*
    查询所有班级信息
     */
    List<Clazz> getClazzList();
}
