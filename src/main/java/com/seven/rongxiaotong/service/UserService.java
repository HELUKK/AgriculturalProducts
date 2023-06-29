package com.seven.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.rongxiaotong.entity.User;

/**
* @author wenjh
* @description 针对表【user】的数据库操作Service
* @createDate 2023-06-28 14:54:02
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @author wjh
     * @create 2023/6/29
     *
     * @param password 密码：以字母开头，长度在6-18之间，只能包含英文字符、数字和下划线
     * @param nickName 昵称：不能为空
     * @param role 角色：由【user】,【expert】,【admin】组成
     * @return String
     **/
    String userRegister(String password,String nickName,String role);
}
