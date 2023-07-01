package com.seven.rongxiaotong.mapper;

import com.seven.rongxiaotong.entity.TbKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 10594
* @description 针对表【tb_knowledge】的数据库操作Mapper
* @createDate 2023-06-30 14:54:50
* @Entity com.seven.rongxiaotong.entity.TbKnowledge
*/
public interface TbKnowledgeMapper extends BaseMapper<TbKnowledge> {
     //查询所有知识
     List<TbKnowledge> selectAll() ;
     //根据农业知识ID搜索
     TbKnowledge selectByPrimaryKey(Integer id);
     //跳进查询农业知识（根据key搜索标题）
     List<TbKnowledge> selectAllByKeys(String keys);
     //查询个人农业知识(根据own_name查找）
     List<TbKnowledge> selectByExample(@Param("name") String name);
     //插入知识
     int insertSelective(TbKnowledge tbKnowledge);
     //更新知识（根据knowledgeId匹配）
     int updateByPrimaryKeySelective(TbKnowledge tbKnowledge);
     //删除知识
     int deleteByPrimaryKey(Integer id);
}




