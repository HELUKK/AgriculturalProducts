package com.seven.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.rongxiaotong.entity.User;
import com.seven.rongxiaotong.entity.request.UserRegisterRequest;

/**
 * @author wenjh
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-06-28 14:54:02
 */
public interface UserService extends IService<User> {

    String userRegister(String password,String nickName,String role);

    User selectByUserName(String username);

    int userRePassword(String newPassword);
}
