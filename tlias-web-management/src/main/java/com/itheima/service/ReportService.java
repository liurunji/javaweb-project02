package com.itheima.service;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    /*
    统计班级人数
     */
    ClazzOption getStudentCountData();

    /**
     * 统计学生学历
     * @return
     */
    List<Map<String, Object>> getStudentDegreeData();
}
