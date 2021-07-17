package com.pd.auth.controller.auth;

import com.pd.auth.dto.auth.LoginDTO;
import com.pd.auth.dto.auth.LoginParamDTO;
import com.pd.auth.service.auth.ValidateCodeService;
import com.pd.auth.service.auth.impl.AuthManager;
import com.pd.core.base.BaseController;
import com.pd.core.base.R;
import com.pd.core.exception.BizException;
import com.pd.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Slf4j
@RestController
@RequestMapping("/anno")
@Api(value = "UserAuthController", tags = "登录")
public class LoginController extends BaseController {
    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    private AuthManager authManager;

    /**
     * 登录认证
     */
    @SysLog("用户登录")
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/login")
    public R<LoginDTO> login(@Validated @RequestBody LoginParamDTO login) throws BizException {
        log.info("account={}", login.getAccount());
        if (this.validateCodeService.check(login.getKey(), login.getCode())) {
            return this.authManager.login(login.getAccount(), login.getPassword());
        }
        return this.success(null);
    }

    @ApiOperation(value = "验证码", notes = "验证码")
    @GetMapping(value = "/captcha", produces = "image/png")
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        this.validateCodeService.create(key, response);
    }
}
