package com.pd.auth.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pd.auth.dto.auth.ResourceQueryDTO;
import com.pd.auth.entity.auth.Resource;

import java.util.List;

/**
 * 资源服务业务接口
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
public interface ResourceService extends IService<Resource> {
    /**
     * 查询用户拥有的资源权限
     *
     * @param resource ResourceQueryDTO
     * @return List<Resource>
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    /**
     * 根据菜单id删除资源
     */
    void removeByMenuId(List<Long> menuIds);

    /**
     * 根据资源id 查询菜单id
     */
    List<Long> findMenuIdByResourceId(List<Long> resourceIdList);
}
