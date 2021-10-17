package com.doan.webbanhang.repository;

import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillRepositoryCustom {
    Page<BillDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable);

    Page<CartDTO> loadDetailPagination(SearchTermDTO searchTermDTO, Pageable pageable);
}
