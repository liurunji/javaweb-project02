package com.itheima.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    @MapKey("pos")
    public List<Map<String,Object>> getEmpJobData();

    @MapKey("gender")
    List<Map<String, Object>> getEmpGenderData();

    @MapKey("clazzName")
    List<Map<String, Object>> getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
