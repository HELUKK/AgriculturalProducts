package com.seven.rongxiaotong.service;

import com.seven.rongxiaotong.entity.TbPurchase ;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.rongxiaotong.model.MyPurchase;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_purchase(purchase_type ：1买到的，2卖出的

purchase_status：1待付款 2待发货 3待收货 4已收货)】的数据库操作Service
* @createDate 2023-07-02 13:27:22
*/
public interface TbPurchaseService extends IService<TbPurchase> {

    // 添加订单
    void add(TbPurchase purchase);

    // 查询最新订单
    TbPurchase selectNewPurchaseId(String ownName);

    // 查询个人订单
    List<MyPurchase> selectByPurchaseType();
}
