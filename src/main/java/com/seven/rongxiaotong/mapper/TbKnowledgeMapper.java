package com.seven.rongxiaotong.mapper;

import com.seven.rongxiaotong.entity.TbKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 10594
* @description 针对表【tb_knowledge】的数据库操作Mapper
* @createDate 2023-06-30 14:54:50
* @Entity com.seven.rongxiaotong.entity.TbKnowledge
*/
public interface TbKnowledgeMapper extends BaseMapper<TbKnowledge> {

      List<TbKnowledge> selectAll() ;

     TbKnowledge selectByPrimaryKey(Integer id);

     List<TbKnowledge> selectAllByKeys(String keys);

}




