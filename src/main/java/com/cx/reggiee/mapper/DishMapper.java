package com.cx.reggiee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.reggiee.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 酷酷的鑫
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2022-04-27 19:38:23
* @Entity com.cx.reggiee.entity.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}




