package com.doan.webbanhang.service;

import com.doan.webbanhang.entity.Client;
import com.doan.webbanhang.entity.Employee;

public interface ClientService {
    Client checkLogin(String phone, String password);

    Client save(Client client);
}
