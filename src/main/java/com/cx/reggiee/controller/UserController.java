package com.cx.reggiee.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cx.reggiee.common.R;
import com.cx.reggiee.entity.User;
import com.cx.reggiee.service.UserService;
import com.cx.reggiee.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 酷酷的鑫
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired private UserService userService;

  /**
   * 发送手机短信验证码
   *
   * @param user
   * @return
   */
  //  @PostMapping("/sendMsg")
  //  public R<String> sendMsg(@RequestBody User user, HttpSession session) {
  //    // 获取手机号
  //    String phone = user.getPhone();
  //
  //    if (StringUtils.isNotEmpty(phone)) {
  //      // 生成随机的4位验证码
  //      String code = ValidateCodeUtils.generateValidateCode(4).toString();
  //      log.info("code={}", code);
  //
  //      // 调用阿里云提供的短信服务API完成发送短信
  //      // SMSUtils.sendMessage("瑞吉外卖","",phone,code);
  //
  //      // 需要将生成的验证码保存到Session
  //      session.setAttribute(phone, code);
  //
  //      return R.success("手机验证码短信发送成功");
  //    }
  //
  //    return R.error("短信发送失败");
  //  }

  /**
   * 移动端用户登录
   *
   * @param map
   * @param session
   * @return
   */
  @PostMapping("/login")
  public R<User> login(@RequestBody Map map, HttpSession session) {
    log.info(map.toString());

    // 获取手机号
    String phone = map.get("phone").toString();

    // 获取验证码
    String code = map.get("code").toString();

    // 从Session中获取保存的验证码
    Object codeInSession = session.getAttribute(phone);

    // 进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
    if (codeInSession != null && codeInSession.equals(code)) {
      // 如果能够比对成功，说明登录成功
      LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.eq(User::getPhone, phone);

      User user = userService.getOne(queryWrapper);
      if (user == null) {
        // 判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
        user = new User();
        user.setPhone(phone);
        user.setStatus(1);
        // 取邮箱前五位为用户名
        user.setName(phone.substring(0,6));
        userService.save(user);
      }
      session.setAttribute("user", user.getId());
      return R.success(user);
    }
    return R.error("登录失败");
  }

  /**
   * 发送邮箱验证码
   *
   * @param user
   * @return
   */
  @PostMapping("/sendMsg")
  public R<String> sendMsg(@RequestBody User user, HttpSession session) {
    // 获取邮箱账号
    String phone = user.getPhone();

    String subject = "瑞吉外卖登录验证码";

    if (StringUtils.isNotEmpty(phone)) {
      String code = ValidateCodeUtils.generateValidateCode(6).toString();
      String context = "欢迎使用瑞吉外卖，登录验证码为: " + code + ",请妥善保管!";

      //String context = "欢迎使用瑞吉外卖，登录验证码为: " + code + ",五分钟内有效，请妥善保管!";
      log.info("code={}", code);

      // 真正地发送邮箱验证码
      userService.sendMsg(phone, subject, context);

      //  将随机生成的验证码保存到session中
      session.setAttribute(phone, code);

      // 验证码由保存到session 优化为 缓存到Redis中，并且设置验证码的有效时间为 5分钟
      //      redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

      return R.success("验证码发送成功，请及时查看!");
    }
    return R.error("验证码发送失败，请重新输入!");
  }
}
