package com.cx.reggiee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.reggiee.dto.DishDto;
import com.cx.reggiee.entity.Dish;
import com.cx.reggiee.entity.DishFlavor;
import com.cx.reggiee.mapper.DishMapper;
import com.cx.reggiee.service.DishFlavorService;
import com.cx.reggiee.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 酷酷的鑫
 * @description 针对表【dish(菜品管理)】的数据库操作Service实现
 * @createDate 2022-04-27 19:38:23
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

  @Autowired private DishFlavorService dishFlavorService;
  private DishService dishService;

  /** 新增菜品同时添加口味 两张表 */
  @Override
  @Transactional
  public void saveWithFlavor(DishDto dishDto) {
    // 保存基本信息到菜品表
    this.save(dishDto);
    // 保存dishId
    Long dishId = dishDto.getId();

    List<DishFlavor> flavors = dishDto.getFlavors();
    flavors =
        flavors.stream()
            .map(
                (item) -> {
                  item.setDishId(dishId);
                  return item;
                })
            .collect(Collectors.toList());

    // 保存到口味表  集合批量保存
    dishFlavorService.saveBatch(flavors);
  }

  /**
   * 根据id查询菜品和口味
   *
   * @param id
   * @return
   */
  @Override
  public DishDto getByIdWithFlavor(Long id) {
    DishDto dishDto = new DishDto();
    // 根据id查询菜品基本信息
    Dish dish = this.getById(id);
    LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(DishFlavor::getDishId, dish.getId());
    List<DishFlavor> dishFlavors = dishFlavorService.list(queryWrapper);
    dishDto.setFlavors(dishFlavors);
    BeanUtils.copyProperties(dish, dishDto);
    return dishDto;
  }

  /** 修改菜品，同时修改口味 两张表 */
  @Override
  @Transactional
  public void updateWithFlavor(DishDto dishDto) {
    // 更新基本信息到菜品表
    this.updateById(dishDto);
    //清除当前菜品口味
    LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
    dishFlavorService.remove(queryWrapper);

    //添加提交过来的口味数据
    List<DishFlavor> flavors = dishDto.getFlavors();
    flavors =
        flavors.stream()
            .map(
                (item) -> {
                  item.setDishId(dishDto.getId());
                  return item;
                })
            .collect(Collectors.toList());

    // 保存到口味表  集合批量保存
    dishFlavorService.saveBatch(flavors);
  }
}
