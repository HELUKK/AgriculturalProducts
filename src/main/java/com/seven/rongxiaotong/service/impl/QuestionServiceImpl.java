package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.Question;

import com.seven.rongxiaotong.mapper.QuestionMapper;
import com.seven.rongxiaotong.service.ExpertService;
import com.seven.rongxiaotong.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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


    private Integer pageSize = 30;

    /**
     * 根据id查询询问情况
     * @author wjh
     * @create 2023/6/30
     *
     * @param id
     * @return com.seven.rongxiaotong.entity.Question
     **/
    @Override
    public Question selectById(Integer id) {
        Question question = questionMapper.selectById(id);
        return question;
    }

    @Override
    public void insert(Question question) {

    }
}




