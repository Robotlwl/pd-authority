package com.pd.auth.service.auth;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
public interface ValidateCodeService {
    /**
     * 校验验证码
     *
     * @param key   前端上送 key
     * @param value 前端上送待校验值
     */
    boolean check(String key, String value);

    /**
     * 生成验证码
     *
     * @param key
     * @param response
     * @throws IOException
     */
    void create(String key, HttpServletResponse response) throws IOException;
}
