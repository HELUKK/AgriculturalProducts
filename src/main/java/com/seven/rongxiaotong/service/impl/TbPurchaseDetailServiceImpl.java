package com.seven.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.rongxiaotong.entity.TbPurchaseDetail ;
import com.seven.rongxiaotong.service.TbPurchaseDetailService;
import com.seven.rongxiaotong.mapper.TbPurchaseDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86152
* @description 针对表【tb_purchase_detail】的数据库操作Service实现
* @createDate 2023-07-02 14:11:22
*/
@Service
public class TbPurchaseDetailServiceImpl extends ServiceImpl<TbPurchaseDetailMapper, TbPurchaseDetail>
    implements TbPurchaseDetailService{

    @Resource
    private TbPurchaseDetailMapper tbPurchaseDetailMapper;

    @Override
    public void add(TbPurchaseDetail purchaseDetail) {
        tbPurchaseDetailMapper.insertSelective(purchaseDetail);
    }

    @Override
    public List<TbPurchaseDetail> selectByPurchaseId(Integer purchaseId) {
        List<TbPurchaseDetail> purchaseDetails = tbPurchaseDetailMapper.selectByPurchaseId(purchaseId);
        return purchaseDetails;
    }
}




