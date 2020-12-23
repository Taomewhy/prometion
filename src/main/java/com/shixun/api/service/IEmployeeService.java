package com.shixun.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shixun.api.dto.EmployeeAddDto;
import com.shixun.api.dto.PageDto;
import com.shixun.api.entity.Employee;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
public interface IEmployeeService extends IService<Employee> {
    IPage<Employee> listByPage(PageDto pageDto);

    boolean addEmployee(EmployeeAddDto employeeAddDto);

    String toUpdatePassword(String originPassword, String newPassword, String checkPassword);
}
