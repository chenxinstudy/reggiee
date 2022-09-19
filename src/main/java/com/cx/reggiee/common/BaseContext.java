package com.cx.reggiee.common;

/**
 * @program: reggiee
 * @description: 获取threadLocal的id，保存和获取当前用户的ID
 * @author: 科城小鑫
 */
public class BaseContext {
  /**
   * 以线程为单位
   */
  private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

  public static void setCurrentId(Long id) {
    threadLocal.set(id);
  }

  public static Long getCurrentId() {
    return threadLocal.get();
  }
}
