package com.cx.reggiee.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.reggiee.common.R;
import com.cx.reggiee.dto.DishDto;
import com.cx.reggiee.entity.Category;
import com.cx.reggiee.entity.Dish;
import com.cx.reggiee.entity.DishFlavor;
import com.cx.reggiee.service.CategoryService;
import com.cx.reggiee.service.DishFlavorService;
import com.cx.reggiee.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: reggiee
 * @description: 菜品controller
 * @author: 科城小鑫
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

  @Autowired private DishService dishService;
  @Autowired private DishFlavorService dishFlavorService;
  @Autowired private CategoryService categoryService;

  /** dish分页查询 */
  @GetMapping("/page")
  public R<Page> page(String name, int page, int pageSize) {
    Page<Dish> pageInfo = new Page<>(page, pageSize);
    Page<DishDto> dishDtoPage = new Page<>();

    LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.like(name != null, Dish::getName, name);
    queryWrapper.orderByDesc(Dish::getUpdateTime);
    dishService.page(pageInfo, queryWrapper);
    // 对象拷贝,除了records之外的属性
    BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");
    // 获得records
    List<Dish> records = pageInfo.getRecords();

    List<DishDto> list =
        records.stream()
            .map(
                (item) -> {
                  DishDto dishDto = new DishDto();
                  BeanUtils.copyProperties(item, dishDto);
                  Long categoryId = item.getCategoryId();

                  // 根据id查询分类对象
                  Category category = categoryService.getById(categoryId);
                  if (category != null) {
                    String categoryName = category.getName();
                    dishDto.setCategoryName(categoryName);
                  }

                  return dishDto;
                })
            .collect(Collectors.toList());

    dishDtoPage.setRecords(list);

    return R.success(dishDtoPage);
  }

  /** 新增菜品 */
  @PostMapping
  public R<String> save(@RequestBody DishDto dishDto) {
    log.info("dishDto；{}", dishDto);
    dishService.saveWithFlavor(dishDto);
    return R.success("新增菜品成功");
  }

  /** 根据id查询菜品信息和口味信息 */
  @GetMapping("/{id}")
  public R<DishDto> get(@PathVariable Long id) {
    DishDto dishDto = dishService.getByIdWithFlavor(id);
    return R.success(dishDto);
  }

  /** 修改菜品 */
  @PutMapping
  public R<String> update(@RequestBody DishDto dishDto) {
    log.info("dishDto；{}", dishDto);
    dishService.updateWithFlavor(dishDto);
    return R.success("修改菜品成功");
  }

  /**
   * 根据条件查询
   *
   * @param dish
   * @return
   */
  //  @GetMapping("/list")
  //  public R<List<Dish>> list(Dish dish) {
  //    LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
  //    queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
  //    //查询状态为在售状态 1
  //    queryWrapper.eq(Dish::getStatus, 1);
  //    queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
  //    List<Dish> list = dishService.list(queryWrapper);
  //    return R.success(list);
  //  }
  @GetMapping("/list")
  public R<List<DishDto>> list(Dish dish) {
    // 构造查询条件
    LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
    // 添加条件，查询状态为1（起售状态）的菜品
    queryWrapper.eq(Dish::getStatus, 1);

    // 添加排序条件
    queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

    List<Dish> list = dishService.list(queryWrapper);

    List<DishDto> dishDtoList =
        list.stream()
            .map(
                (item) -> {
                  DishDto dishDto = new DishDto();

                  BeanUtils.copyProperties(item, dishDto);

                  // 分类id
                  Long categoryId = item.getCategoryId();
                  // 根据id查询分类对象
                  Category category = categoryService.getById(categoryId);

                  if (category != null) {
                    String categoryName = category.getName();
                    dishDto.setCategoryName(categoryName);
                  }

                  // 当前菜品的id
                  Long dishId = item.getId();
                  LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                  lambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
                  // SQL:select * from dish_flavor where dish_id = ?
                  List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
                  dishDto.setFlavors(dishFlavorList);
                  return dishDto;
                })
            .collect(Collectors.toList());

    return R.success(dishDtoList);
  }
}
