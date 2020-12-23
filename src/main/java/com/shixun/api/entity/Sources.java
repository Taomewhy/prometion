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
public class Sources extends Model<Sources> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 图标
     */
    private String icon;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
