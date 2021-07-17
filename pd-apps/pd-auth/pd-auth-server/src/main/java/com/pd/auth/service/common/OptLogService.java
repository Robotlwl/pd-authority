package com.pd.auth.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pd.auth.entity.common.OptLog;
import com.pd.log.entity.OptLogDTO;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
public interface OptLogService extends IService<OptLog> {
    /**
     * 保存日志
     *
     * @param entity OptLogDTO
     * @return boolean
     */
    boolean save(OptLogDTO entity);
}
