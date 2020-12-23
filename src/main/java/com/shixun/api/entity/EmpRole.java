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
public class EmpRole extends Model<EmpRole> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "erid", type = IdType.AUTO)
    private Integer erid;

    /**
     * 角色表的关联外键
     */
    private Integer roleFk;

    /**
     * 员工表的关联外键
     */
    private Integer empFk;

    /**
     * 预留字段没有实际意义
     */
    private String erdis;


    @Override
    protected Serializable pkVal() {
        return this.erid;
    }

}
