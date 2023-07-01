package com.seven.rongxiaotong.controller;

import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.TbDiscuss;
import com.seven.rongxiaotong.entity.TbKnowledge;
import com.seven.rongxiaotong.service.TbDiscussService;
import com.seven.rongxiaotong.service.TbKnowledgeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    @Resource
    private TbKnowledgeService tbKnowledgeService;
    private TbDiscussService tbDiscussService;

    /**
     * @description: TODO 分页查询所有知识
     * @author: juny
     * @date: 2023-06-30 下午3:05
     */
    @GetMapping("{pageNum}")
    public Result<PageInfo<TbKnowledge>> findPage(@PathVariable Integer pageNum){
        PageInfo<TbKnowledge> knowledgePageInfo = tbKnowledgeService.findPage(pageNum);
        return new Result<PageInfo<TbKnowledge>>(true, StatusCode.OK,"查询成功",knowledgePageInfo);
    }
    /**
     * @description: TODO 按Id查询
     * @author: juny
     * @date: 2023-06-30 下午3:57
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        TbKnowledge tbKnowledge = tbKnowledgeService.selectById(id);
        return new Result(true,StatusCode.OK,"查询成功",tbKnowledge);
    }
    /**
     * @description: TODO 分页条件查询所有知识
     * @author: juny
     * @date: 2023-06-30 下午3:10
     */
    @GetMapping("/{keys}/{pageNum}")
    public Result<PageInfo<TbKnowledge>> findPageByKeys(@PathVariable String keys,@PathVariable Integer pageNum){
        PageInfo<TbKnowledge> tbKnowledgePageInfo = tbKnowledgeService.findPageByKeys(keys,pageNum);
        return new Result<PageInfo<TbKnowledge>>(true,StatusCode.OK,"查询成功",tbKnowledgePageInfo);
    }
    /**
     * @description: TODO 查询个人农业知识
     * @return com.seven.rongxiaotong.common.Result
     * @author: juny
     * @date: 2023-06-30 下午5:01
     */
    @GetMapping("/selectByUsername")
    public Result selectByUsername(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        List<TbKnowledge> tbKnowledges = tbKnowledgeService.selectByUsername(name);
        return new Result(true,StatusCode.OK,"查询成功",tbKnowledges);
    }

    /**
     * @description: TODO 添加农业知识
     * @author: juny
     * @date: 2023-07-01 上午9:34
     */
    @PostMapping()
    public Result add(@RequestBody TbKnowledge tbKnowledge){
        tbKnowledgeService.add(tbKnowledge);
        return new Result(true,StatusCode.OK,"添加成功");
    }
    /**
     * @description: TODO 修改农业知识
     * @return com.seven.rongxiaotong.common.Result
     * @author: juny
     * @date: 2023-06-30 下午5:12
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody TbKnowledge tbKnowledge,@PathVariable("id")Integer id){
        tbKnowledgeService.update(tbKnowledge,id);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * @description: TODO 删除农业知识
     * @author: juny
     * @date: 2023-07-01 上午10:39
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id){
        tbKnowledgeService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * @description: TODO 查询评论
     * @author: juny
     * @date: 2023-07-01 下午2:49
     */
    @GetMapping("/selectByKnowledge/{id}")
    public Result selectByKnowledge(@PathVariable("id") Integer id){
        List<TbDiscuss> discusses = tbDiscussService.selectByKnowledgeId(id);
        return new Result(true,StatusCode.OK,"查询成功",discusses);
    }
    /**
     * @description: TODO 添加讨论消息
     * @return com.seven.rongxiaotong.common.Result
     * @author: juny
     * @date: 2023-07-01 下午4:10
     */
    @GetMapping("/addByKnowledge/{id}/{content}")
    public Result addByKnowledge(@PathVariable("id") Integer id,@PathVariable("content") String content){
        //获取用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();

        TbDiscuss tbDiscuss = new TbDiscuss();
        tbDiscuss.setKnowledgeId(id);
        tbDiscuss.setOwnName(name);
        tbDiscuss.setCreateTime(new Date());
        tbDiscuss.setContent(content);

        tbDiscussService.add(tbDiscuss);
        return new Result(true,StatusCode.OK,"查询成功",tbDiscuss);
    }
}
