package com.shixun.api.controller;


import cn.hutool.json.JSONUtil;
import com.shixun.api.dto.PageDto;
import com.shixun.api.entity.Sources;
import com.shixun.api.service.ISourcesService;
import com.shixun.api.vo.MenuVO;
import com.shixun.api.vo.SourcesVo;
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
public class SourcesController {

    private final ISourcesService sourcesService;
    private final PageDto pageDto = new PageDto(1L,2L);

    @RequestMapping("/list-resources")
    public ModelAndView sourcesList(){
        // 获取菜单树
        MenuVO menuTree1 = sourcesService.findMenu();
        String menuTree = JSONUtil.toJsonStr(menuTree1);

        // 获取所有菜单
        List<Sources> menuList = sourcesService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menuTree", menuTree);
        modelAndView.addObject("menulist", menuList);
        modelAndView.setViewName("list-resources");
        return modelAndView;
    }



    @GetMapping(value = "/del-sources")
    public ModelAndView delRole(Integer id){
        sourcesService.removeById(id);
        ModelAndView modelAndView = this.sourcesList();
        return modelAndView;
    }

    @GetMapping(value = "/to-update-sources")
    public ModelAndView toUpdateRole(Integer id){
        SourcesVo sourcesVo = sourcesService.findById(id);
//        Sources sources = sourcesService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sourcesVo", sourcesVo);
        modelAndView.setViewName("update-resources");
        return modelAndView;
    }

    @PostMapping(value = "/update-sources")
    public ModelAndView updateDept(Sources sources){
        sources.updateById();
        ModelAndView modelAndView = this.sourcesList();
        return modelAndView;
    }

    @PostMapping(value = "/add-sources")
    public ModelAndView addSources(Sources sources){
        sources.insert();
        ModelAndView modelAndView = this.sourcesList();
        return modelAndView;
    }

}

