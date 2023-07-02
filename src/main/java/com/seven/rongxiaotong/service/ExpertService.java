package com.seven.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.rongxiaotong.entity.Expert;

/**
* @author wenjh
* @description 针对表【expert(专家信息表)】的数据库操作Service
* @createDate 2023-06-30 15:06:37
*/
public interface ExpertService extends IService<Expert> {

    Expert selectByUserName(String userName);
}
