package com.seven.rongxiaotong.mapper;

import com.seven.rongxiaotong.entity.TbPurchase ;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.rongxiaotong.model.MyPurchase;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_purchase(purchase_type ：1买到的，2卖出的

purchase_status：1待付款 2待发货 3待收货 4已收货)】的数据库操作Mapper
* @createDate 2023-07-02 13:27:22
* @Entity generator.domain.TbPurchase
*/
public interface TbPurchaseMapper extends BaseMapper<TbPurchase> {

//    添加订单
    int insertSelective(TbPurchase record);

//    根据用户名获取最新订单
    TbPurchase selectNewPurchaseId(@Param("ownName")String ownName);

//    查询个人订单
    List<MyPurchase> selectByPurchase(String  name);
}




