package com.shixun.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class Employee extends Model<Employee> {

    private static final long serialVersionUID=1L;

    /**
     * 员工编号（主键）
     */
    @TableId(value = "eid", type = IdType.AUTO)
    private Integer eid;

    /**
     * 姓名
     */
    private String ename;

    /**
     * 性别
     */
    private String esex;

    /**
     * 年龄
     */
    private Integer eage;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 入职日期
     */
    private Date hiredate;

    public String getHiredate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (hiredate != null){
            String format = simpleDateFormat.format(hiredate);
            return format;
        }
        return  null;
    }

    /**
     * 身份证号码
     */
    private String pnum;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门表的外键
     */
    private Integer dfk;

    /**
     * 头像
     */
    private String pic;


    @Override
    protected Serializable pkVal() {
        return this.eid;
    }

}
