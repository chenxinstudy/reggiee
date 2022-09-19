package com.cx.reggiee.service.impl;
/** Created with IntelliJ IDEA. @Author:cx @Description: @Date: 2022/04/27/23:29:53 */
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.reggiee.entity.DishFlavor;
import com.cx.reggiee.mapper.DishFlavorMapper;
import com.cx.reggiee.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @program: reggiee
 * @description:
 * @author: 科城小鑫
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService {}
