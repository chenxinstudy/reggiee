package com.cx.reggiee.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.reggiee.entity.User;

/**
 * @author 酷酷的鑫
 */
public interface UserService extends IService<User> {
    /**
     * 发送邮箱
     * @param to
     * @param subject
     * @param context
     */
    void sendMsg(String to,String subject,String context);

}
