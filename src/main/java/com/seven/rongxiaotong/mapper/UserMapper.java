package com.seven.rongxiaotong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.rongxiaotong.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author wenjh
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-06-28 14:54:02
* @Entity com.wjh.agriculturalproducts.model.User
*/
public interface UserMapper extends BaseMapper<User> {

    User selectByUserName(String userName);

    void updatePasswordByUserName(@Param("userName") String userName, @Param("encryptPassword") String encryptPassword);

    void updateUpdateTimeByUserName(String userName, Date updateTime);

    List<User> selectAllUserPage(User user);
}




