package com.seven.rongxiaotong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 专家信息表
 * @TableName expert
 */
@TableName(value ="expert")
public class Expert implements Serializable {
    /**
     * 用户名
     */
    @TableId
    private String userName;

    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    /**
     * 手机号
     */
    @NotBlank(message = "电话不能为空")
    private String phone;

    /**
     * 从事专业
     */
    @NotBlank(message = "从事专业不能为空")
    private String profession;

    /**
     * 职位
     */
    @NotBlank(message = "职位不能为空")
    private String position;

    /**
     * 所属单位
     */
    @NotBlank(message = "所属单位不能为空")
    private String belong;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 从事专业
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 从事专业
     */
    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    /**
     * 职位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 职位
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 所属单位
     */
    public String getBelong() {
        return belong;
    }

    /**
     * 所属单位
     */
    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }
}