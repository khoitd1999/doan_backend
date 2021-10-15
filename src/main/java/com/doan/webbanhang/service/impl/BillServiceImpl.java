package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.entity.Bill;
import com.doan.webbanhang.entity.Employee;
import com.doan.webbanhang.repository.BillRepository;
import com.doan.webbanhang.repository.EmployeeRepository;
import com.doan.webbanhang.service.BillService;
import com.doan.webbanhang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill save(Bill bill) {
        bill.setFromDate(LocalDate.now());
        bill = billRepository.save(bill);
        return bill;
    }
}
