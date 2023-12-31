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

    private Integer pageSize = 10;

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
        // keys存到content里面
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
        PageHelper.startPage(pageNum, pageSize);
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

    // 以用户名+类型的方式搜索
    @Override
    public PageInfo<TbOrder> selectByType(Integer pageNum, String type) {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        TbOrder order = new TbOrder();
        Iterator it = principal.getAuthorities().iterator(); // 获得一个迭代子
        while(it.hasNext()) {
            Object obj = it.next(); // 得到下一个元素
            String role = obj.toString();
            if(!role.equals("admin"))
            {
                order.setOwnName(name);
                break;
            }
        }

//        TbOrder order = new TbOrder();
//        order.setOwnName("voiceofmyheart");

        order.setType(type);

        PageHelper.startPage(pageNum, pageSize);
        List<TbOrder> orders = tbOrderMapper.selectByExample(order);
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(orders);
        return orderPageInfo;
    }




    // /个人商品操作

    // 所有需求模块实现

    // 条件查询商品需求
    @Override
    public PageInfo<TbOrder> selectAllNeedsByKeys(Integer pageNum, String keys, String name) {
        TbOrder order = new TbOrder();
        // 把类型设置为needs
        order.setType("needs");
        // keys存到content里面
        order.setContent(keys);

        try {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("========="+principal);
            Iterator it = principal.getAuthorities().iterator(); // 获得一个迭代子
            while(it.hasNext()) {
                Object obj = it.next(); // 得到下一个元素
                String role = obj.toString();
                if(!role.equals("admin")) // 如果不是管理员
                {
                    order.setOwnName(name);
                    order.setOrderStatu(0);
                    break;
                }
            }
        }catch (Exception e){

        }

        //分页
        PageHelper.startPage(pageNum, pageSize);
        //查询
        List<TbOrder> orders = tbOrderMapper.selectByKeys(order);
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(orders);

        return orderPageInfo;
    }


    // 查询全部商品需求
    @Override
    public PageInfo<TbOrder> selectAllNeeds(Integer pageNum) {
        TbOrder order = new TbOrder();
        order.setType("needs");
        PageHelper.startPage(pageNum, pageSize);
        List<TbOrder> orders = tbOrderMapper.selectByExample(order);
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(orders);
        return orderPageInfo;
    }

    // 所有需求模块实现

    // 条件查询个人需求
    @Override
    public PageInfo<TbOrder> selectNeedsByKeys(Integer pageNum, String keys, String name) {
        TbOrder order = new TbOrder();
        order.setType("needs");
        order.setContent(keys);
        order.setOwnName(name);
        PageHelper.startPage(pageNum, pageSize);
        List<TbOrder> orders = tbOrderMapper.selectByKeys(order);
        PageInfo<TbOrder> orderPageInfo = new PageInfo<>(orders);
        return orderPageInfo;
    }

    // 分页查询个人需求 不用写，selectByType中把type设为needs
    // 更新个人需求
    @Override
    public void updateMyNeeds(TbOrder order) {

    }
    // 删除个人需求
    @Override
    public void deleteMyNeeds(Integer id) {

    }
    // 修改个人需求
    @Override
    public void addMyNeeds(TbOrder order) {

    }
}




