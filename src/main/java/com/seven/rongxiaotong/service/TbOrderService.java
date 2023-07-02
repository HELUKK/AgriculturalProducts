package com.seven.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.TbOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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

    // 查询所有商品的货源 （根据登陆者权限判定）
    PageInfo<TbOrder> selectGoodsByKeys (Integer pageNum,String keys,String name);

    // 个人商品操作

    // 添加商品
    void add (TbOrder order);

    // 删除商品
    void delete (Integer id);

    // 修改商品
    void update (TbOrder order);

    // 按类型查询商品
    PageInfo<TbOrder> selectByType(Integer pageNum,String type);

    // /个人商品操作

    // 所有需求模块实现
    // 查询全部商品需求
    PageInfo<TbOrder> selectAllNeeds(Integer pageNum);

    // 条件查询商品需求
    PageInfo<TbOrder> selectAllNeedsByKeys(Integer pageNum,String keys,String name);
    // 所有需求模块实现

    // 个人需求管理模块
    // 条件查询个人需求
    PageInfo<TbOrder> selectNeedsByKeys(Integer pageNum,String keys,String name);
    // 分页查询个人需求 不用写，selectByType中把type设为needs
    // 更新需求
    void updateMyNeeds(TbOrder order);
    // 删除需求
    void deleteMyNeeds(Integer id);
    // 添加需求
    void addMyNeeds(TbOrder order);
    // 个人需求管理模块
}
