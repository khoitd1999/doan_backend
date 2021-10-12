package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Brand;
import com.doan.webbanhang.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query(value = "select * from Brand where idCat = ?1", nativeQuery = true)
    List<Brand> getBrand(Long idCat);
}
