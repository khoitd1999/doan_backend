package com.doan.webbanhang.repository;

import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.entity.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WarehouseReceiptRepositoryCustom {
    Page<WarehouseReceiptDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable);

    Page<WarehouseReceiptDTO> loadDetailPagination(SearchTermDTO searchTermDTO, Pageable pageable);
}
