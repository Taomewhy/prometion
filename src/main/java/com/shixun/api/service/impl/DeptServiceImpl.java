package com.shixun.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shixun.api.dto.PageDto;
import com.shixun.api.entity.Dept;
import com.shixun.api.mapper.DeptMapper;
import com.shixun.api.service.IDeptService;
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
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {



    @Override
    public IPage<Dept> listByPage(PageDto pageDto) {
        IPage<Dept> page = new Page<>(pageDto.getPageNum(), pageDto.getSize());
        QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
        IPage<Dept> deptIPage = this.page(page, deptQueryWrapper);
        long pages = deptIPage.getPages();
        return deptIPage;
    }
}
