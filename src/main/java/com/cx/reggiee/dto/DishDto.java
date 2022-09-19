package com.cx.reggiee.dto;

import com.cx.reggiee.entity.Dish;
import com.cx.reggiee.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 酷酷的鑫
 * DTO  data transfer object
 */
@Data
public class DishDto extends Dish {

  /** 菜品对应的口味数据 */
  private List<DishFlavor> flavors = new ArrayList<>();

  /**
  *分类名称
  */
  private String categoryName;

  /**
  *
  */
  private Integer copies;
}
