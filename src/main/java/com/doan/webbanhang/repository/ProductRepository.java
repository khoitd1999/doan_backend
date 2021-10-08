package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    @Query(value = "select * from Product where namePro = ?1", nativeQuery = true)
    Product findByName(String name);

    @Modifying
    @Query(value = "update Product " +
            "set namePro = ?1, " +
            "price = ?2, " +
            "idCat = ?3, " +
            "idBra = ?4, " +
            "description = ?5, " +
            "screen = ?6, " +
            "os = ?7, " +
            "ram = ?8, " +
            "battery = ?9, " +
            "date = ?10, " +
            "status = ?11 where id = ?12", nativeQuery = true)
    void updateOne(String namePro, Double price, Long idCat, Long idBra, String description, String screen, String os, String ram, String battery, LocalDate date, Boolean status, Long id);

}
