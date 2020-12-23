package com.shixun.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shixun.api.dto.EmployeeAddDto;
import com.shixun.api.dto.PageDto;
import com.shixun.api.entity.EmpRole;
import com.shixun.api.entity.Employee;
import com.shixun.api.mapper.EmployeeMapper;
import com.shixun.api.service.IEmpRoleService;
import com.shixun.api.service.IEmployeeService;
import com.shixun.api.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@RequiredArgsConstructor
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService , UserDetailsService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Autowired
    private IEmpRoleService empRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.lambda().eq(Employee::getUsername, s);

        Employee employee = employeeMapper.selectOne(employeeQueryWrapper);
        if (employee != null){
            // 获取用户的角色
            QueryWrapper<EmpRole> empRoleQueryWrapper = new QueryWrapper<>();
            empRoleQueryWrapper.lambda().eq(EmpRole::getEmpFk, employee.getEid());
            List<EmpRole> roleList = empRoleService.list(empRoleQueryWrapper);
            String[] roleStrings = roleList
                    .stream()
                    .map(empRole -> {
                        return empRole.getRoleFk().toString();
                    })
                    .collect(Collectors.toList())
                    .toArray(new String[roleList.size()]);


            //生成UserDetails
            UserDetails userDetails = User.withUsername(employee.getUsername()).password(employee.getPassword()).roles(roleStrings).build();
            return userDetails;
        }
        return null;
    }

    public IPage<Employee> listByPage(PageDto pageDto){
        IPage<Employee> page = new Page(pageDto.getPageNum(),pageDto.getSize());
        IPage<Employee> employeeIPage = employeeMapper.selectPage(page, new QueryWrapper<Employee>());
        return employeeIPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEmployee(EmployeeAddDto employeeAddDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeAddDto, employee);
        boolean insertEmpSuccess = employee.insert();
        if (insertEmpSuccess) {
            // 往emp-role表添加关系
            EmpRole empRole = new EmpRole();
            empRole.setEmpFk(employee.getEid());
            empRole.setRoleFk(employeeAddDto.getRoleId());
            boolean insertEmpRoleSuccess = empRole.insert();
            return insertEmpRoleSuccess;

        }
        return false;
    }

    @Override
    public String toUpdatePassword(String originPassword, String newPassword, String checkPassword) {
        QueryWrapper<Employee> employeeQueryWrapper= new QueryWrapper<>();
        employeeQueryWrapper.lambda().eq(Employee::getUsername, SecurityUtils.getUsername())
                .eq(Employee::getPassword, originPassword);

        Employee user = this.getOne(employeeQueryWrapper);
        if (user == null || user.getUsername() == null){
            return "密码错误";
        }
        if (!(user.getPassword().equals(originPassword))){
            return "密码错误";
        }
        if (!newPassword.equals(checkPassword)){
            return "输入的两次密码不一致";
        }

        user.setPassword(newPassword);

        boolean isSuccess = user.updateById();
        if (isSuccess){
            return "修改成功";
        }
        return "修改失败";
    }
}
