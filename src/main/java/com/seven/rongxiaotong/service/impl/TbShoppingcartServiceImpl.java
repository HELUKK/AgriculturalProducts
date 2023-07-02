package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.TbShoppingcart;
import com.seven.rongxiaotong.model.ShoppingModel;
import com.seven.rongxiaotong.service.TbShoppingcartService;
import com.seven.rongxiaotong.mapper.TbShoppingcartMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 10594
* @description 针对表【tb_shoppingcart】的数据库操作Service实现
* @createDate 2023-07-02 14:36:41
*/
@Service
public class TbShoppingcartServiceImpl extends ServiceImpl<TbShoppingcartMapper, TbShoppingcart>
    implements TbShoppingcartService{

    @Resource
    private TbShoppingcartMapper tbShoppingcartMapper;
    @Override
    public void delete(Integer id) {
        tbShoppingcartMapper.deleteByPrimaryKey(id);
    }

    /**
     * @description: TODO 查找购物车中是否有该商品
     * @return java.util.List<com.seven.rongxiaotong.model.ShoppingModel>
     * @author: juny
     * @date: 2023-07-02 下午4:36
     */
    @Override
    public List<ShoppingModel> selectByUserOrderId(Integer id) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();

        TbShoppingcart tbShoppingcart = new TbShoppingcart();
        tbShoppingcart.setOwnName(name);
        tbShoppingcart.setOrderId(id);

        return tbShoppingcartMapper.selectByShopping(tbShoppingcart);
    }

    @Override
    public void update(TbShoppingcart tbshoppingcart) {
        tbShoppingcartMapper.updateByPrimaryKeySelective(tbshoppingcart);
    }

    @Override
    public void add(TbShoppingcart tbShoppingcart){
        tbShoppingcartMapper.insertSelective(tbShoppingcart);
    }

    @Override
    public List<ShoppingModel> selectByUsername() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
//        String name = "wyn3";
        TbShoppingcart tbShoppingcart = new TbShoppingcart();
        tbShoppingcart.setOwnName(name);
        return tbShoppingcartMapper.selectByShopping(tbShoppingcart);

    }

}




