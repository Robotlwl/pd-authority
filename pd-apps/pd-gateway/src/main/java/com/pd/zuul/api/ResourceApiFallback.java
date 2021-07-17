package com.pd.zuul.api;

import com.pd.auth.dto.auth.ResourceQueryDTO;
import com.pd.auth.entity.auth.Resource;
import com.pd.core.base.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Component
public class ResourceApiFallback implements ResourceApi {
    @Override
    public R<List> list() {
        return null;
    }

    @Override
    public R<List<Resource>> visible(ResourceQueryDTO resource) {
        return null;
    }
}
