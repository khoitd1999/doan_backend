package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.Employee;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.repository.AreaRepository;
import com.doan.webbanhang.repository.EmployeeRepository;
import com.doan.webbanhang.repository.WareHouseRepository;
import com.doan.webbanhang.service.EmployeeService;
import com.doan.webbanhang.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee checkLogin(String username, String password) {
        Employee employee = employeeRepository.checkLogin(username, password);
        Employee tmp = new Employee();
        if (employee != null) {
            tmp.setId(employee.getId());
            tmp.setFullName(employee.getFullName());
        }
        return tmp;
    }
}