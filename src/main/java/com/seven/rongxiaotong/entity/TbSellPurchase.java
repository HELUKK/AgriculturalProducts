package com.seven.rongxiaotong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * purchase_type ：2卖出的

purchase_status：1待付款 2待发货 3待收货 4已收货
 * @TableName tb_sell_purchase
 */
@TableName(value ="tb_sell_purchase")
public class TbSellPurchase implements Serializable {
    /**
     * 卖出订单id
     */
    @TableId
    private Integer sellPurchaseId;

    /**
     * 订单id
     */
    private Integer purchaseId;

    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 用户名
     */
    private String ownName;

    /**
     * 类型
     */
    private Integer purchaseType;

    /**
     * 单价
     */
    private BigDecimal uninPrice;

    /**
     * 总价格
     */
    private BigDecimal sumPrice;

    /**
     * 地址
     */
    private String address;

    /**
     * 订单状态
     */
    private Integer purchaseStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 卖出订单id
     */
    public Integer getSellPurchaseId() {
        return sellPurchaseId;
    }

    /**
     * 卖出订单id
     */
    public void setSellPurchaseId(Integer sellPurchaseId) {
        this.sellPurchaseId = sellPurchaseId;
    }

    /**
     * 订单id
     */
    public Integer getPurchaseId() {
        return purchaseId;
    }

    /**
     * 订单id
     */
    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * 用户名
     */
    public String getOwnName() {
        return ownName;
    }

    /**
     * 用户名
     */
    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    /**
     * 类型
     */
    public Integer getPurchaseType() {
        return purchaseType;
    }

    /**
     * 类型
     */
    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    /**
     * 单价
     */
    public BigDecimal getUninPrice() {
        return uninPrice;
    }

    /**
     * 单价
     */
    public void setUninPrice(BigDecimal uninPrice) {
        this.uninPrice = uninPrice;
    }

    /**
     * 总价格
     */
    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    /**
     * 总价格
     */
    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
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
     * 订单状态
     */
    public Integer getPurchaseStatus() {
        return purchaseStatus;
    }

    /**
     * 订单状态
     */
    public void setPurchaseStatus(Integer purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
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
}