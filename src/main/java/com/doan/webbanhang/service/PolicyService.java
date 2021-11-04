package com.doan.webbanhang.service;

import com.doan.webbanhang.dto.InventoryDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Policy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PolicyService {
    List<Policy> loadAllData();
}
