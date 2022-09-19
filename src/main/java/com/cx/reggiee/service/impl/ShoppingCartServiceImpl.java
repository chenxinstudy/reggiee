package com.cx.reggiee.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.reggiee.entity.ShoppingCart;
import com.cx.reggiee.mapper.ShoppingCartMapper;
import com.cx.reggiee.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
