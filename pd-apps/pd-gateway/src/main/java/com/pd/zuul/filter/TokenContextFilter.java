package com.pd.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import com.pd.auth.client.properties.AuthClientProperties;
import com.pd.auth.client.utils.JwtTokenClientUtils;
import com.pd.auth.utils.JwtUserInfo;
import com.pd.core.base.R;
import com.pd.core.context.BaseContextConstants;
import com.pd.core.exception.BizException;
import com.pd.core.utils.StrHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 解析token中的用户信息过滤器
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Component
public class TokenContextFilter extends BaseFilter {
    @Autowired
    private AuthClientProperties authClientProperties;
    @Autowired
    private JwtTokenClientUtils jwtTokenClientUtils;

    @Override
    public String filterType() {
        // 前置过滤器
        return PRE_TYPE;
    }

    /**
     * filterOrder：通过int值来定义过滤器的执行顺序，数字越大，优先级越低
     */
    @Override
    public int filterOrder() {
        /**
         * 一定要在org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter
         * 过滤器之后执行, 因为这个过滤器做了路由, 而我们需要这个路由信息来鉴权
         * 这个过滤器会将我们鉴权需要的信息放置在请求上下文中
         */
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // 不进行拦截的地址
        if (isIgnoreToken()) {
            return null;
        }

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // 获取token, 解析, 然后将信息放入 header
        //  获取token
        String userToken =
                request.getHeader(authClientProperties.getUser().getHeaderName());

        // 解析token
        JwtUserInfo userInfo = null;

        try {
            userInfo = jwtTokenClientUtils.getUserInfo(userToken);
        } catch (BizException e) {
            errorResponse(e.getMessage(), e.getCode(), 200);
            return null;
        } catch (Exception e) {
            errorResponse("解析token出错", R.FAIL_CODE, 200);
            return null;
        }

        // 将信息放入header
        if (userInfo != null) {
            addHeader(ctx, BaseContextConstants.JWT_KEY_ACCOUNT,
                    userInfo.getAccount());
            addHeader(ctx, BaseContextConstants.JWT_KEY_USER_ID,
                    userInfo.getUserId());
            addHeader(ctx, BaseContextConstants.JWT_KEY_NAME,
                    userInfo.getName());
            addHeader(ctx, BaseContextConstants.JWT_KEY_ORG_ID,
                    userInfo.getOrgId());
            addHeader(ctx, BaseContextConstants.JWT_KEY_STATION_ID,
                    userInfo.getStationId());
        }
        return null;
    }

    private void addHeader(RequestContext ctx, String name, Object value) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        ctx.addZuulRequestHeader(name, StrHelper.encode(value.toString()));
    }
}
