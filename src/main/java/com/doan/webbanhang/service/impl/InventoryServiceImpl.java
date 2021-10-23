package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.InventoryDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Bill;
import com.doan.webbanhang.repository.BillRepository;
import com.doan.webbanhang.repository.InventoryRepository;
import com.doan.webbanhang.service.BillService;
import com.doan.webbanhang.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Page<InventoryDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        return inventoryRepository.loadPagination(searchTermDTO, pageable);
    }

    @Override
    public Integer getQuantityInventory(SearchTermDTO searchTermDTO) {
        return inventoryRepository.getQuantityInventory(searchTermDTO.getCodeProvince());
    }
}
