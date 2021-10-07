package com.doan.webbanhang.service;

import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.Product;
import com.doan.webbanhang.entity.WareHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Object> getBrandAndCategory();

    Product save(Product product, MultipartFile multipartFile) throws IOException;

    Page<Product> loadAllData(SearchTermDTO searchTermDTO, Pageable pageable);
}