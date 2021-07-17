package com.pd.zuul.filter;

import cn.hutool.core.util.StrUtil;
import com.netflix.zuul.context.RequestContext;
import com.pd.auth.dto.auth.ResourceQueryDTO;
import com.pd.auth.entity.auth.Resource;
import com.pd.common.constant.CacheKey;
import com.pd.core.base.R;
import com.pd.core.context.BaseContextConstants;
import com.pd.core.exception.code.ExceptionCode;
import com.pd.zuul.api.ResourceApi;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限验证过滤器
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Slf4j
@Component
public class AccessFilter extends BaseFilter {
    @Autowired
    private CacheChannel cacheChannel;
    @Autowired
    private ResourceApi resourceApi;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 验证当前用户是否拥有某个URI的访问权限
     */
    @Override
    public Object run() {
        // 不进行拦截的地址
        if (isIgnoreToken()) {
            return null;
        }

        RequestContext requestContext = RequestContext.getCurrentContext();
        String requestURI = requestContext.getRequest().getRequestURI();
        requestURI = StrUtil.subSuf(requestURI, zuulPrefix.length());
        requestURI = StrUtil.subSuf(requestURI, requestURI.indexOf("/", 1));
        String method = requestContext.getRequest().getMethod();
        String permission = method + requestURI;

        // 从缓存中获取所有需要进行鉴权的资源
        CacheObject resourceNeed2AuthObject = cacheChannel.get(CacheKey.RESOURCE, CacheKey.RESOURCE_NEED_TO_CHECK);
        List<String> resourceNeed2Auth = (List<String>) resourceNeed2AuthObject.getValue();
        if (resourceNeed2Auth == null) {
            resourceNeed2Auth = resourceApi.list().getData();
            if (resourceNeed2Auth != null) {
                cacheChannel.set(CacheKey.RESOURCE, CacheKey.RESOURCE_NEED_TO_CHECK, resourceNeed2Auth);
            }
        }
        if (resourceNeed2Auth != null) {
            long count = resourceNeed2Auth.stream().filter((String r) -> {
                return permission.startsWith(r);
            }).count();
            if (count == 0) {
                // 未知请求
                errorResponse(ExceptionCode.UNAUTHORIZED.getMsg(), ExceptionCode.UNAUTHORIZED.getCode(), 200);
                return null;
            }
        }

        String userId = requestContext.getZuulRequestHeaders().
                get(BaseContextConstants.JWT_KEY_USER_ID);
        CacheObject cacheObject = cacheChannel.get(CacheKey.USER_RESOURCE, userId);
        List<String> userResource = (List<String>) cacheObject.getValue();
        // 如果从缓存获取不到当前用户的资源权限, 需要查询数据库获取, 然后再放入缓存
        if (userResource == null) {
            ResourceQueryDTO resourceQueryDTO = new ResourceQueryDTO();
            resourceQueryDTO.setUserId(new Long(userId));
            //通过Feign调用服务，查询当前用户拥有的权限
            R<List<Resource>> result = resourceApi.visible(resourceQueryDTO);
            if (result.getData() != null) {
                List<Resource> userResourceList = result.getData();
                userResource = userResourceList.stream().map((Resource r) -> {
                    return r.getMethod() + r.getUrl();
                }).collect(Collectors.toList());
                cacheChannel.set(CacheKey.USER_RESOURCE, userId, userResource);
            }
        }

        long count = userResource.stream().filter((String r) -> {
            return permission.startsWith(r);
        }).count();

        if (count > 0) {
            // 有访问权限
            return null;
        } else {
            log.warn("用户{}没有访问{}资源的权限", userId, method + requestURI);
            errorResponse(ExceptionCode.UNAUTHORIZED.getMsg(), ExceptionCode.UNAUTHORIZED.getCode(), 200);
        }
        return null;
    }
}
