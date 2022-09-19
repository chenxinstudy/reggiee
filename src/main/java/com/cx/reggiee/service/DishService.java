package com.cx.reggiee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.reggiee.dto.DishDto;
import com.cx.reggiee.entity.Dish;

/**
 * @author 酷酷的鑫
 * @description 针对表【dish(菜品管理)】的数据库操作Service
 * @createDate 2022-04-27 19:38:23
 */
public interface DishService extends IService<Dish> {

    /**
     * 新增菜品同时添加口味数据   两张表
     *
     * @param dishDto
     */
    void saveWithFlavor(DishDto dishDto);

    /**
     * 根据id查询菜品和口味
     * @param id
     * @return
     */
    DishDto getByIdWithFlavor(Long id);

    /**
     * 修改菜品同时添加口味数据   两张表
     *
     * @param dishDto
     */
    void updateWithFlavor(DishDto dishDto);

}
