package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.entity.TbKnowledge;
import com.seven.rongxiaotong.mapper.TbKnowledgeMapper;
import com.seven.rongxiaotong.service.TbKnowledgeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 10594
* @description 针对表【tb_knowledge】的数据库操作Service实现
* @createDate 2023-06-30 14:54:50
*/
@Service
public class TbKnowledgeServiceImpl extends ServiceImpl<TbKnowledgeMapper, TbKnowledge>
    implements TbKnowledgeService {

    private static final Integer pageSize = 30;
    @Resource
    private TbKnowledgeMapper tbKnowledgeMapper;
    @Override
    public PageInfo<TbKnowledge> findPage(Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbKnowledge> tbKnowledges = tbKnowledgeMapper.selectAll();
        PageInfo<TbKnowledge> knowledgePageInfo = new PageInfo<>(tbKnowledges);
        return knowledgePageInfo;
    }

    @Override
    public TbKnowledge selectById(Integer id) {
        TbKnowledge tbKnowledge = tbKnowledgeMapper.selectByPrimaryKey(id);
        return tbKnowledge;
    }

    @Override
    public PageInfo<TbKnowledge> findPageByKeys(String keys, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbKnowledge> tbKnowledges = tbKnowledgeMapper.selectAllByKeys(keys);
        PageInfo<TbKnowledge> tbKnowledgePageInfo = new PageInfo<>(tbKnowledges);
        return tbKnowledgePageInfo;
    }
}




