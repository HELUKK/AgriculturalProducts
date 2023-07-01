package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.entity.TbOrder;
import com.seven.rongxiaotong.mapper.TbOrderMapper;
import com.seven.rongxiaotong.service.TbOrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
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

    private Integer pageSize = 30;

    // 查询所有商品
    @Override
    public PageInfo<TbOrder> selectAll(Integer pageNum) {

        PageHelper.startPage(pageNum,pageSize);
        List<TbOrder> goods = tbOrderMapper.selectAll();
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(goods);
//        System.out.println("Service实现类被调用"+orderPageInfo.getList());
        return orderPageInfo;
    }

    // 查询所有商品货源（type = goods）
    @Override
    public PageInfo<TbOrder> selectAllGoods(Integer pageNum) {
        TbOrder order = new TbOrder();
        order.setType("goods");
        PageHelper.startPage(pageNum,pageSize);
        List<TbOrder> goods = tbOrderMapper.selectByExample(order);
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(goods);

        return orderPageInfo;
    }

    // 按条件查询商品（内容：content）
    @Override
    public PageInfo<TbOrder> selectAllByKeys(Integer pageNum,String keys) {
        TbOrder order = new TbOrder();
        order.setContent(keys);
        PageHelper.startPage(pageNum,pageSize);
        List<TbOrder> goods = tbOrderMapper.selectByKeys(order);
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(goods);
        return orderPageInfo;
    }

    // 按id搜索商品（id）
    @Override
    public TbOrder selectById(Integer id) {
        TbOrder order = tbOrderMapper.selectById(id);

        return order;
    }

    // 查询所有商品的货源 （根据登陆者权限判定）

    @Override
    public PageInfo<TbOrder> selectGoodsByKeys(Integer pageNum, String keys,  String name) {
        //创建Order实例
        TbOrder order = new TbOrder();
        order.setType("goods");
        order.setContent(keys);

        try {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("========="+principal);
            Iterator it = principal.getAuthorities().iterator(); // 获得一个迭代子
            while(it.hasNext()) {
                Object obj = it.next(); // 得到下一个元素
                String role = obj.toString();
                if(!role.equals("admin"))
                {
                    order.setOwnName(name);
                    order.setOrderStatu(0);
                    break;
                }
            }
        }catch (Exception e){

        }

        //分页
        PageHelper.startPage(pageNum, 20);
        //查询
        List<TbOrder> orders = tbOrderMapper.selectByKeys(order);
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(orders);

        return orderPageInfo;
    }

    // 个人商品操作

    // 添加商品
    @Override
    public void add(TbOrder order) {
        tbOrderMapper.insertOrder(order);
    }

    // 删除商品
    @Override
    public void delete(Integer id) {
        tbOrderMapper.deleteOrder(id);
    }

    // 修改商品
    @Override
    public void update(TbOrder order) {
        tbOrderMapper.updateOrder(order);
    }


    // /个人商品操作

}




