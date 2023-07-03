package com.seven.rongxiaotong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.rongxiaotong.entity.Question;

import java.util.List;

/**
* @author wenjh
* @description 针对表【question(在线问答表)】的数据库操作Mapper
* @createDate 2023-06-30 14:41:30
* @Entity generator.domain.Question
*/
public interface QuestionMapper extends BaseMapper<Question> {

    void insertOne(Question question);

    List<Question> selectAllByKeys(String keys);

    List<Question> selectQuestionByNowUser(Question question);
}




