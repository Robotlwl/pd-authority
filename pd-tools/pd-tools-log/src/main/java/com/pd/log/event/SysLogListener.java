package com.pd.log.event;

import com.pd.log.entity.OptLogDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

/**
 * 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {
    //private String database;
    private Consumer<OptLogDTO> consumer;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        OptLogDTO optLog = (OptLogDTO) event.getSource();
        //BaseContextHandler.setDatabase(database);
        consumer.accept(optLog);
    }
}
