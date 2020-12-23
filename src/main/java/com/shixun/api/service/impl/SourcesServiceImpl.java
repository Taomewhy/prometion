package com.shixun.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shixun.api.entity.Sources;
import com.shixun.api.mapper.SourcesMapper;
import com.shixun.api.service.IEmpRoleService;
import com.shixun.api.service.ISourcesService;
import com.shixun.api.vo.MenuVO;
import com.shixun.api.vo.SourcesVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
@Service

public class SourcesServiceImpl extends ServiceImpl<SourcesMapper, Sources> implements ISourcesService {

    @Autowired
    private  IEmpRoleService empRoleService;
    @Resource
    private  SourcesMapper sourcesMapper;

    @Override
    public MenuVO findMenu() {
        // 获取父级菜单
        QueryWrapper<Sources> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.lambda().isNull(Sources::getPid);
        Sources sources = sourcesMapper.selectOne(menuQueryWrapper);
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(sources, menuVO);
        recursionMenu(menuVO);
        return menuVO;
    }

    public MenuVO findUserMenu(String username) {
        // 获取父级菜单
        QueryWrapper<Sources> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.lambda().isNull(Sources::getPid);
        Sources sources = sourcesMapper.selectOne(menuQueryWrapper);
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(sources, menuVO);
        // 角色id
        Integer roleId = empRoleService.findRoleIdByUsername(username);
        this.recursionUserMenu(menuVO, roleId);
        return menuVO;
    }

    @Override
    public SourcesVo findById(Integer id) {
        Sources sources = sourcesMapper.selectById(id);
        SourcesVo sourcesVo = new SourcesVo();
        BeanUtils.copyProperties(sources, sourcesVo);
        Sources partentSources = sourcesMapper.selectById(sources.getPid());
        sourcesVo.setPname(partentSources.getName());
        return sourcesVo;
    }

    public void recursionMenu(MenuVO menuVO){
        QueryWrapper<Sources> sourcesQueryWrapper = new QueryWrapper<>();
        sourcesQueryWrapper.lambda().eq(Sources::getPid, menuVO.getId());
        List<Sources> sources = sourcesMapper.selectList(sourcesQueryWrapper);
        if (sources == null || sources.size() < 1){
            return;
        }
        List<MenuVO> menuVOList = sources.stream()
                .map(sources1 -> {
                    MenuVO menu = new MenuVO();
                    BeanUtils.copyProperties(sources1, menu);

                    return menu;
                }).collect(Collectors.toList());
        menuVO.setChildren(menuVOList);
        menuVO.setOpen(true);
        menuVOList.stream()
                .forEach(menuVO1 -> {
                    recursionMenu(menuVO1);
                });

    }

    public void recursionUserMenu(MenuVO menuVO, Integer roleId){
        List<Sources> sources = sourcesMapper.findUserMenuVO(roleId, menuVO.getId());
        if (sources == null || sources.size() < 1){
            return;
        }
        List<MenuVO> menuVOList = sources.stream()
                .map(sources1 -> {
                    MenuVO menu = new MenuVO();
                    BeanUtils.copyProperties(sources1, menu);

                    return menu;
                }).collect(Collectors.toList());
        menuVO.setChildren(menuVOList);
        menuVO.setOpen(true);
        menuVOList.stream()
                .forEach(menuVO1 -> {
                    recursionUserMenu(menuVO1, roleId);
                });

    }

}
