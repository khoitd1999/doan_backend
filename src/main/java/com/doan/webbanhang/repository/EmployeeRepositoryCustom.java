package com.doan.webbanhang.repository;

import com.doan.webbanhang.dto.InventoryDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeRepositoryCustom {
    Page<Employee> loadAllData(SearchTermDTO searchTermDTO, Pageable pageable);
}
