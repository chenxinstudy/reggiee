package com.cx.reggiee.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.reggiee.common.R;
import com.cx.reggiee.entity.Employee;
import com.cx.reggiee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author 酷酷的鑫
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  /** 登录功能 */
  @PostMapping("/login")
  public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
    String password = employee.getPassword();
    password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

    LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Employee::getUsername, employee.getUsername());
    Employee one = employeeService.getOne(queryWrapper);

    if (one == null) {
      return R.error("登录失败");
    }
    if (!one.getPassword().equals(password)) {
      return R.error("登录失败");
    }
    if (one.getStatus() == 0) {
      return R.error("帐号被禁用");
    }

    request.getSession().setAttribute("employee", one.getId());
    return R.success(one);
  }

  /** 退出功能 */
  @PostMapping("/logout")
  public R<String> logout(HttpServletRequest request) {
    // 清理Session中保存的ID
    request.getSession().removeAttribute("employee");
    return R.success("退出成功!");
  }
  /** 新增员工 */
  @PostMapping
  public R<String> save(@RequestBody Employee employee,HttpServletRequest request) {
    log.info("新增员工，员工信息：{}", employee.toString());
    //md5加密 设置初始密码123456
    employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
//    employee.setCreateTime(LocalDateTime.now());
//    employee.setUpdateTime(LocalDateTime.now());

    //获取当前登录用户的id
    Long empId = (Long)request.getSession().getAttribute("employee");
//    employee.setCreateUser(empId);
//    employee.setUpdateUser(empId);

    employeeService.save(employee);
    return R.success("新增员工成功");
  }

  /**
  *员工信息分页查询
  */
  @GetMapping("/page")
  public R<Page> page(int page,int pageSize,String name){
    log.info("page={},pageSize={},name={}",page,pageSize,name);
    //构造分页构造器
    Page pageInfo = new Page(page, pageSize);
    //构造条件构造器
    LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
    //过滤条件
    queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
    //执行查询
    employeeService.page(pageInfo,queryWrapper);

    return R.success(pageInfo);
  }

  /**
  *根据ID修改员工帐号
  */
  @PutMapping
  public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
    Long empId = (Long) request.getSession().getAttribute("employee");
    log.info(employee.toString());
    long id = Thread.currentThread().getId();
    log.info("线程id:{}",id);
//    employee.setUpdateTime(LocalDateTime.now());
//    employee.setUpdateUser(empId);
    employeeService.updateById(employee);

    return R.success("员工信息修改成功");
  }

  @GetMapping("/{id}")
  public R<Employee> getById(@PathVariable long id){
    log.info("根据员工ID查询信息");
    Employee employee = employeeService.getById(id);
    if (employee != null){
      return R.success(employee);
    }
    return R.error("没有查询到对应的员工信息");
  }


}
