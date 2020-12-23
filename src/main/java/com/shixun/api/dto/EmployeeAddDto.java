package com.shixun.api.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EmployeeAddDto {

    /**
     * 员工编号（主键）
     */
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;

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

    private  Integer roleId;
}
