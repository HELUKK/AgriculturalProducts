package com.seven.rongxiaotong.security.filter;

import com.alibaba.druid.util.StringUtils;
import com.seven.rongxiaotong.security.util.JwtTokenUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class   JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(jwtTokenUtil.getHeader());

        System.out.println("这里是获取到的token： "+token);

        if (!StringUtils.isEmpty(token)) {

            // 去掉令牌中的bearer前缀
            token = token.replace("Bearer ","");
            System.out.println("这里是去掉bearer前缀的token： "+token);

            // 拿username
            String username = jwtTokenUtil.getUsernameFromToken(token);
            System.out.println("这里是获取到的username： "+username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println("");
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    // 将用户信息存入 authentication，方便后续校验
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息

                    System.out.println("待存入的用户信息是： "+authentication);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}