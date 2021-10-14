package com.doan.webbanhang.repository;

import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.WareHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WareHouseRepositoryCustom {
    Page<WareHouse> loadAllData(SearchTermDTO searchTermDTO, Pageable pageable);

    List<WarehouseDTO> loadWarehouseHaveProduct(SearchTermDTO searchTermDTO);
}
