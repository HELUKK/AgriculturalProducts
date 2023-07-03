package com.seven.rongxiaotong.service;

import com.seven.rongxiaotong.entity.TbShoppingcart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.rongxiaotong.model.ShoppingModel;

import java.util.List;

/**
* @author 10594
* @description 针对表【tb_shoppingcart】的数据库操作Service
* @createDate 2023-07-02 14:36:41
*/
public interface TbShoppingcartService extends IService<TbShoppingcart> {


    void delete(Integer id);

    List<ShoppingModel> selectByUserOrderId(Integer id);

    void update(TbShoppingcart tbshoppingcart);

    void add(TbShoppingcart tbshoppingcart);

    List<ShoppingModel> selectByUsername();
}
