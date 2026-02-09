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
}
