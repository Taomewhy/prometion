package com.shixun.api.vo;

import lombok.Data;

@Data
public class SourcesVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 资源备注
     */
    private String remark;

    /**
     * 父菜单id
     */
    private Integer pid;

    /**
     * 父菜单名称
     */
    private String pname;

    /**
     * 图标
     */
    private String icon;
}
