package com.seven.rongxiaotong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.rongxiaotong.entity.Expert;

import java.util.List;

/**
* @author wenjh
* @description 针对表【expert(专家信息表)】的数据库操作Mapper
* @createDate 2023-06-30 15:06:37
* @Entity generator.domain.Expert
*/
public interface ExpertMapper extends BaseMapper<Expert> {

    List<Expert> selectByKeys(String keys);
}




