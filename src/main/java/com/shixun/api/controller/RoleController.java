package com.shixun.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shixun.api.dto.PageDto;
import com.shixun.api.dto.RoleAddDto;
import com.shixun.api.entity.Role;
import com.shixun.api.entity.Sources;
import com.shixun.api.service.IRoleService;
import com.shixun.api.service.ISourcesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
@RestController
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;
    private final ISourcesService sourcesService;
    private final PageDto pageDto = new PageDto(1L,2L);

    @RequestMapping("/list-role")
    public ModelAndView roleList(PageDto pageDto){
        // 获取角色列表
        if (pageDto.getPageNum() == null){
            pageDto = this.pageDto;
        }
        IPage<Role> roleIPage = roleService.listByPage(pageDto);
        // 获取权限列表
        List<Sources> menuList = sourcesService.list();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("roleIPage", roleIPage);
        modelAndView.addObject("menuList", menuList);
        modelAndView.setViewName("list-role");
        return modelAndView;
    }
    @GetMapping(value = "/del-role")
    public ModelAndView delRole(Integer roleid){
        roleService.removeById(roleid);
        ModelAndView modelAndView = this.roleList(this.pageDto);
        return modelAndView;
    }

    @GetMapping(value = "/to-update-role")
    public ModelAndView toUpdateRole(Integer roleno){
        Role role = roleService.getById(roleno);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role", role);
        modelAndView.setViewName("update-role");
        return modelAndView;
    }

    @PostMapping(value = "/update-role")
    public ModelAndView updateRole(Role role){
        role.updateById();
        ModelAndView modelAndView = this.toUpdateRole(role.getRoleid());
        return modelAndView;
    }

    @PostMapping(value = "/add-role")
    public ModelAndView addRole(RoleAddDto roleAddDto){
        this.roleService.insertRole(roleAddDto);
        ModelAndView modelAndView = this.roleList(this.pageDto);
        return modelAndView;
    }
}

