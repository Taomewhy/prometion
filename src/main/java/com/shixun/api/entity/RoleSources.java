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
public class RoleSources extends Model<RoleSources> {

    private static final long serialVersionUID=1L;

    /**
     * 主键字段
     */
    @TableId(value = "rsid", type = IdType.AUTO)
    private Integer rsid;

    /**
     * 预留字段无实际意义
     */
    private String rsdis;

    /**
     * 权限表的关联字段
     */
    private Integer resourcesFk;

    /**
     * 角色表的关联字段
     */
    private Integer roleFk;


    @Override
    protected Serializable pkVal() {
        return this.rsid;
    }

}
