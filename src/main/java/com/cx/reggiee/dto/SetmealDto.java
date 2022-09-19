package com.cx.reggiee.dto;

import com.cx.reggiee.entity.Setmeal;
import com.cx.reggiee.entity.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @author 酷酷的鑫
 */
@Data
public class SetmealDto extends Setmeal {

  private List<SetmealDish> setmealDishes;

  private String categoryName;
}
