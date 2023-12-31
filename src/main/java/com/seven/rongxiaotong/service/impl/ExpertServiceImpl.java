package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.Expert;
import com.seven.rongxiaotong.mapper.ExpertMapper;
import com.seven.rongxiaotong.service.ExpertService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author wenjh
* @description 针对表【expert(专家信息表)】的数据库操作Service实现
* @createDate 2023-06-30 15:06:37
*/
@Service
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert>
    implements ExpertService{

    @Resource
    private ExpertMapper expertMapper;
    @Override
    public Expert selectByUserName(String userName) {
        return expertMapper.selectExpertByUserName(userName);
    }

    @Override
    public void insert(Expert expert) {
        expertMapper.insert(expert);
    }
}




