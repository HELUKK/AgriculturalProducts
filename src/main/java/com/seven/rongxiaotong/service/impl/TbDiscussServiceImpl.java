package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.TbDiscuss;
import com.seven.rongxiaotong.service.TbDiscussService;
import com.seven.rongxiaotong.mapper.TbDiscussMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 10594
* @description 针对表【tb_discuss】的数据库操作Service实现
* @createDate 2023-07-01 14:39:12
*/
@Service
public class TbDiscussServiceImpl extends ServiceImpl<TbDiscussMapper, TbDiscuss>
    implements TbDiscussService{
    @Resource
    private TbDiscussMapper tbDiscussMapper;
    /**
     * @description: TODO 查询评论
     * @return java.util.List<com.seven.rongxiaotong.entity.TbDiscuss>
     * @author: juny
     * @date: 2023-07-01 下午2:57
     */
    @Override
    public List<TbDiscuss> selectByKnowledgeId(Integer knowledgeId) {
        return tbDiscussMapper.selectByKnowledgeId(knowledgeId);
    }

    /**
     * @description: TODO 添加评论
     * @author: juny
     * @date: 2023-07-01 下午4:13
     */
    @Override
    public void add(TbDiscuss tbDiscuss) {
        tbDiscussMapper.insertSelective(tbDiscuss);
    }
}




