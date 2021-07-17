package com.pd.auth.service.auth.impl;

import com.pd.auth.service.auth.ValidateCodeService;
import com.pd.common.constant.CacheKey;
import com.pd.core.exception.BizException;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码接口实现
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    @Autowired
    private CacheChannel cache;

    /**
     * 校验验证码
     *
     * @param key   前端上送 key
     * @param value 前端上送待校验值
     * @return
     */
    @Override
    public boolean check(String key, String value) {
        if (StringUtils.isBlank(value)) {
            throw BizException.validFail("请输入验证码");
        }

        // 根据key从缓存中获取验证码
        CacheObject cacheObject = cache.get(CacheKey.CAPTCHA, key);
        if (cacheObject.getValue() == null) {
            throw BizException.validFail("验证码已过期");
        }
        // 比对验证码
        if (!StringUtils.equalsIgnoreCase(value,
                String.valueOf(cacheObject.getValue()))) {
            throw BizException.validFail("验证码不正确");
        }
        // 验证通过, 立即从缓存中删除验证码
        cache.evict(CacheKey.CAPTCHA, key);
        return true;
    }

    /**
     * 实现验证码添加
     *
     * @param key
     * @param response
     * @throws IOException
     */
    @Override
    public void create(String key, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(key)) {
            // 自定义异常抛出
            throw BizException.validFail("验证码key不能为空");
        }

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);

        Captcha captcha = new ArithmeticCaptcha(115, 42);
        captcha.setCharType(2);

        cache.set(CacheKey.CAPTCHA, key, StringUtils.lowerCase(captcha.text()));
        captcha.out(response.getOutputStream());
    }
}
