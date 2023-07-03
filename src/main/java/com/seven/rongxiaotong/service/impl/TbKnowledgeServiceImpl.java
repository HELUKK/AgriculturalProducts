package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.entity.TbKnowledge;
import com.seven.rongxiaotong.mapper.TbKnowledgeMapper;
import com.seven.rongxiaotong.service.TbKnowledgeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    /**
     * @description: TODO 分页查询所有农业知识
     * @return com.github.pagehelper.PageInfo<com.seven.rongxiaotong.entity.TbKnowledge>
     * @author: juny
     * @date: 2023-07-01 上午11:01
     */
    @Override
    public PageInfo<TbKnowledge> findPage(Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbKnowledge> tbKnowledges = tbKnowledgeMapper.selectAll();
        return new PageInfo<>(tbKnowledges);
    }

    /**
     * @description: TODO 根据ID查询农业知识
     * @return com.seven.rongxiaotong.entity.TbKnowledge
     * @author: juny
     * @date: 2023-07-01 上午11:02
     */
    @Override
    public TbKnowledge selectById(Integer id) {
        return tbKnowledgeMapper.selectByPrimaryKey(id);
    }

    /**
     * @description: TODO 根据keys条件查询相关农业知识
     * @return com.github.pagehelper.PageInfo<com.seven.rongxiaotong.entity.TbKnowledge>
     * @author: juny
     * @date: 2023-07-01 上午11:02
     */
    @Override
    public PageInfo<TbKnowledge> findPageByKeys(String keys, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbKnowledge> tbKnowledges = tbKnowledgeMapper.selectAllByKeys(keys);
        return new PageInfo<>(tbKnowledges);
    }

    /**
     * @description: TODO 根据ownName查询个人农业知识
     * @return java.util.List<com.seven.rongxiaotong.entity.TbKnowledge>
     * @author: juny
     * @date: 2023-07-01 上午11:03
     */
    @Override
    public List<TbKnowledge> selectByUsername(String name) {
        return tbKnowledgeMapper.selectByExample(name);
    }

    /**
     * @description: TODO 增加农业知识
     * @return void
     * @author: juny
     * @date: 2023-07-01 上午11:04
     */
    @Override
    public void add(TbKnowledge tbKnowledge){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
//        String name = "wyn3";
        tbKnowledge.setOwnName(name);
        tbKnowledge.setCreateTime(new Date());
        tbKnowledge.setUpdateTime(new Date());
        tbKnowledgeMapper.insertSelective(tbKnowledge);
    }

    /**
     * @description: TODO 根据新knowledgeId更新信息
     * @return void
     * @author: juny
     * @date: 2023-07-01 上午11:23
     */
    @Override
    public void update(TbKnowledge tbKnowledge, Integer id) {
        tbKnowledge.setKnowledgeId(id);
        tbKnowledgeMapper.updateByPrimaryKeySelective(tbKnowledge);
    }
    /**
     * @description: TODO 删除信息
     * @return void
     * @author: juny
     * @date: 2023-07-01 上午11:24
     */
    @Override
    public void delete(Integer id) {
        tbKnowledgeMapper.deleteByPrimaryKey(id);
    }
}




