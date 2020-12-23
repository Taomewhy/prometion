package com.shixun.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shixun.api.entity.Sources;
import com.shixun.api.vo.MenuVO;
import com.shixun.api.vo.SourcesVo;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
public interface ISourcesService extends IService<Sources> {

    /**
     * 多级菜单查询,
     */
    MenuVO findMenu();

    SourcesVo findById(Integer id);

    public MenuVO findUserMenu(String username);
}
