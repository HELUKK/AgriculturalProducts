package com.seven.rongxiaotong.security.service;

import com.seven.rongxiaotong.entity.User;
import com.seven.rongxiaotong.security.entity.JwtUser;
import com.seven.rongxiaotong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("%s.这个用户不存在", username));
        }

        String role = user.getRole();
        return new JwtUser(user.getUserName(),user.getNickName(), user.getPassword(), user.getAvatar(), AuthorityUtils.commaSeparatedStringToAuthorityList(role));
    }
}
