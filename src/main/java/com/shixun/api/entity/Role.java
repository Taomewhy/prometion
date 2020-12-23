package com.shixun.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends Model<Role> {

    private static final long serialVersionUID=1L;

    /**
     * 角色id
     */
    @TableId(value = "roleid", type = IdType.AUTO)
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


    @Override
    protected Serializable pkVal() {
        return this.roleid;
    }

}
