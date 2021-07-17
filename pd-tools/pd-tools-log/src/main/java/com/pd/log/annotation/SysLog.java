package com.pd.log.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 描述
     *
     * @return {String}
     */
    String value();

    /**
     * 记录执行参数
     *
     * @return
     */
    boolean recordRequestParam() default true;

    /**
     * 记录返回参数
     *
     * @return
     */
    boolean recordResponseParam() default true;
}
