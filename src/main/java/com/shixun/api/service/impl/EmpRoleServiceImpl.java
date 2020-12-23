package com.shixun.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shixun.api.entity.EmpRole;
import com.shixun.api.entity.Employee;
import com.shixun.api.mapper.EmpRoleMapper;
import com.shixun.api.service.IEmpRoleService;
import com.shixun.api.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EmpRoleServiceImpl extends ServiceImpl<EmpRoleMapper, EmpRole> implements IEmpRoleService {

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public Integer findRoleIdByUsername(String username) {
        // 根据用户名获取用户信息
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.lambda().eq(Employee::getUsername, username);
        Employee employee = employeeService.getOne(employeeQueryWrapper);

        QueryWrapper<EmpRole> empRoleQueryWrapper = new QueryWrapper<>();
        empRoleQueryWrapper.lambda().eq(EmpRole::getEmpFk, employee.getEid());
        EmpRole empRole = this.getOne(empRoleQueryWrapper);
        return empRole.getRoleFk();
    }
}
