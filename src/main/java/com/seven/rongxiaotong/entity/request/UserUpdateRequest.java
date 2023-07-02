package com.seven.rongxiaotong.entity.request;

public class UserUpdateRequest {
    private String userName;
    private String nickName;
    private String phone;
    private String identityNum;
    private String address;
    private String avatar;
    private String realName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String userName, String nickName, String phone, String identityNum, String address, String avatar, String realName) {
        this.userName = userName;
        this.nickName = nickName;
        this.phone = phone;
        this.identityNum = identityNum;
        this.address = address;
        this.avatar = avatar;
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "UserUpdateRequest{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", identityNum='" + identityNum + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
