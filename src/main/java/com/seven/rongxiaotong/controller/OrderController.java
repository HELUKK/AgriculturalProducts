package com.seven.rongxiaotong.controller;

import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.TbOrder;
import com.seven.rongxiaotong.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.swing.plaf.nimbus.State;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private TbOrderService tbOrderService;

    @GetMapping("/All/{pageNum}")
    Result<PageInfo> selectAll(@PathVariable("pageNum") Integer pageNum) {
        PageInfo<TbOrder> orders = tbOrderService.selectAll(pageNum);
//        System.out.println(orders.getList());
        return new Result<PageInfo>(true, StatusCode.OK,"查询成功",orders);
    }

    @GetMapping("/goods/{pageNum}")
    Result<PageInfo> selectAllGoods(@PathVariable("pageNum") Integer pageNum) {

        PageInfo<TbOrder> orders = tbOrderService.selectAllGoods(pageNum);

        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",orders);
    }
}
