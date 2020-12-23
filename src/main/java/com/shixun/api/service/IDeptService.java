package com.shixun.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shixun.api.dto.PageDto;
import com.shixun.api.entity.Dept;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
public interface IDeptService extends IService<Dept> {

    IPage<Dept> listByPage(PageDto pageDto);
}
