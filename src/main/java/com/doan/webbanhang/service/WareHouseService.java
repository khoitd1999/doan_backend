package com.doan.webbanhang.service;

import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.WareHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface WareHouseService {
    List<Area> getAllArea(SearchTermDTO searchTermDTO);

    WareHouse save(WareHouse wareHouse);

    Page<WareHouse> loadDataAll(Pageable pageable, SearchTermDTO searchTermDTO);

    List<WarehouseDTO> loadWarehouse(SearchTermDTO searchTermDTO);

    WarehouseDTO calculateFee(SearchTermDTO searchTermDTO) throws IOException;
}
