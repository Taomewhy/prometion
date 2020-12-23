package com.shixun.api.dto;

import lombok.Data;

@Data
public class RoleAddDto {
    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 角色描述
     */
    private String roledis;

    /**
     * 是否启用(0 禁用 1启用)
     */
    private Integer status;

//    public Integer getStatus() {
//        return status;
//    }

//    public void setStatus(Boolean status) {
//        if (status){
//            this.status = 1;
//        }else {
//            this.status = 0;
//        }
//
//    }

    private Integer[] sourcesListId;
}
