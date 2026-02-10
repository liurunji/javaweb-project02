package com.itheima.exception;
/*
班级删除失败异常处理类
当班级下还关联有学生时不能删除该班级
 */
public class DeleteClazzFailedException extends RuntimeException{
    public DeleteClazzFailedException() {
    }

    public DeleteClazzFailedException(String message) {
        super(message);
    }

    public DeleteClazzFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
