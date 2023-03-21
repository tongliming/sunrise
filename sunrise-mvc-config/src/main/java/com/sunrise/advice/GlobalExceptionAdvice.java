package com.sunrise.advice;

import com.sunrise.exception.ParameterException;
import com.sunrise.model.base.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: sunrise
 * @description: 全局异常捕获处理
 * @author: T.LM
 * @date: 2023-03-01 21:21
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler(value = ParameterException.class)
    public CommonResponse<String> handlerParameterException(HttpServletRequest request,
                                                   Exception exception) {
        ParameterException parameterException = (ParameterException) exception;
        CommonResponse<String> commonResponse = new CommonResponse<>(
                parameterException.getErrorCode(), parameterException.getMessage()
        );
        log.error("sunrise service parameter has error: [{}]", parameterException.getMessage(), parameterException);
        return commonResponse;
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handlerException(HttpServletRequest request,
                                                           Exception exception) {

        CommonResponse<String> commonResponse = new CommonResponse<>(
                -1, "business error"
        );
        commonResponse.setData(exception.getMessage());
        log.error("sunrise service has error: [{}]", exception.getMessage(), exception);
        return commonResponse;
    }
}
