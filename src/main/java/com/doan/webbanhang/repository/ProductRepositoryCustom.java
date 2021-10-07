package com.doan.webbanhang.repository;

import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepositoryCustom {
    Page<Product> loadAllData(SearchTermDTO searchTermDTO, Pageable pageable);
}
