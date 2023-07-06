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
//        String name = "wyn3";
        //创建购物车对象
        TbShoppingcart tbshoppingcart =new TbShoppingcart();
        tbshoppingcart.setOwnName(name);
        tbshoppingcart.setOrderId(id);
        tbshoppingcart.setCount(1);
        tbshoppingcart.setCreateTime(new Date());
        tbshoppingcart.setUpdateTime(new Date());
        //该用户的购物车是否已有该商品
        List<ShoppingModel> shoppingCarts = tbShoppingcartService.selectByUserOrderId(id);
        if(shoppingCarts != null && shoppingCarts.size() > 0){
            tbshoppingcart.setCount(shoppingCarts.get(0).getCount() + 1);
            tbshoppingcart.setShoppingId(shoppingCarts.get(0).getShoppingId());
            tbShoppingcartService.update(tbshoppingcart);
        }
        else {
            tbShoppingcartService.add(tbshoppingcart);
        }
        return new Result(true,StatusCode.OK,"添加购物车成功");
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
    /**
     * @description: TODO 展示购物车
     * @author: juny
     * @date: 2023-07-02 下午4:48
     */
    @GetMapping("/show")
    public Result<List<ShoppingModel>>show(){
        List<ShoppingModel> shoppingModelList = tbShoppingcartService.selectByUsername();
        return new Result<List<ShoppingModel>>(true,StatusCode.OK,"展示成功",shoppingModelList);
    }
    /**
     * @description: TODO 更新购物车商品
     * @author: juny
     * @date: 2023-07-02 下午4:54
     */
    @PutMapping("/update/{id}/{count}")
    public Result update(@PathVariable("id") Integer id, @PathVariable("count") Integer count){

        //选择要更新的购物车
        TbShoppingcart tbShoppingcart = new TbShoppingcart();
        tbShoppingcart.setCount(count);
        tbShoppingcart.setShoppingId(id);
        tbShoppingcart.setUpdateTime(new Date());
        //更新
        tbShoppingcartService.update(tbShoppingcart);
        return new Result(true,StatusCode.OK,"更新购物车成功");
    }
    @PostMapping("/commitOrder/{addId}/{tMoney}")
    public Result commitOrder(@PathVariable("addId") Integer addId, @PathVariable("tMoney") String tMoney, @RequestBody List<ShoppingModel> shoppingModelList) {

        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();

        // commitOrder中正常获取到了name；
//        System.out.println("commitOrder中的获取的name: "+name);

        //添加购买人买入订单
        //创建Purchase实例，设置订单类型为
        TbPurchase purchase = new TbPurchase();
//        purchaseId作为主键有自增效果 不用特意设计

        purchase.setPurchaseType(1);//买入商品
        purchase.setOwnName(name);

//        ？？？ addId是addressId的意思，addressId与tb_address对应
        purchase.setAddress(addId.toString());

        purchase.setPurchaseStatus(1);//状态统一为待发货
        purchase.setTotalPrice(new BigDecimal(tMoney));
        purchase.setCreateTime(new Date());
        purchase.setUpdateTime(new Date());

//        将商品存入订单表中
        tbPurchaseService.add(purchase);

        //取得刚才插入订单的id 通过获取最新订单的方式
        TbPurchase PurchaseGetId = tbPurchaseService.selectNewPurchaseId(name);
        Integer purchaseId = PurchaseGetId.getPurchaseId();

        //添加购买人买入订单详细
        System.out.println("传入的body"+shoppingModelList);

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
