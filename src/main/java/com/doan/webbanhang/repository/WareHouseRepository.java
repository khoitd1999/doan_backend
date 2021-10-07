package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WareHouseRepository extends JpaRepository<WareHouse, Long>, WareHouseRepositoryCustom {
    @Query(value = "select * from WareHouse where nameWar = ?1", nativeQuery = true)
    WareHouse findByName(String name);
}
