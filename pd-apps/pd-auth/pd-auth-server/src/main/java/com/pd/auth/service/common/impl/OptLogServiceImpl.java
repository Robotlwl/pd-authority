package com.pd.auth.service.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pd.auth.entity.common.OptLog;
import com.pd.auth.mapper.common.OptLogMapper;
import com.pd.auth.service.common.OptLogService;
import com.pd.dozer.DozerUtils;
import com.pd.log.entity.OptLogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志业务实现类
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Slf4j
@Service
public class OptLogServiceImpl extends ServiceImpl<OptLogMapper, OptLog> implements OptLogService {
    @Autowired
    DozerUtils dozer;

    @Override
    public boolean save(OptLogDTO entity) {
        return super.save(dozer.map(entity, OptLog.class));
    }
}
