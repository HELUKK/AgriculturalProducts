package com.seven.rongxiaotong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.rongxiaotong.entity.Reserve;

import java.util.List;

/**
* @author wenjh
* @description 针对表【reserve】的数据库操作Mapper
* @createDate 2023-07-03 11:37:12
* @Entity generator.domain.Reserve
*/
public interface ReserveMapper extends BaseMapper<Reserve> {

    List<Reserve> selectReserveByNowUser(Reserve reserve);
}




