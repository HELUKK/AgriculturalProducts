package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.entity.TbOrder;
import com.seven.rongxiaotong.mapper.TbOrderMapper;
import com.seven.rongxiaotong.service.TbOrderService;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86152
* @description 针对表【tb_order】的数据库操作Service实现
* @createDate 2023-06-29 10:57:32
*/
@Service
public class TbOrderServiceImpl extends ServiceImpl<TbOrderMapper, TbOrder>
    implements TbOrderService {

    // 注入mapper接口
    @Resource
    private TbOrderMapper tbOrderMapper;

    // 查询所有商品
    @Override
    public PageInfo<TbOrder> selectAll(Integer pageNum) {

        PageHelper.startPage(1,20);
        List<TbOrder> goods = tbOrderMapper.selectAll();
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(goods);
//        System.out.println("Service实现类被调用"+orderPageInfo.getList());
        return orderPageInfo;
    }

    // 查询所有商品货源
    @Override
    public PageInfo<TbOrder> selectAllGoods(Integer pageNum) {
        TbOrder order = new TbOrder();
        order.setType("goods");
        PageHelper.startPage(1,20);
        List<TbOrder> goods = tbOrderMapper.selectByExample(order);
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(goods);

        return orderPageInfo;
    }

    // 按条件查询商品
    @Override
    public PageInfo<TbOrder> selectAllByKeys(Integer pageNum,String keys) {
        TbOrder order = new TbOrder();
        order.setContent(keys);
        PageHelper.startPage(1,20);
        List<TbOrder> goods = tbOrderMapper.selectByKeys(order);
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(goods);
        return orderPageInfo;
    }


}




