package com.cx.reggiee.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: reggiee
 * @description: 填充字段配置类
 * @author: 科城小鑫
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
  /**
   * 插入自动填充
   *
   * @param metaObject
   */
  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("公共字段自动填充 insert...");
    log.info(metaObject.toString());
    metaObject.setValue("createTime", LocalDateTime.now());
    metaObject.setValue("updateTime", LocalDateTime.now());
    metaObject.setValue("createUser", BaseContext.getCurrentId());
    metaObject.setValue("updateUser", BaseContext.getCurrentId());
  }

  @Override
  /** 更新自动填充 */
  public void updateFill(MetaObject metaObject) {
    log.info("公共字段自动填充 update...");
    log.info(metaObject.toString());
    long id = Thread.currentThread().getId();
    log.info("线程id:{}", id);
    metaObject.setValue("updateTime", LocalDateTime.now());
    metaObject.setValue("updateUser", BaseContext.getCurrentId());
  }
}
