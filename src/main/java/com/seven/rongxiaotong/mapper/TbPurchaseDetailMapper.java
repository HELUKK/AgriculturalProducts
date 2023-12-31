package com.seven.rongxiaotong.mapper;

import com.seven.rongxiaotong.entity.TbPurchaseDetail ;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_purchase_detail】的数据库操作Mapper
* @createDate 2023-07-02 14:11:22
* @Entity generator.domain.TbPurchaseDetail
*/
public interface TbPurchaseDetailMapper extends BaseMapper<TbPurchaseDetail> {

    // 添加订单详情
    int insertSelective(TbPurchaseDetail record);

    // 查询个人订单详情
    List<TbPurchaseDetail> selectByPurchaseId(@Param("purchaseId")Integer purchaseId);
}




