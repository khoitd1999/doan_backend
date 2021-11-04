package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.entity.Bill;
import com.doan.webbanhang.entity.Cart;
import com.doan.webbanhang.entity.Employee;
import com.doan.webbanhang.entity.WareHouseReceipt;
import com.doan.webbanhang.repository.BillRepository;
import com.doan.webbanhang.repository.CartRepository;
import com.doan.webbanhang.repository.EmployeeRepository;
import com.doan.webbanhang.service.BillService;
import com.doan.webbanhang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Bill save(Bill bill) {
        bill.setFromDate(LocalDate.now());
        List<Cart> carts = bill.getCarts();
        bill.setCarts(null);
        bill = billRepository.save(bill);
        cartRepository.updateCart(bill.getId(), carts.stream().map(Cart::getId).collect(Collectors.toList()));
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

    @Override
    public Bill findOne(Long id) {
        return billRepository.findById(id).get();
    }
}
