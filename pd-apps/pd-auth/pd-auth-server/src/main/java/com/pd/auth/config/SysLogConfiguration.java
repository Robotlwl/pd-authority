package com.pd.auth.config;

import com.pd.auth.service.common.OptLogService;
import com.pd.log.entity.OptLogDTO;
import com.pd.log.event.SysLogListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.function.Consumer;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@EnableAsync
@Configuration
public class SysLogConfiguration {
    /**
     * 日志记录监听器
     *
     * @param optLogService OptLogService
     * @return SysLogListener
     */
    @Bean
    public SysLogListener sysLogListener(OptLogService optLogService) {
        Consumer<OptLogDTO> consumer = (optLog) -> optLogService.save(optLog);
        return new SysLogListener(consumer);
    }
}
