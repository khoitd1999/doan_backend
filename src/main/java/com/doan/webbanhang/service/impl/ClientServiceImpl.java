package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.entity.Client;
import com.doan.webbanhang.entity.Employee;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.repository.ClientRepository;
import com.doan.webbanhang.repository.EmployeeRepository;
import com.doan.webbanhang.service.ClientService;
import com.doan.webbanhang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client checkLogin(String phone, String password) {
        Client client = clientRepository.checkLogin(phone, password);
        Client tmp = new Client();
        if (client != null) {
            tmp.setId(client.getId());
            tmp.setFullName(client.getFullName());
            tmp.setPhone(client.getPhone());
        }
        return tmp;
    }

    @Override
    public Client save(Client client) {
        Client check = clientRepository.findByPhone(client.getPhone());
        if (client.getId() != null) {
            if (check != null && check.getId() != null && !check.getId().equals(client.getId())) {
                return null;
            }
        } else {
            if (check != null && check.getId() != null) {
                return null;
            }
        }
        client = clientRepository.save(client);
        return client;
    }
}
