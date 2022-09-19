package com.cx.reggiee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.reggiee.dto.SetmealDto;
import com.cx.reggiee.entity.Setmeal;

import java.util.List;

/**
 * @author 酷酷的鑫
 * @description 针对表【setmeal(套餐)】的数据库操作Service
 * @createDate 2022-04-27 19:38:41
 */
public interface SetmealService extends IService<Setmeal> {
  /**
   * 新增套餐，同时需要保存套餐和菜品的关联关系
   *
   * @param setmealDto
   */
  void saveWithDish(SetmealDto setmealDto);

  /**
   * 删除套餐，同时需要删除套餐和菜品的关联数据
   *
   * @param ids
   */
  void removeWithDish(List<Long> ids);
}
