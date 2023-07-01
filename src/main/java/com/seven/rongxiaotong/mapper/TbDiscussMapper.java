package com.seven.rongxiaotong.mapper;

import com.seven.rongxiaotong.entity.TbDiscuss;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
* @author 10594
* @description 针对表【tb_discuss】的数据库操作Mapper
* @createDate 2023-07-01 14:39:12
* @Entity com.seven.rongxiaotong.entity.TbDiscuss
*/
public interface TbDiscussMapper extends BaseMapper<TbDiscuss> {
    List<TbDiscuss> selectByKnowledgeId(@PathVariable("knowledgeId") Integer knowledgeId);
}




