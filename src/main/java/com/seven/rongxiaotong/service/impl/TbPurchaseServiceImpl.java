package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.TbPurchase ;
import com.seven.rongxiaotong.service.TbPurchaseService;
import com.seven.rongxiaotong.mapper.TbPurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 86152
* @description 针对表【tb_purchase(purchase_type ：1买到的，2卖出的

purchase_status：1待付款 2待发货 3待收货 4已收货)】的数据库操作Service实现
* @createDate 2023-07-02 13:27:22
*/
@Service
public class TbPurchaseServiceImpl extends ServiceImpl<TbPurchaseMapper, TbPurchase>
    implements TbPurchaseService{

    @Autowired
    private TbPurchaseMapper tbPurchaseMapper;

    @Override
    public void add(TbPurchase purchase) {
        tbPurchaseMapper.insertSelective(purchase);
    }

    @Override
    public TbPurchase selectNewPurchaseId(String ownName) {
        TbPurchase purchase = tbPurchaseMapper.selectNewPurchaseId(ownName);
        return purchase;
    }

}




