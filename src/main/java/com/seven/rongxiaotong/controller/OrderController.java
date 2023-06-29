package com.seven.rongxiaotong.controller;

import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.entity.TbOrder;
import com.seven.rongxiaotong.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.nimbus.State;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private TbOrderService tbOrderService;

    @GetMapping("/All/{pageNum}")
    Result<PageInfo> selectAll(@PathVariable("pageNum") Integer pageNum) {
        PageInfo<TbOrder> orders = tbOrderService.selectAll(pageNum);
//        System.out.println(orders.getList());
        return new Result(true,20000,"查询成功",orders.getList());
    }
}
