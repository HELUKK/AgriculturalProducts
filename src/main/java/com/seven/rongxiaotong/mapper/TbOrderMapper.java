package com.seven.rongxiaotong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.rongxiaotong.entity.TbOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* @author 86152
* @description 针对表【tb_order】的数据库操作Mapper
* @createDate 2023-06-29 10:57:32
* @Entity generator.domain.TbOrder
*/
public interface TbOrderMapper extends BaseMapper<TbOrder> {

    // 查询所有订单的接口
    List<TbOrder> selectAll();

    // 分页查询所有商品货源
    List<TbOrder> selectByExample(TbOrder order);

    // 条件查询所有商品 （根据类型， 根据发起人，根据内容）可被复用
    List<TbOrder> selectByKeys(TbOrder order);

    // 按id查询商品
    TbOrder selectById(Integer id);

    // 个人商品操作

    // 添加商品
    Integer insertOrder(TbOrder order);


    // /个人商品操作

}




