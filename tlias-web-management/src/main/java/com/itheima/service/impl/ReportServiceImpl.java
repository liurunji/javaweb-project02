package com.itheima.service.impl;

import com.itheima.mapper.ReportMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    /*
    获取员工职位的统计数据
     */
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> empJobData = reportMapper.getEmpJobData();  //这个集合是list集合里面套着map集合
        //数据库查询到的每一条数据都是一个map集合
        //例如其中一条map集合{<key = pos,value = '教学主管'>,<key = num,value = 1>},这是一条数据，然后每一条数据都是一个map集合，所有的map集合都封装到list集合中
        List<Object> jobList = empJobData.stream().map(dataMap -> dataMap.get("pos")).toList();//map()中把每一个map集合通过键(pos)拿到对应值(教研主管)然后收集到集合中
        List<Object> dataList = empJobData.stream().map(dataMap -> dataMap.get("num")).toList();//map()中把每一个map集合通过键(num)拿到对应值(1)然后收集到集合中
        return new JobOption(jobList, dataList);
    }

    /*
    统计员工性别
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        List<Map<String, Object>> empGenderData = reportMapper.getEmpGenderData();
        return empGenderData;
    }

    /*
    统计班级人数
    实现方法与员工职位的统计数据实现相同
     */
    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String, Object>> list = reportMapper.getStudentCountData();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazzName")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        ClazzOption clazzOption = new ClazzOption(clazzList, dataList);
        return clazzOption;
    }

    /*
    统计学生学历数据
    方法与统计员工性别相同
     */
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> list = reportMapper.getStudentDegreeData();
        //System.out.println(list);    //[{name=初中, value=1}, {name=高中, value=13}, {name=本科, value=15}, {name=硕士, value=10}, {name=大专, value=9}]
        return list;
    }
}
