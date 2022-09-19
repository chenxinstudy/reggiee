package com.cx.reggiee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.reggiee.common.CustomException;
import com.cx.reggiee.entity.Category;
import com.cx.reggiee.entity.Dish;
import com.cx.reggiee.entity.Setmeal;
import com.cx.reggiee.mapper.CategoryMapper;
import com.cx.reggiee.service.CategoryService;
import com.cx.reggiee.service.DishService;
import com.cx.reggiee.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 酷酷的鑫
 * @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
 * @createDate 2022-04-27 17:13:45
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {
  @Autowired
  private DishService dishService;
  @Autowired
  private SetmealService setmealService;

  /** 根据id删除分类 判断是否关联 */
  @Override
  public void remove(Long ids) {
    // 查询当前分类是否关联菜品，如果已经关联，就抛出一个异常
    LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
    // 根据菜品id查询
    dishLambdaQueryWrapper.eq(Dish::getCategoryId, ids);
    long count = dishService.count(dishLambdaQueryWrapper);
    if (count > 0) {
      // 抛出业务异常
      throw new CustomException("当前分类下关联了菜品，不能删除");
    }

    // 查询当前分类是否关联套餐，如果已经关联，就抛出一个异常
    LambdaQueryWrapper<Setmeal> setMealWrapper = new LambdaQueryWrapper<>();
    setMealWrapper.eq(Setmeal::getCategoryId, ids);
    long count1 = setmealService.count(setMealWrapper);
    if (count1 > 0) {
      // 抛出业务异常
      throw new CustomException("当前分类下关联了套餐，不能删除");
    }
    // 正常删除
    super.removeById(ids);
  }
}
