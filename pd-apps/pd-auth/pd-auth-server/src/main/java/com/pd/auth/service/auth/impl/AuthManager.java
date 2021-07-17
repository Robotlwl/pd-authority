package com.pd.auth.service.auth.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pd.auth.service.auth.ResourceService;
import com.pd.auth.service.auth.UserService;
import com.pd.auth.dto.auth.LoginDTO;
import com.pd.auth.dto.auth.ResourceQueryDTO;
import com.pd.auth.dto.auth.UserDTO;
import com.pd.auth.entity.auth.Resource;
import com.pd.auth.entity.auth.User;
import com.pd.auth.server.utils.JwtTokenServerUtils;
import com.pd.auth.utils.JwtUserInfo;
import com.pd.auth.utils.Token;
import com.pd.core.base.R;
import com.pd.core.exception.code.ExceptionCode;
import com.pd.dozer.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 认证管理器
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Slf4j
@Service
public class AuthManager {
    @Autowired
    private JwtTokenServerUtils jwtTokenServerUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 账号登录
     *
     * @param account
     * @param password
     */
    public R<LoginDTO> login(String account, String password) {
        // 登录验证
        R<User> result = checkUser(account, password);
        if (result.getIsError()) {
            return R.fail(result.getCode(), result.getMsg());
        }
        User user = result.getData();

        // 生成jwt token
        Token token = this.generateUserToken(user);

        List<Resource> resourceList =
                this.resourceService.findVisibleResource(ResourceQueryDTO.builder().userId(user.getId()).build());
        List<String> permissionsList = null;
        if (resourceList != null && resourceList.size() > 0) {
            permissionsList = resourceList
                    .stream()
                    .map(Resource::getCode)
                    .collect(Collectors.toList());
        }
        // 封装数据
        LoginDTO loginDTO = LoginDTO.builder()
                .user(this.dozer.map(user, UserDTO.class))
                .token(token)
                .permissionsList(permissionsList)
                .build();
        return R.success(loginDTO);
    }

    /**
     * 生成jwt token
     *
     * @param user User
     * @return Token
     */
    private Token generateUserToken(User user) {
        JwtUserInfo userInfo = new JwtUserInfo(user.getId(),
                user.getAccount(),
                user.getName(),
                user.getOrgId(),
                user.getStationId());

        Token token = this.jwtTokenServerUtils.generateUserToken(userInfo, null);
        log.info("token={}", token.getToken());
        return token;
    }

    /**
     * 登录验证
     *
     * @param account  账号
     * @param password 密码
     * @return R<User>
     */
    private R<User> checkUser(String account, String password) {
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getAccount, account));

        // 密码加密
        String passwordMd5 = DigestUtils.md5Hex(password);

        if (user == null || !user.getPassword().equals(passwordMd5)) {
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }

        return R.success(user);
    }
}
