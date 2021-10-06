package com.web2019.team4.system.Dao.Mapper;

import com.web2019.team4.system.Dao.Entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<Permission> getPermissionList(@Param("userId") String userId);
}
