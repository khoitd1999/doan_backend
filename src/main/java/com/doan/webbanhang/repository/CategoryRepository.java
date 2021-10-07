package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Category;
import com.doan.webbanhang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
