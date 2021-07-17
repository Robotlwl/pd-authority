package com.pd.auth.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.auth.dto.auth.ResourceQueryDTO;
import com.pd.auth.entity.auth.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询用户拥有的资源权限
     *
     * @param resource ResourceQueryDTO
     * @return List<Resource>
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    List<Long> findMenuIdByResourceId(@Param("resourceIdList") List<Long> resourceIdList);
}
