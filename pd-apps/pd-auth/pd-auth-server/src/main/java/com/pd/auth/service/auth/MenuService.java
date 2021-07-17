package com.pd.auth.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pd.auth.entity.auth.Menu;

import java.util.List;

/**
 * 业务接口
 * 菜单
 */
public interface MenuService extends IService<Menu> {
    /**
     * 根据ID删除
     */
    boolean removeByIds(List<Long> ids);

    /**
     * 查询用户可用菜单
     *
     * @param group
     * @param userId
     * @return
     */
    List<Menu> findVisibleMenu(String group, Long userId);
}
