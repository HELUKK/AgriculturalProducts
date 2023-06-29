package com.seven.rongxiaotong.entity.request;


/**
 * 用户注册请求体
 * @author wjh
 * @create 2023/6/29
 **/
public class UserRegisterRequest {
    private String password;
    private String nickName;
    private String role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
