package com.pd.auth.client.utils;

import com.pd.auth.client.properties.AuthClientProperties;
import com.pd.auth.utils.JwtHelper;
import com.pd.auth.utils.JwtUserInfo;
import com.pd.core.exception.BizException;
import lombok.AllArgsConstructor;

/**
 * JwtToken 客户端工具
 */
@AllArgsConstructor
public class JwtTokenClientUtils {
    /**
     * 用于认证服务的客户端使用(如网关), 在网关获取到token后,
     * 调用此工具类进行token解析.
     * 客户端一般只需要解析token即可.
     */
    private AuthClientProperties authClientProperties;

    /**
     * 解析token
     *
     * @param token
     * @return
     * @throws BizException
     */
    public JwtUserInfo getUserInfo(String token) throws BizException {
        AuthClientProperties.TokenInfo userTokenInfo = authClientProperties.getUser();
        return JwtHelper.getJwtFromToken(token, userTokenInfo.getPubKey());
    }
}
