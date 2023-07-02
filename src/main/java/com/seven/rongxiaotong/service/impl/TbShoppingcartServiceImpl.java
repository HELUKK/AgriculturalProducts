package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.TbShoppingcart;
import com.seven.rongxiaotong.service.TbShoppingcartService;
import com.seven.rongxiaotong.mapper.TbShoppingcartMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}




