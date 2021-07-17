package com.pd.zuul.api;

import com.pd.auth.dto.auth.ResourceQueryDTO;
import com.pd.auth.entity.auth.Resource;
import com.pd.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@FeignClient(name = "${pinda.feign.authority-server:pd-auth-server}", fallback = ResourceApiFallback.class)
public interface ResourceApi {
    /**
     * 获取所有需要鉴权的资源
     *
     * @return
     */
    @GetMapping("/resource/list")
    R<List> list();

    /**
     * 查询当前登录用户拥有的资源权限
     *
     * @param resource
     * @return
     */
    @GetMapping("/resource")
    R<List<Resource>> visible(ResourceQueryDTO resource);
}
