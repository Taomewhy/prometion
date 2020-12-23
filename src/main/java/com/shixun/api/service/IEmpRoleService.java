package com.shixun.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shixun.api.entity.EmpRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
public interface IEmpRoleService extends IService<EmpRole> {

    Integer findRoleIdByUsername(String username);

}
