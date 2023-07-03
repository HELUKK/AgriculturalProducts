package com.seven.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.rongxiaotong.entity.Reserve;

import java.util.List;

/**
* @author wenjh
* @description 针对表【reserve】的数据库操作Service
* @createDate 2023-07-03 11:37:12
*/
public interface ReserveService extends IService<Reserve> {

    void addReserve(Reserve reserve);

    Reserve selectReserveById(Integer id);

    List<Reserve> selectReserveByNowUser(String type);
}
