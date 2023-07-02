package com.seven.rongxiaotong.service;

import com.seven.rongxiaotong.entity.TbSellPurchase ;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_sell_purchase(purchase_type ：2卖出的

purchase_status：1待付款 2待发货 3待收货 4已收货)】的数据库操作Service
* @createDate 2023-07-02 14:11:30
*/
public interface TbSellPurchaseService extends IService<TbSellPurchase> {

    // 添加卖出订单
    void add(TbSellPurchase purchase);

    // 查询个人卖出订单
    List<TbSellPurchase> selectByName();

}
