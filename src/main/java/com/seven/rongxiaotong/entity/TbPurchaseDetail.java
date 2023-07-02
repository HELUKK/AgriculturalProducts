package com.seven.rongxiaotong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @TableName tb_purchase_detail
 */
@TableName(value ="tb_purchase_detail")
public class TbPurchaseDetail implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer detailId;

    /**
     * 订单id
     */
    private Integer purchaseId;

    /**
     * 商品id
     */
    private Integer orderId;

    /**
     * 单价
     */
    private BigDecimal uninPrice;

    /**
     * 总价格
     */
    private BigDecimal sumPrice;

    /**
     * 数量
     */
    private Integer count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Integer getDetailId() {
        return detailId;
    }

    /**
     * id
     */
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
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
     * 数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}