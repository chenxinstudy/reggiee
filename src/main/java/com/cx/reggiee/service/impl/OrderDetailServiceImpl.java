package com.cx.reggiee.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.reggiee.entity.OrderDetail;
import com.cx.reggiee.mapper.OrderDetailMapper;
import com.cx.reggiee.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}