package com.doan.webbanhang.service;

import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.entity.WareHouseReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WareHouseReceiptService {
    List<Object> getEmployeeAndWareHouse();

    WareHouseReceipt save(WareHouseReceipt wareHouseReceipt);

    WareHouseReceipt createExport(WareHouseReceipt wareHouseReceipt);

    Page<WarehouseReceiptDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable);

    WareHouseReceipt findOne(Long id);

    Page<WarehouseReceiptDTO> loadDetailPagination(SearchTermDTO searchTermDTO, Pageable pageable);
}
