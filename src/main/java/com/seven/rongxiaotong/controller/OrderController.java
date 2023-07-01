package com.seven.rongxiaotong.controller;

import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.TbOrder;
import com.seven.rongxiaotong.service.TbOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;

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

    // 个人商品操作

    // 添加商品
//    @ApiOperation(value = "添加商品")
    @PostMapping
    public Result<String> add(@Valid @RequestBody TbOrder order, BindingResult bindingResult) {
        //检查项目
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "添加失败",s);
        }
        //获取用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
//        String name = "skz";
        order.setOwnName(name);
        //设置时间
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        //添加
        tbOrderService.add(order);
        return new Result(true, StatusCode.OK, "添加成功",null);
    }

    // 删除商品
    @DeleteMapping("/{id}")
    public Result deleteOrder(@PathVariable("id") Integer id) {
        tbOrderService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }



    // /个人商品操作
}
