package com.cx.reggiee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.reggiee.entity.Orders;

/**
 * @author 酷酷的鑫
 */
public interface OrderService extends IService<Orders> {

  /**
   * 用户下单
   *
   * @param orders
   */
  void submit(Orders orders);
}
