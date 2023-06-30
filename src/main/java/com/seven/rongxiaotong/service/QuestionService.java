package com.seven.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.rongxiaotong.entity.Question;

/**
* @author wenjh
* @description 针对表【question(在线问答表)】的数据库操作Service
* @createDate 2023-06-30 14:41:30
*/
public interface QuestionService extends IService<Question> {

    Question selectById(Integer id);

    void insert(Question question);
}
