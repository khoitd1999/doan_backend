package com.doan.webbanhang.service;

import com.doan.webbanhang.dto.ProductDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Object> getBrandAndCategory();

    List<Brand> getBrand(Long idCat);

    Product save(Product product, MultipartFile multipartFile) throws IOException;

    Page<Product> loadAllData(SearchTermDTO searchTermDTO, Pageable pageable);

    List<Product> loadAll();

    List<ProductDTO> loadProductDefaultForWelcome(List<Long> listID);

    Double submitComment(Comment comment);

    ProductDTO findById(Long id);

    List<Comment> getAllComment(Long id);
}
