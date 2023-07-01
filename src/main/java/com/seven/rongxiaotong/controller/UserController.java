package com.seven.rongxiaotong.controller;

import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.entity.request.UserRegisterRequest;
import com.seven.rongxiaotong.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.seven.rongxiaotong.common.StatusCode.ERROR;
import static com.seven.rongxiaotong.common.StatusCode.OK;

/**
 * 用户接口
 *
 * @author wjh
 */
//跨域
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @author wjh
     * @create 2023/6/30
     **/

    @PostMapping("/register")
    public Result<String> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
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
            } else if(result.equals("2")){
                return new Result(false,ERROR,"注册失败,请重新注册");
            }
        }
        String message = "恭喜你，注册成功,您的账号是:"+result;
        return new Result(true,OK,message,result);
    }

    /**
     * 修改密码
     * @author wjh
     * @create 2023/6/30
     **/
    @PostMapping ("/rePassword")
    public Result<String> userRename(@RequestParam("userName") String userName,@RequestParam("newPassword") String newPassword){
        //用户名是否重复 0 -- 重复   1 -- 没重复
        System.out.println("当前用户:"+userName+";修改后用户密码:"+newPassword);
        int flag = userService.userRePassword(userName,newPassword);
        if(flag == 1){
            return new Result(true,OK,"您的密码已成功修改");
        }else{
            return new Result(false,ERROR,"密码修改失败");
        }
    }
}
