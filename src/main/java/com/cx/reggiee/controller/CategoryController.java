package com.cx.reggiee.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.reggiee.common.R;
import com.cx.reggiee.entity.Category;
import com.cx.reggiee.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: reggiee
 * @description: 分类controller
 * @author: 科城小鑫
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category:{}", category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * 分页查询功能
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        // 构造分页构造器
        Page<Category> pageInfo = new Page(page, pageSize);
        // 构造条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        // 排序
        queryWrapper.orderByAsc(Category::getSort);
        // 执行查询
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 删除功能
     */
    @DeleteMapping
    public R<String> delete(Long ids) {
        log.info("删除的id为{}", ids);
        categoryService.remove(ids);
        return R.success("删除成功");
    }

    /**
     * 修改菜品信息
     */
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("修改分类信息{}", category);
        categoryService.updateById(category);
        return R.success("修改分类信息成功");
    }

    /**
     * 根据条件查询分类数据
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}

