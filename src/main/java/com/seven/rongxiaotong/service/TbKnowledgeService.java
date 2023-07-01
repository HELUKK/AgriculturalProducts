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
    PageInfo<TbKnowledge> findPage(Integer pageNum);
    TbKnowledge selectById(Integer id);
    PageInfo<TbKnowledge> findPageByKeys(String keys,Integer pageNum);
    List<TbKnowledge> selectByUsername(String name);
    void add(TbKnowledge tbKnowledge);
}
