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
public class Dept extends Model<Dept> {

    private static final long serialVersionUID=1L;

    /**
     * 部门编号
     */
    @TableId(value = "deptno", type = IdType.AUTO)
    private Integer deptno;

    /**
     * 部门名称
     */
    private String dname;

    /**
     * 部门位置
     */
    private String local;


    @Override
    protected Serializable pkVal() {
        return this.deptno;
    }

}
