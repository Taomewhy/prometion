package com.shixun.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shixun.api.dto.EmployeeAddDto;
import com.shixun.api.dto.PageDto;
import com.shixun.api.entity.Dept;
import com.shixun.api.entity.Employee;
import com.shixun.api.entity.Role;
import com.shixun.api.service.IDeptService;
import com.shixun.api.service.IEmployeeService;
import com.shixun.api.service.IRoleService;
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
public class EmployeeController {

    private final IEmployeeService employeeService;
    private final IDeptService deptService;
    private final IRoleService roleService;
    private final PageDto pageDto = new PageDto(1L,2L);

    @RequestMapping("/list-emp")
    public ModelAndView employeeList(PageDto pageDto){
        if (pageDto.getPageNum() == null){
            pageDto = this.pageDto;
        }
        IPage<Employee> employeeIPage = employeeService.listByPage(pageDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employeeIPage",employeeIPage);
        modelAndView.setViewName("list-employee");
        return modelAndView;
    }

    @RequestMapping("/delete-emp")
    public ModelAndView deleteEmp(Integer eid){
        employeeService.removeById(eid);
        ModelAndView modelAndView = this.employeeList(pageDto);
        return modelAndView;
    }

    @PostMapping(value = "/add-emp")
    public ModelAndView addEmp(EmployeeAddDto employeeAddDto){
        System.out.println("eid:"+employeeAddDto);
        employeeService.addEmployee(employeeAddDto);
        ModelAndView modelAndView = this.employeeList(this.pageDto);
        return modelAndView;
    }

    @RequestMapping("to-add-emp")
    public ModelAndView toAddView(Integer eid , PageDto pageDto){
        employeeService.removeById(eid);
        // 获取部门列表
        List<Dept> deptList = deptService.list();
        // 获取角色列表
        List<Role> roleList = roleService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("deptList",deptList);
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("save-employee");
        return modelAndView;
    }

    @GetMapping(value = "/to-update-emp")
    public ModelAndView toUpdateDept(Integer eid){
        Employee employee = employeeService.getById(eid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employee", employee);
        modelAndView.setViewName("show-employee");
        return modelAndView;
    }

    @PostMapping(value = "/update-password")
    public ModelAndView updatePassword(String originPassword, String newPassword, String checkPassword){
        String result = employeeService.toUpdatePassword(originPassword,newPassword,checkPassword);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result", result);
        modelAndView.setViewName("update-password");
        return modelAndView;
    }

    @GetMapping(value = "/to-update-password")
    public ModelAndView toUpdatePassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update-password");
        return modelAndView;
    }
}

