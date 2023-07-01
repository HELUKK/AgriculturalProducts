package com.seven.rongxiaotong.service;

import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.entity.TbKnowledge;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 10594
* @description 针对表【tb_knowledge】的数据库操作Service
* @createDate 2023-06-30 14:54:50
*/
public interface TbKnowledgeService extends IService<TbKnowledge> {
   //查询所有知识
    PageInfo<TbKnowledge> findPage(Integer pageNum);
    //根据k.id查询知识
    TbKnowledge selectById(Integer id);
    //根据keys查询匹配信息
    PageInfo<TbKnowledge> findPageByKeys(String keys,Integer pageNum);
    //查询个人信息
    List<TbKnowledge> selectByUsername(String name);
    //新增信息
    void add(TbKnowledge tbKnowledge);
    //更新信息
    void update(TbKnowledge tbKnowledge, Integer id);
    //删除信息
    void delete(Integer id);
}
