package com.seven.rongxiaotong.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
public class User implements Serializable {
    /**
     * 用户名,用户使用用户名和密码进行登录
     */
    @TableId
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份证号
     */
    private String identityNum;

    /**
     * 地址
     */
    private String address;

    /**
     * 角色
     */
    private String role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 积分
     */
    private Integer integral;

    /**
     * 信誉
     */
    private Integer credit;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 真实姓名
     */
    private String realName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户名,用户使用用户名和密码进行登录
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名,用户使用用户名和密码进行登录
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
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
        this.phone = phone;
    }

    /**
     * 身份证号
     */
    public String getIdentityNum() {
        return identityNum;
    }

    /**
     * 身份证号
     */
    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    /**
     * 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 角色
     */
    public String getRole() {
        return role;
    }

    /**
     * 角色
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 积分
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 信誉
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * 信誉
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    /**
     * 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        this.realName = realName;
    }
}