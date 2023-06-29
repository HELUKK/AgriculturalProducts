package com.seven.rongxiaotong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName tb_order
 */
@TableName(value ="tb_order")
public class TbOrder implements Serializable {
    /**
     * 商品id
     */
    @TableId
    private Integer orderId;

    /**
     * 标题
     */
    private String title;

    /**
     * 期望价格
     */
    private BigDecimal price;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     */
    private Integer orderStatu;

    /**
     * 类型
     */
    private String type;

    /**
     * 
     */
    private String picture;

    /**
     * 发布者
     */
    private String ownName;

    /**
     * 合作人名字
     */
    private String cooperationName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 
     */
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 商品id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 期望价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 期望价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 状态
     */
    public Integer getOrderStatu() {
        return orderStatu;
    }

    /**
     * 状态
     */
    public void setOrderStatu(Integer orderStatu) {
        this.orderStatu = orderStatu;
    }

    /**
     * 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 发布者
     */
    public String getOwnName() {
        return ownName;
    }

    /**
     * 发布者
     */
    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    /**
     * 合作人名字
     */
    public String getCooperationName() {
        return cooperationName;
    }

    /**
     * 合作人名字
     */
    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName;
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
     * 
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    // 检查标题的属性
    public boolean checkTitle(String title){
        if (title != null && title.length() >=2 && title.length() <= 8) {
            return true;
        }else {
            return false;
        }
    }
}