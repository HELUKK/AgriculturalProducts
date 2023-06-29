package com.seven.rongxiaotong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.rongxiaotong.entity.TbOrder;

import java.util.List;


/**
* @author 86152
* @description 针对表【tb_order】的数据库操作Mapper
* @createDate 2023-06-29 10:57:32
* @Entity generator.domain.TbOrder
*/
public interface TbOrderMapper extends BaseMapper<TbOrder> {

    // 查询所有接口
    List<TbOrder> selecAll();
}




