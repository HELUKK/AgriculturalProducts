package com.seven.rongxiaotong.mapper;

import com.seven.rongxiaotong.entity.TbSellPurchase ;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_sell_purchase(purchase_type ：2卖出的

purchase_status：1待付款 2待发货 3待收货 4已收货)】的数据库操作Mapper
* @createDate 2023-07-02 14:11:30
* @Entity generator.domain.TbSellPurchase
*/
public interface TbSellPurchaseMapper extends BaseMapper<TbSellPurchase> {

    // 添加卖出订单
    int insertSelective(TbSellPurchase record);

    // 查询个人卖出订单
    List<TbSellPurchase> selectByName(@Param("ownName")String ownName);
}




