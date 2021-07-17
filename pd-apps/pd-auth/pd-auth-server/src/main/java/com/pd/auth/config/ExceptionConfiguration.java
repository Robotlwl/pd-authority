package com.pd.auth.config;

import com.pd.common.handler.DefaultGlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常配置类
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Configuration
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class ExceptionConfiguration extends DefaultGlobalExceptionHandler {
}
