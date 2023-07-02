package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.TbSellPurchase ;
import com.seven.rongxiaotong.service.TbSellPurchaseService;
import com.seven.rongxiaotong.mapper.TbSellPurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 86152
* @description 针对表【tb_sell_purchase(purchase_type ：2卖出的

purchase_status：1待付款 2待发货 3待收货 4已收货)】的数据库操作Service实现
* @createDate 2023-07-02 14:11:30
*/
@Service
public class TbSellPurchaseServiceImpl extends ServiceImpl<TbSellPurchaseMapper, TbSellPurchase>
    implements TbSellPurchaseService{

    @Resource
    private TbSellPurchaseMapper tbSellPurchaseMapper;

    @Override
    public void add(TbSellPurchase sellPurchase) {
        tbSellPurchaseMapper.insertSelective(sellPurchase);
    }

}




