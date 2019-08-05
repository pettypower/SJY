package com.cqkj.snail.common.exception;

import java.util.List;

import com.cqkj.snail.common.domain.ResponseVO;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidException {
    
    /**
     * 统一拦截BEAN校验的错误信息
     * @param exception
     * @return 校验错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO validationException(MethodArgumentNotValidException exception) {
        ResponseVO response = new ResponseVO();
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            StringBuilder errInfo = new StringBuilder();
            for (ObjectError error : errors) {
                errInfo.append(error.getDefaultMessage());
                errInfo.append("\r\n");
            }
            response.status(false);
            response.message(errInfo.toString());
            return response; 
        }

        return response; 
    }
}