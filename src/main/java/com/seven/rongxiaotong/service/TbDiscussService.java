package com.seven.rongxiaotong.service;

import com.seven.rongxiaotong.entity.TbDiscuss;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 10594
* @description 针对表【tb_discuss】的数据库操作Service
* @createDate 2023-07-01 14:39:12
*/
public interface TbDiscussService extends IService<TbDiscuss> {
    //根据知识id查询评论
    List<TbDiscuss> selectByKnowledgeId(Integer knowledgeId);
    //添加新评论
    void add(TbDiscuss tbDiscuss);
}
