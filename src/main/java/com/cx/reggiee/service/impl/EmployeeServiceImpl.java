package com.cx.reggiee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.reggiee.entity.Employee;
import com.cx.reggiee.mapper.EmployeeMapper;
import com.cx.reggiee.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author 酷酷的鑫
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService {}
