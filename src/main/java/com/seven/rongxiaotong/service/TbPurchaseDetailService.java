package com.seven.rongxiaotong.service;

import com.seven.rongxiaotong.entity.TbPurchaseDetail ;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86152
* @description 针对表【tb_purchase_detail】的数据库操作Service
* @createDate 2023-07-02 14:11:22
*/
public interface TbPurchaseDetailService extends IService<TbPurchaseDetail> {

    // 添加订单详情
    void add(TbPurchaseDetail purchaseDetail);
}
