package com.itheima.exception;

/**
 * 果部门下有员工，则不允许删除该部门
 */
public class DeleteDeptFailedException extends RuntimeException{
    public DeleteDeptFailedException() {
    }

    public DeleteDeptFailedException(String message) {
        super(message);
    }

    public DeleteDeptFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
