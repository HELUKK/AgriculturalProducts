package com.seven.rongxiaotong.controller;

import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.TbPurchase;
import com.seven.rongxiaotong.entity.TbPurchaseDetail;
import com.seven.rongxiaotong.entity.TbSellPurchase;
import com.seven.rongxiaotong.entity.TbShoppingcart;
import com.seven.rongxiaotong.model.ShoppingModel;
import com.seven.rongxiaotong.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private TbPurchaseService tbPurchaseService;
    @Resource
    private TbPurchaseDetailService tbPurchaseDetailService;
    @Resource
    private TbSellPurchaseService tbSellPurchaseService;

    @Resource
    private TbShoppingcartService tbShoppingcartService;

    /**
     * @description: TODO 添加商品到购物车
     * @return com.seven.rongxiaotong.common.Result
     * @author: juny
     * @date: 2023-07-02 下午3:11
     */
    @PostMapping("/add/{id}")
    public Result add(@PathVariable("id") Integer id) {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        TbShoppingcart tbshoppingcart =new TbShoppingcart();
        tbshoppingcart.setOwnName(name);
        tbshoppingcart.setOrderId(id);
        tbshoppingcart.setCount(1);
        tbshoppingcart.setCreateTime(new Date());
        tbshoppingcart.setUpdateTime(new Date());

        List<ShoppingModel> shoppingcarts = TbShoppingcartService.selectByUserOrderId(id);
        if (null!= shoppingcarts && shoppingcarts.size() > 0){
            tbshoppingcart.setCount(shoppingcarts.get(0).getCount() + 1);
            tbshoppingcart.setShoppingId(shoppingcarts.get(0).getShoppingId());
            TbShoppingcartService.update(tbshoppingcart);
        }
        else {
            TbShoppingcartService.add(tbshoppingcart);
        }
        return new Result(true, StatusCode.OK, "添加商品到购物车成功");
    }
    /**
     * @description: TODO 删除购物车商品
     * @author: juny
     * @date: 2023-07-02 下午3:24
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        //获取用户名
        tbShoppingcartService.delete(id);
        return new Result<>(true,StatusCode.OK,"删除商品成功");
    }
    @PostMapping("/commitOrder/{addId}/{tMoney}")
    public Result commitOrder(@PathVariable("addId") Integer addId, @PathVariable("tMoney") String tMoney, @RequestBody List<ShoppingModel> shoppingModelList) {

        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();

        //添加购买人买入订单
        //创建Purchase实例，设置订单类型为
        TbPurchase purchase = new TbPurchase();
        purchase.setPurchaseType(1);//买入商品
        purchase.setOwnName(name);
        purchase.setAddress(addId.toString());
        purchase.setPurchaseStatus(1);//状态统一为待发货
        purchase.setTotalPrice(new BigDecimal(tMoney));
        purchase.setCreateTime(new Date());
        purchase.setUpdateTime(new Date());

        tbPurchaseService.add(purchase);
        //取得刚才插入订单的id
        TbPurchase PurchaseGetId = tbPurchaseService.selectNewPurchaseId(name);
        Integer purchaseId = PurchaseGetId.getPurchaseId();
        //添加购买人买入订单详细
        if (null != shoppingModelList && shoppingModelList.size() > 0){
            TbPurchaseDetail purchaseDetail = new TbPurchaseDetail();
            TbSellPurchase sellPurchase = new TbSellPurchase();
            for (int i = 0; i<shoppingModelList.size(); i++){
                ShoppingModel shoppingModel = shoppingModelList.get(i);
                purchaseDetail.setCount(shoppingModel.getCount());
                purchaseDetail.setOrderId(shoppingModel.getOrderId());
                purchaseDetail.setPurchaseId(purchaseId);
                purchaseDetail.setSumPrice(new BigDecimal(Double.parseDouble(shoppingModel.getPrice())* shoppingModel.getCount()));
                purchaseDetail.setUninPrice(new BigDecimal(shoppingModel.getPrice()));
                tbPurchaseDetailService.add(purchaseDetail);

                //卖出订单
                //添加卖出方卖出订单
                //创建Purchase实例，设置订单类型为
                sellPurchase.setOrderId(shoppingModel.getOrderId());
                sellPurchase.setAddress(addId.toString());
                sellPurchase.setOwnName(shoppingModel.getOwnName());
                sellPurchase.setPurchaseId(purchaseId);
                sellPurchase.setPurchaseStatus(1);
                sellPurchase.setPurchaseType(2);
                sellPurchase.setSumPrice(new BigDecimal(Double.parseDouble(shoppingModel.getPrice())* shoppingModel.getCount()));
                sellPurchase.setUninPrice(new BigDecimal(shoppingModel.getPrice()));
                sellPurchase.setCreateTime(new Date());
                sellPurchase.setUpdateTime(new Date());
                tbSellPurchaseService.add(sellPurchase);

               // 删除购物车信息
                tbShoppingcartService.delete(shoppingModel.getShoppingId());
            }
        }

        return new Result(true, StatusCode.OK, "提交成功");
    }



}
