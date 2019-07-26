package com.cqkj.snail.common.exception;

/**
 * 系统内部异常
 */
public class CQException extends Exception {

    private static final long serialVersionUID = 1L;

    public CQException(String message) {
        super(message);
    }
}
