package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.Client;
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
    public Employee checkLoginAdmin(String username, String password) {
        Employee employee = employeeRepository.checkLoginAdmin(username, password);
        Employee tmp = new Employee();
        if (employee != null) {
            tmp.setId(employee.getId());
            tmp.setFullName(employee.getFullName());
            tmp.setRole(employee.getRole());
        }
        return tmp;
    }

    @Override
    public Page<Employee> loadDataAll(Pageable pageable, SearchTermDTO searchTermDTO) {
        return this.employeeRepository.loadAllData(searchTermDTO, pageable);
    }

    @Override
    public Employee save(Employee employee) {
        Employee check = employeeRepository.findOneByUsername(employee.getUsername());
        if (employee.getId() != null) {
            if (check != null && check.getId() != null && !check.getId().equals(employee.getId())) {
                return null;
            }
        } else {
            if (check != null && check.getId() != null) {
                return null;
            }
        }
        employee = employeeRepository.save(employee);
        return employee;
    }
}
