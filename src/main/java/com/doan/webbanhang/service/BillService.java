package com.doan.webbanhang.service;

import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.entity.Bill;
import com.doan.webbanhang.entity.Employee;
import com.doan.webbanhang.entity.WareHouseReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillService {
    Bill save(Bill bill);

    void cancelOrder(Bill bill);

    Page<BillDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable);

    Page<CartDTO> loadDetailPagination(SearchTermDTO searchTermDTO, Pageable pageable);

    Bill findOne(Long id);
}
