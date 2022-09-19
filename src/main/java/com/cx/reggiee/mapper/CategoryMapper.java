package com.cx.reggiee.mapper;

import com.cx.reggiee.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 酷酷的鑫
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2022-04-27 17:13:45
* @Entity com.cx.reggiee.entity.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




