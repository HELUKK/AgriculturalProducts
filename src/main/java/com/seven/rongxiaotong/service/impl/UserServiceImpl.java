package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.mapper.UserMapper;
import com.seven.rongxiaotong.entity.User;
import com.seven.rongxiaotong.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author wenjh
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-06-28 14:54:02
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




