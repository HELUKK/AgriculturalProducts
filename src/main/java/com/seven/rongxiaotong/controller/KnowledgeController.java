package com.seven.rongxiaotong.controller;

import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.TbKnowledge;
import com.seven.rongxiaotong.service.TbKnowledgeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;


public class KnowledgeController {
    @Resource
    private TbKnowledgeService tbKnowledgeService;

    /**
     * @description: 分页查询所有知识
     * @param pageNum
     * @author: juny
     * @date: 2023-06-30 下午3:05
     */
    @GetMapping("{pageNum}")
    public Result<PageInfo<TbKnowledge>> findPage(@PathVariable Integer pageNum){
        PageInfo<TbKnowledge> knowledgePageInfo = tbKnowledgeService.findPage(pageNum);
        return new Result<PageInfo<TbKnowledge>>(true, StatusCode.OK,"查询成功",knowledgePageInfo);
    }
    /**
     * @description: 按Id查询
     * @param id
     * @author: juny
     * @date: 2023-06-30 下午3:57
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        TbKnowledge tbKnowledge = tbKnowledgeService.selectById(id);
        return new Result(true,StatusCode.OK,"查询成功",tbKnowledge);
    }
    /**
     * @description: 分页条件查询所有知识
     * @param keys
     * @param pageNum
     * @author: juny
     * @date: 2023-06-30 下午3:10
     */
    @GetMapping("/{keys}/{pageNum}")
    public Result<PageInfo<TbKnowledge>> findPageByKeys(@PathVariable String keys,@PathVariable Integer pageNum){
        PageInfo<TbKnowledge> tbKnowledgePageInfo = tbKnowledgeService.findPageByKeys(keys,pageNum);
        return new Result<PageInfo<TbKnowledge>>(true,StatusCode.OK,"查询成功",tbKnowledgePageInfo);
    }
}
