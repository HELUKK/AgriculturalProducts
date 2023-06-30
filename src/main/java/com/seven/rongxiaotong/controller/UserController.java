package com.seven.rongxiaotong.controller;

import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.entity.request.UserRegisterRequest;
import com.seven.rongxiaotong.service.UserService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.seven.rongxiaotong.common.StatusCode.ERROR;
import static com.seven.rongxiaotong.common.StatusCode.OK;

/**
 * 用户接口
 *
 * @author wjh
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result<String> userRegister(UserRegisterRequest userRegisterRequest){
        System.out.println(userRegisterRequest.toString());

        if(userRegisterRequest == null){
            return new Result(false,ERROR,"请求为空");
        }

        String password = userRegisterRequest.getPassword();
        String nickName = userRegisterRequest.getNickName();
        String role = userRegisterRequest.getRole();
        if(StringUtils.isAnyBlank(password,nickName,role)){
            return new Result(false,ERROR,"密码、昵称和角色均不能为空");
        }
        String result = userService.userRegister( password, nickName, role);
        if(result.length()==1){
            if(result.equals("0")){
                return new Result(false,ERROR,"密码：以字母开头，长度在6-18之间，只能包含英文字符、数字和下划线");
            } else if(result.equals("1")){
                return new Result(false,ERROR,"未选择你的角色");
            }
        }
        String message = "恭喜你，注册成功,您的账号是:"+result;
        return new Result(true,OK,message,result);
    }
}
