package com.seven.rongxiaotong.controller;

import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.Expert;
import com.seven.rongxiaotong.entity.TbOrder;
import com.seven.rongxiaotong.entity.User;
import com.seven.rongxiaotong.entity.request.UserRegisterRequest;
import com.seven.rongxiaotong.entity.request.UserUpdateRequest;
import com.seven.rongxiaotong.mapper.TbOrderMapper;
import com.seven.rongxiaotong.mapper.UserMapper;
import com.seven.rongxiaotong.service.ExpertService;
import com.seven.rongxiaotong.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.seven.rongxiaotong.security.service.impl.JwtUserDetailsServiceImpl;
import com.seven.rongxiaotong.security.util.JwtTokenUtil;
import javax.annotation.Resource;

import java.util.List;

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

    @Resource
    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private TbOrderMapper tbOrderMapper;

    @Resource
    private ExpertService expertService;

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
    public Result<String> userRePassword(@RequestParam("newPassword") String newPassword){
        //用户名是否重复 0 -- 重复   1 -- 没重复
        System.out.println("修改后用户密码:"+newPassword);
        int flag = userService.userRePassword(newPassword);
        if(flag == 1){
            return new Result(true,OK,"您的密码已成功修改");
        }else{
            return new Result(false,ERROR,"密码修改失败");
        }
    }

    /**
     * 登录后获取用户信息,用于用户信息展示页
     * @author wjh
     * @create 2023/7/2
     **/
    @GetMapping("/loginSelectByUsername")
    public Result<User> loginSelectByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        User user = userService.selectByUserName(username);
        user.setPassword("这也偷看?");
        return new Result<>(true,OK,"用户信息查询成功",user);
    }

    /**
     * 根据用户名更新用户信息
     * @author wjh
     * @create 2023/7/2
     **/
    @PostMapping("/loginUpdateByUsername")
    public Result<String> loginUpdateByUsername(@RequestBody UserUpdateRequest userUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();

        userUpdateRequest.setUserName(username);
        userService.loginUpdateByUsername(userUpdateRequest);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return new Result<String>(true, StatusCode.OK, "修改成功", token);
    }

    /**
     * 查询所有用户
     * @author wjh
     * @create 2023/7/2
     * **/
    @GetMapping("/selectAllUser")
    public Result<List<User>> selectAllUser(){
        List<User> users = userService.selectAllUser();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", users);
    }

    /**
     * 根据用户名更新用户
     * @author wjh
     * @create 2023/7/2
     **/
    @PutMapping(value = "/{userName}")
    public Result<String> update(@Validated @RequestBody User user, BindingResult bindingResult, @PathVariable("userName") String userName) {
        if(user == null){
            return new Result<String>(true, ERROR, "user为null", "修改失败");
        }
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "信息修改失败", s);
        }
        if(userName != null){
            user.setUserName(userName);
            userService.updateById(user);
            return new Result<String>(true, StatusCode.OK, "信息修改成功", "修改成功");
        }else{
            return new Result<String>(true, ERROR, "userName为null,修改失败", "修改失败");
        }
    }

    /**
     * 根据用户名删除用户
     * @author wjh
     * @create 2023/7/2
     **/
    @DeleteMapping("/{userName}")
    public Result<String> deletes(@PathVariable("userName") String userName) {
        if(userName == null){
            return new Result<String>(false, StatusCode.ERROR, "删除失败", "该用户不存在");
        }
        //判断该用户是否有订单信息
        TbOrder order = new TbOrder();
        order.setOwnName(userName);
        List<TbOrder> orders = tbOrderMapper.selectByExample(order);
        if (!orders.isEmpty()) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败", "该用户有发布的订单，暂不能删除");
        }
        userService.removeById(userName);
        expertService.removeById(userName);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 按userName查找用户
     * @author wjh
     * @create 2023/7/2
     **/
    @GetMapping("/{userName}")
    public Result<User> selectByUserName(@PathVariable("userName") String userName){
        User user = userService.selectByUserName(userName);
        return new Result(true, StatusCode.OK, "删除成功",user);
    }

    /**
     * 分页查询所有用户
     * @author wjh
     * @create 2023/7/2
     *
     * @param pageNum 页码
     * @return com.seven.rongxiaotong.common.Result<com.github.pagehelper.PageInfo < com.seven.rongxiaotong.entity.User>>
     **/
    @GetMapping("/search/{pageNum}")
    public Result<PageInfo<User>> selectAllUserPage(@PathVariable("pageNum") Integer pageNum) {
        PageInfo<User> pageInfo = userService.selectAllUserPage(pageNum);
        return new Result(true, StatusCode.OK, "查询成功",pageInfo);
    }

    /**
     * 分页条件查询
     * userName,nickName,address,realName 模糊查询条件
     * role精准查询
     * @author wjh
     * @create 2023/7/2
     *
     * @param pageNum 页码
     * @param user 查询条件
     *
     * @return com.seven.rongxiaotong.common.Result<com.github.pagehelper.PageInfo < com.seven.rongxiaotong.entity.User>>
     **/
    @PostMapping("/search/{pageNum}")
    public Result<PageInfo<User>> selectAllUser(@PathVariable("pageNum") Integer pageNum,@RequestBody User user) {
        PageInfo<User> userPageInfo = userService.selectAllUserPage(pageNum,user);
        return new Result(true, StatusCode.OK, "查询成功",userPageInfo);
    }

    /**
     * 查看登录专家信息
     * @author wjh
     * @create 2023/7/3
     *
     * @return com.seven.rongxiaotong.common.Result<com.seven.rongxiaotong.entity.Expert>
     **/
    @GetMapping("/searchExpertByUserName")
    public Result<Expert> searchExpertByUserName() {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = principal.getUsername();
        Expert expert = expertService.selectByUserName(userName);
        return new Result(true, StatusCode.OK, "查询成功",expert);
    }

    /**
     * 查询所有专家存入list
     * @author wjh
     * @create 2023/7/3
     *
     * @return com.seven.rongxiaotong.common.Result<java.util.List < com.seven.rongxiaotong.entity.Expert>>
     **/
    @GetMapping("/searchAllExpert")
    public Result<List<Expert>> searchAllExpert() {
        List<Expert> experts = expertService.list(null);
        return new Result(true, StatusCode.OK, "查询成功",experts);
    }

    /**
     * 根据用户名删除专家
     * @author wjh
     * @create 2023/7/3
     *
     * @param userName 用户名
     * @return com.seven.rongxiaotong.common.Result
     **/
    @DeleteMapping("/deleteExpert/{userName}")
    public Result deleteExpert(@PathVariable("userName") String userName){
        if(userName == null){
            return new Result(true, StatusCode.OK, "用户名不存在");
        } else {
            expertService.removeById(userName);
            return new Result(true, StatusCode.OK, "删除成功");
        }
    }

    /**
     * 根据用户名更新专家信息
     * @author wjh
     * @create 2023/7/3
     *
     * @param expert 专家信息 包括用户名
     * @return com.seven.rongxiaotong.common.Result
     **/
    @PutMapping("/updateExpert")
    public Result updateExpert(@RequestBody Expert expert) {
        expertService.updateById(expert);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 新增专家信息
     * @author wjh
     * @create 2023/7/3
     *
     * @param expert 专家信息
     * @return com.seven.rongxiaotong.common.Result
     **/
    @PostMapping("/addExpert")
    public Result addExpert(Expert expert) {
        expertService.insert(expert);
        return new Result(true, StatusCode.OK, "新增专家数据成功");
    }

    @PostMapping("/addOrUpdateExpert")
    public Result addOrUpdateExpert(@RequestBody Expert expert) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = principal.getUsername();
        Expert expert1 = expertService.selectByUserName(userName);
        expert.setUserName(userName);
        if(expert1 != null){
            expertService.updateById(expert);
            return new Result(true, StatusCode.OK, "修改专家数据成功");
        }else {
            expertService.insert(expert);
            return new Result(true, StatusCode.OK, "新增专家数据成功");
        }
    }
}
