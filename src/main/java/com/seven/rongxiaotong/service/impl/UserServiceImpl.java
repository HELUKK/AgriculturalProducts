package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.entity.request.UserUpdateRequest;
import com.seven.rongxiaotong.mapper.UserMapper;
import com.seven.rongxiaotong.entity.User;
import com.seven.rongxiaotong.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author wenjh
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-06-28 14:54:02
 */
//添加属性的检查条件
//1 用户名(账号)：以字母开头，长度在3-10之间，只能包含英文字符、数字
//2 密码：以字母开头，长度在6-18之间，只能包含英文字符、数字和下划线
//3 昵称：不能为空
//4 手机号：数字组成，第一位为【1】，第二位为【3|4|5|7|8】，共11位
//5 身份证号：15或18位有效身份证号
//6 地址：长度在2-30之间
//7 角色：由【user】,【expert】,【admin】组成
//@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService{
    @Resource
    private  UserMapper userMapper;

    /**
     * @author wjh
     * @create 2023/6/29
     *
     **/

    private Integer pageSize = 10;
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String PASSWORD = "^[a-zA-Z][a-zA-Z0-9_]{5,17}$";
    private static final String ROLE = "^(user|expert|admin)$";
    @Override
    public String userRegister(String password,String nickName,String role){
        //controller中已经验证过非空不用反复验证

        //注册只拿必要的值,以方便用户,其他值可以让用户注册结束之后看需求补充信息.
        //1.验证
        //1.2 密码：以字母开头，长度在6-18之间，只能包含英文字符、数字和下划线
        Pattern pattern = Pattern.compile(PASSWORD);
        if (pattern.matcher(password).matches()) {
            System.out.println("密码有效");
        } else {
            //密码不合规
            return "0";
        }
        //1.3 昵称：不能为空,controller已验证
        //1.7 角色：由【user】,【expert】,【admin】组成
        pattern = Pattern.compile(ROLE);
        if (pattern.matcher(role).matches()) {
            System.out.println("角色有效");
        } else {
            //角色不合规
            return "1";
        }

        //生成账号
        String userName = generateUserName();
        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        long count = userMapper.selectCount(queryWrapper);
//        System.out.println(count);
        while (count > 0){
            userName = generateUserName();
            count = userMapper.selectCount(queryWrapper);
        }

        //密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptPassword = passwordEncoder.encode(password);
        //插入数据到数据库
        User user = new User();
        user.setUserName(userName);
        user.setPassword(encryptPassword);
        user.setRole(role);
        user.setNickName(nickName);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        boolean saveResult = this.save(user);
        if(!saveResult){
            return "2";
        }
        return userName;
    }

    @Override
    public User selectByUserName(String username) {
        User user = userMapper.selectByUserName(username);
        return user;
    }


    /**
     * 更新密码
     * @author wjh
     * @create 2023/6/30
     *
     * @param newPassword 用户重新设置的密码
     * @return java.lang.String
     **/
    @Override
    public int userRePassword(String newPassword) {
        // 获取当前用户
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = principal.getUsername();
//        String userName = "wjh";//测试使用
        //1.2 校验密码：以字母开头，长度在6-18之间，只能包含英文字符、数字和下划线
        Pattern pattern = Pattern.compile(PASSWORD);
        if (pattern.matcher(newPassword).matches()) {
            //密码加密后加入数据库
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encryptPassword = passwordEncoder.encode(newPassword);
            userMapper.updatePasswordByUserName(userName,encryptPassword);
            System.out.println("密码更新成功");
            return 1;
        } else {
            //密码不合规
            return 0;
        }

    }

    /**
     * 通过用户名更新用户数据
     * @author wjh
     * @create 2023/7/2
     *
     * @param userUpdateRequest 更新用户数据
     * @return void
     **/
    @Override
    public void loginUpdateByUsername(UserUpdateRequest userUpdateRequest) {
        //更新用户信息
        userMapper.updateUpdateTimeByUserName(userUpdateRequest.getUserName(),new Date());
        User user = userMapper.selectByUserName(userUpdateRequest.getUserName());
        BeanUtils.copyProperties(userUpdateRequest,user);
        userMapper.updateById(user);
    }

    /**
     * 查询所有用户数据
     * @author wjh
     * @create 2023/7/2
     *
     * @return java.util.List<com.seven.rongxiaotong.entity.User>
     **/
    @Override
    public List<User> selectAllUser() {
        return userMapper.selectList(null);
    }

    /**
     * 分页查询所有用户
     * @author wjh
     * @create 2023/7/2
     *
     * @param pageNum 页码
     * @return com.github.pagehelper.PageInfo<com.seven.rongxiaotong.entity.User>
     **/
    @Override
    public PageInfo<User> selectAllUserPage(Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.selectList(null);
        PageInfo<User> userPageInfo = new PageInfo<User>(users);
        return userPageInfo;
    }

    @Override
    public PageInfo<User> selectAllUserPage(Integer pageNum, User user) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.selectAllUserPage(user);
        PageInfo<User> userPageInfo = new PageInfo<User>(users);
        return userPageInfo;
    }

    /**
     * 生成用户名(账号)
     * @author wjh
     * @create 2023/7/2
     *
     * @return java.lang.String
     **/
    public static String generateUserName(){
        //生成账号
        Random random = new Random();
        StringBuilder userName = new StringBuilder();
        //以字母开头
        userName.append(ALLOWED_CHARACTERS.charAt(random.nextInt(52)));
        //长度在3-10之间,字母占1,其他2-9
        int length = random.nextInt(8) + 2;
        for (int i = 0; i < length; i++) {
            userName.append(ALLOWED_CHARACTERS.charAt(random.nextInt(62)));
        }
        return userName.toString();
    }

}





