package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice   //注解1
public class GlobalExceptionHandler {
    @ExceptionHandler  //注解2
    public Result handleException(Exception e) {
        log.error("出错了！", e);
        return Result.error("出错了，请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        //### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '13309090006' for key 'emp.phone'
        //从错误信息里提取出电话号码
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String s = message.substring(i);
        String[] arr = s.split(" ");
        return Result.error(arr[2] + "已存在");
    }

    @ExceptionHandler
    public Result handleDeleteFailedException(DeleteClazzFailedException e){
        return Result.error("对不起, 该班级下有学生, 不能直接删除");
    }
}
