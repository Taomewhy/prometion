package com.shixun.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shixun.api.dto.PageDto;
import com.shixun.api.entity.Dept;
import com.shixun.api.service.IDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
@Controller
@RequiredArgsConstructor
public class DeptController {

    private final IDeptService deptService;
    private final PageDto pageDto = new PageDto(1L,2L);

    @RequestMapping("/list-dept")
    public ModelAndView deptList(PageDto pageDto){
        // 获取部门列表
        if (pageDto.getPageNum() == null){
            pageDto = this.pageDto;
        }
        IPage<Dept> deptIPage = deptService.listByPage(pageDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("deptIPage", deptIPage);
        modelAndView.setViewName("list-dept");
        return modelAndView;
    }

    @PostMapping(value = "/add-dept")
    public ModelAndView addDept(Dept dept){
        dept.insert();
        ModelAndView modelAndView = this.deptList(this.pageDto);
        return modelAndView;
    }

    @GetMapping(value = "/del-dept")
    public ModelAndView delDept(Integer deptno){
        deptService.removeById(deptno);
        ModelAndView modelAndView = this.deptList(this.pageDto);
        return modelAndView;
    }

    @GetMapping(value = "/to-update-dept")
    public ModelAndView toUpdateDept(Integer deptno){
        Dept dept = deptService.getById(deptno);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dept", dept);
        modelAndView.setViewName("update-dept");
        return modelAndView;
    }

    @PostMapping(value = "/update-dept")
    public ModelAndView updateDept(Dept dept){
        dept.updateById();
        ModelAndView modelAndView = this.toUpdateDept(dept.getDeptno());
        return modelAndView;
    }





}

