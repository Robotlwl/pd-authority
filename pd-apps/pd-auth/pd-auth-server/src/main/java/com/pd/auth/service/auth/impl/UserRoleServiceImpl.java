package com.pd.auth.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pd.auth.entity.auth.UserRole;
import com.pd.auth.mapper.auth.UserRoleMapper;
import com.pd.auth.service.auth.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 业务实现类
 * 角色分配
 * 账号角色绑定
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
