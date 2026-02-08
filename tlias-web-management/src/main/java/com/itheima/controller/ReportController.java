package com.itheima.controller;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/report")
@Slf4j
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("员工职位信息统计");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("员工性别统计");
        List<Map<String,Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }
}
