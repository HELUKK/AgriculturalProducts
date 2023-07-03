package com.seven.rongxiaotong.mapper;

import com.seven.rongxiaotong.entity.TbShoppingcart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.rongxiaotong.model.ShoppingModel;

import java.util.List;

/**
* @author 10594
* @description 针对表【tb_shoppingcart】的数据库操作Mapper
* @createDate 2023-07-02 14:36:41
* @Entity com.seven.rongxiaotong.entity.TbShoppingcart
*/
public interface TbShoppingcartMapper extends BaseMapper<TbShoppingcart> {

    int deleteByPrimaryKey(Integer shoppingId);

    List<ShoppingModel> selectByShopping(TbShoppingcart shoppingcart);

    int insertSelective(TbShoppingcart tbshoppingcart);

    int updateByPrimaryKeySelective(TbShoppingcart tbshoppingcart);
}




