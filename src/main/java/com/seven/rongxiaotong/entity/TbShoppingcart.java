package com.seven.rongxiaotong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tb_shoppingcart
 */
@TableName(value ="tb_shoppingcart")
public class TbShoppingcart implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer shoppingId;

    /**
     * 
     */
    private Integer orderId;

    /**
     * 
     */
    private Integer count;

    /**
     * 
     */
    private String ownName;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getShoppingId() {
        return shoppingId;
    }

    /**
     * 
     */
    public void setShoppingId(Integer shoppingId) {
        this.shoppingId = shoppingId;
    }

    /**
     * 
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     */
    public String getOwnName() {
        return ownName;
    }

    /**
     * 
     */
    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    /**
     * 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}