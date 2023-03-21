package com.sunrise.advice;

import com.sunrise.annotation.IgnoreResponseAdvice;
import com.sunrise.model.base.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @program: sunrise
 * @description: 实现统一响应
 * @author: T.LM
 * @date: 2023-03-01 21:02
 **/
@RestControllerAdvice("com.sunrise")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /************************************************
     * @Description: 判断是否需要对响应进行处理
     * @Param: [returnType, converterType]
     * @return: boolean
     * @Author: T.LM
     * @Date: 2023/3/1 21:10
     * ***********************************************/
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if (returnType.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // 定义最终的返回对象
        CommonResponse<Object> commonResponse = new CommonResponse<>(0, "success");

        if (null == body) {
            return commonResponse;
        } else if (body instanceof CommonResponse) {
            commonResponse = (CommonResponse<Object>) body;
        } else {
            commonResponse.setData(body);
        }

        return commonResponse;
    }
}
