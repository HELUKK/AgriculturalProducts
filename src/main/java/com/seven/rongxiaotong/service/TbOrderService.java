package com.seven.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.entity.TbOrder;


/**
* @author 86152
* @description 针对表【tb_order】的数据库操作Service
* @createDate 2023-06-29 10:57:32
*/


public interface TbOrderService extends IService<TbOrder> {

    // 分页查询所有商品
    PageInfo<TbOrder> selectAll(Integer pageNum);

    // 查询所有商品货源（type = goods）
    PageInfo<TbOrder> selectAllGoods(Integer pageNum);

    // 条件查询所有商品（内容：content）
    PageInfo<TbOrder> selectAllByKeys(Integer pageNum,String keys);

    // 按id查询商品（id）
    TbOrder selectById(Integer id);

}
