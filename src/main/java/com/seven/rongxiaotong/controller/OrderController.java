package com.seven.rongxiaotong.controller;

import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.TbOrder;
import com.seven.rongxiaotong.service.TbOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private TbOrderService tbOrderService;

    // 查询全部的商品（无条件）
    @GetMapping("/All/{pageNum}")
    Result<PageInfo> selectAll(@PathVariable("pageNum") Integer pageNum) {
        PageInfo<TbOrder> orders = tbOrderService.selectAll(pageNum);
//        System.out.println(orders.getList());
        return new Result<PageInfo>(true, StatusCode.OK,"查询成功",orders);
    }

    // 查询所有商品货源（type = goods）
    @GetMapping("/goods/{pageNum}")
    Result<PageInfo> selectAllGoods(@PathVariable("pageNum") Integer pageNum) {

        PageInfo<TbOrder> orders = tbOrderService.selectAllGoods(pageNum);

        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",orders);
    }

    // 条件查询所有商品（内容：content）
    @GetMapping("/searchAllByKeys/{keys}/{pageNum}")
    Result<PageInfo> selectAllByKeys(@PathVariable("keys") String keys,@PathVariable("pageNum") Integer pageNum) {

        PageInfo<TbOrder> orders = tbOrderService.selectAllByKeys(pageNum,keys);

        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",orders);
    }

    // 按权限查询所有商品货源
    @GetMapping("/searchGoodsByKeys/{keys}/{pageNum}")
    public Result<PageInfo> searchGoodsByKeys(@PathVariable("keys") String keys,@PathVariable("pageNum") Integer pageNum) {
        PageInfo<TbOrder> orders = tbOrderService.selectGoodsByKeys(pageNum,keys,null);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    // 按id查询商品 只查询到一个数据
    @GetMapping("/selectById/{id}")
    public Result<TbOrder> selectById (@PathVariable("id") Integer id) {
        TbOrder order = tbOrderService.selectById(id);
        return new Result<TbOrder>(true,StatusCode.OK,"查询成功",order);
    }
}
