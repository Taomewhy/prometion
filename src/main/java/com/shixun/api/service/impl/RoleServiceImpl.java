package com.shixun.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shixun.api.dto.PageDto;
import com.shixun.api.dto.RoleAddDto;
import com.shixun.api.entity.Role;
import com.shixun.api.entity.RoleSources;
import com.shixun.api.mapper.RoleMapper;
import com.shixun.api.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public IPage<Role> listByPage(PageDto pageDto) {
        IPage<Role> page = new Page<>(pageDto.getPageNum(), pageDto.getSize());
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        IPage<Role> roleIPage = this.page(page, roleQueryWrapper);
        return roleIPage;
    }

    /**
     * 新增角色并为该角色设置权限
     * @param roleAddDto
     */
    @Override
    public void insertRole(RoleAddDto roleAddDto) {
        // 新增角色
        Role role = new Role();
        BeanUtils.copyProperties(roleAddDto, role);
        role.insert();
        // 为角色设置权限
        Integer[] sourcesListId = roleAddDto.getSourcesListId();
        for (int i = 0; i < sourcesListId.length; i++) {
            RoleSources roleSources = new RoleSources();
            roleSources.setResourcesFk(sourcesListId[i]);
            roleSources.setRoleFk(role.getRoleid());
            roleSources.insert();
        }
    }
}
