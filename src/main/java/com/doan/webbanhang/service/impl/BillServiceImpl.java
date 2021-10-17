package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.entity.Bill;
import com.doan.webbanhang.entity.Employee;
import com.doan.webbanhang.repository.BillRepository;
import com.doan.webbanhang.repository.EmployeeRepository;
import com.doan.webbanhang.service.BillService;
import com.doan.webbanhang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public void cancelOrder(Bill bill) {
        billRepository.cancelOrder(bill.getStatus(), bill.getId());
    }

    @Override
    public Page<BillDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        return billRepository.loadPagination(searchTermDTO, pageable);
    }

    @Override
    public Page<CartDTO> loadDetailPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        return billRepository.loadDetailPagination(searchTermDTO, pageable);
    }
}
