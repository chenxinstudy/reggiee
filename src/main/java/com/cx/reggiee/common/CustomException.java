package com.cx.reggiee.common;
/** Created with IntelliJ IDEA. @Author:cx @Description: @Date: 2022/04/27/19:55:56 */

/**
 * @program: reggiee
 * @description: 自定义业务异常
 * @author: 科城小鑫
 */
public class CustomException extends RuntimeException {
  public CustomException(String message) {
    super(message);
  }
}
