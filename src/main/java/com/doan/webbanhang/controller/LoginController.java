package com.doan.webbanhang.controller;

import com.doan.webbanhang.dto.Result;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.Client;
import com.doan.webbanhang.entity.Employee;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.service.ClientService;
import com.doan.webbanhang.service.EmployeeService;
import com.doan.webbanhang.service.WareHouseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    private final EmployeeService employeeService;

    @Autowired
    private ClientService clientService;

    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/check-login-admin")
    public ResponseEntity<Result<Employee>> checkLoginAdmin(@RequestParam String username, @RequestParam String password) {
        Result<Employee> result = new Result<>();
        Employee employee = employeeService.checkLoginAdmin(username, password);
        if (employee.getId() == null) {
            result.setMessage("Tài khoản mật khẩu không tồn tại");
        }
        result.setBody(employee);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/check-login")
    public ResponseEntity<Result<Client>> checkLogin(@RequestParam String phone, @RequestParam String password) {
        Result<Client> result = new Result<>();
        Client client = clientService.checkLogin(phone, password);
        if (client.getId() == null) {
            result.setMessage("Tài khoản mật khẩu không tồn tại");
        }
        result.setBody(client);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Result<Client>> checkLogin(@RequestBody Client client) {
        Result<Client> result = new Result<>();
        client = clientService.save(client);
        if (client == null) {
            result.setMessage("Số điện thoại đã được đăng ký");
        }
        result.setBody(client);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
