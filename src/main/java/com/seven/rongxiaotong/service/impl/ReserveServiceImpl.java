package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.Reserve;
import com.seven.rongxiaotong.mapper.ReserveMapper;
import com.seven.rongxiaotong.service.ReserveService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author wenjh
* @description 针对表【reserve】的数据库操作Service实现
* @createDate 2023-07-03 11:37:12
*/
@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve>
    implements ReserveService{

    @Resource
    private ReserveMapper reserveMapper;
    @Override
    public void addReserve(Reserve reserve) {
        reserveMapper.insert(reserve);
    }

    @Override
    public Reserve selectReserveById(Integer id) {
        Reserve reserve = reserveMapper.selectById(id);
        return reserve;
    }

    @Override
    public List<Reserve> selectReserveByNowUser(String type) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        Reserve reserve = new Reserve();
        if(type.equals("questioner")) {
            reserve.setQuestioner(name);
        } else {
            reserve.setExpertName(name);
        }
        List<Reserve> reserves = reserveMapper.selectReserveByNowUser(reserve);
        return reserves;
    }
}




