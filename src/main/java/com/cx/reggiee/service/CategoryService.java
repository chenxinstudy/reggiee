package com.cx.reggiee.service;

import com.cx.reggiee.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 酷酷的鑫
 * @description 针对表【category(菜品及套餐分类)】的数据库操作Service
 * @createDate 2022-04-27 17:13:45
 */
public interface CategoryService extends IService<Category> {
  /**
   * @param id
  * 删除
  */
  void remove(Long id);
}
