package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.entity.Expert;
import com.seven.rongxiaotong.entity.Question;
import com.seven.rongxiaotong.mapper.ExpertMapper;
import com.seven.rongxiaotong.mapper.QuestionMapper;
import com.seven.rongxiaotong.service.QuestionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author wenjh
* @description 针对表【question(在线问答表)】的数据库操作Service实现
* @createDate 2023-06-30 14:41:30
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private ExpertMapper expertMapper;

    private Integer pageSize = 30;

    /**
     * 根据id查询询问情况
     * @author wjh
     * @create 2023/6/30
     *
     * @param id id
     * @return com.seven.rongxiaotong.entity.Question
     **/
    @Override
    public Question selectById(Integer id) {
        Question question = questionMapper.selectById(id);
        return question;
    }

    /**
     * 插入数据
     * @author wjh
     * @create 2023/7/1
     *
     * @param question 插入的问题
     * @return void
     **/
    @Override
    public void insert(Question question) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
//        String name = "wjh";//测试数据
        question.setQuestioner(name);
        question.setId(null);
        questionMapper.insertOne(question);
    }

    /**
     * 以标题为keys模糊查询第pageNum的数据,每页30行
     * @author wjh
     * @create 2023/7/1
     *
     * @param keys 标题
     * @param pageNum 页码
     * @return com.github.pagehelper.PageInfo<com.seven.rongxiaotong.entity.Question>
     **/
    @Override
    public PageInfo<Question> selectByKeys(String keys, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Question> questions = questionMapper.selectAllByKeys(keys);
        PageInfo<Question> questionPageInfo = new PageInfo<>(questions);
        return questionPageInfo;
    }

    @Override
    public PageInfo<Expert> selectExpert(Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Expert> experts = expertMapper.selectList(null);
        PageInfo<Expert> expertPageInfo = new PageInfo<>(experts);
        return expertPageInfo;
    }

    @Override
    public PageInfo<Expert> selectExpertByKeys(String keys, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Expert> experts = expertMapper.selectByKeys(keys);
        PageInfo<Expert> expertPageInfo = new PageInfo<>(experts);
        return expertPageInfo;
    }
}




