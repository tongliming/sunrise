package com.sunrise.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: sunrise
 * @description: 忽略统一响应注解定义
 * @author: T.LM
 * @date: 2023-03-01 20:55
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}
