package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.TbPurchase ;
import com.seven.rongxiaotong.model.MyPurchase;
import com.seven.rongxiaotong.service.TbPurchaseService;
import com.seven.rongxiaotong.mapper.TbPurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86152
* @description 针对表【tb_purchase(purchase_type ：1买到的，2卖出的

purchase_status：1待付款 2待发货 3待收货 4已收货)】的数据库操作Service实现
* @createDate 2023-07-02 13:27:22
*/
@Service
public class TbPurchaseServiceImpl extends ServiceImpl<TbPurchaseMapper, TbPurchase>
    implements TbPurchaseService{

    @Resource
    private TbPurchaseMapper tbPurchaseMapper;

    @Override
    public void add(TbPurchase purchase) {
        tbPurchaseMapper.insertSelective(purchase);
    }

    @Override
    public TbPurchase selectNewPurchaseId(String ownName) {
        TbPurchase purchase = tbPurchaseMapper.selectNewPurchaseId(ownName);
        return purchase;
    }

    @Override
    public List<MyPurchase> selectByPurchaseType() {
        //获取用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        //创建Purchase实例，设置用户名，订单类型
//        Purchase purchase= new Purchase();
        MyPurchase purchase = new MyPurchase();
        purchase.setOwnName(name);
        //查询
        List<MyPurchase> purchases = tbPurchaseMapper.selectByPurchase(name);

        System.out.println("查询到的结果： "+purchases);

        return purchases;
    }

}




