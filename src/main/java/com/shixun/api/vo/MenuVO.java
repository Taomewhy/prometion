package com.shixun.api.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {

    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    private Boolean open;

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    /**
     * 父菜单id
     */
    private List<MenuVO> children;

    private String url;

}
