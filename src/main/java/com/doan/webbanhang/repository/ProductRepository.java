package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    @Query(value = "select * from Product where namePro = ?1", nativeQuery = true)
    Product findByName(String name);
}
