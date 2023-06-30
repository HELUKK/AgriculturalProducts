package com.seven.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.entity.TbOrder;
import org.springframework.stereotype.Service;


/**
* @author 86152
* @description 针对表【tb_order】的数据库操作Service
* @createDate 2023-06-29 10:57:32
*/


public interface TbOrderService extends IService<TbOrder> {

    // 分页查询所有商品
    PageInfo<TbOrder> selectAll(Integer pageNum);

    // 分页查询所有商品货源
    PageInfo<TbOrder> selectAllGoods(Integer pageNum);

    // 条件查询所有商品
    PageInfo<TbOrder> selectAllByKeys(Integer pageNum,String keys);
}
