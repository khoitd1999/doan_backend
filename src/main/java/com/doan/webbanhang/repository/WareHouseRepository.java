package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WareHouseRepository extends JpaRepository<WareHouse, Long>, WareHouseRepositoryCustom {
    @Query(value = "select * from WareHouse where nameWar = ?1", nativeQuery = true)
    WareHouse findByName(String name);

    @Query(value = "select * from WareHouse where idPro = ?1", nativeQuery = true)
    List<WareHouse> findWarehouseByIdPro(String idPro);

    @Query(value = "select * from WareHouse where idPro = ?1 and idDis = ?2", nativeQuery = true)
    List<WareHouse> findWarehouseByIdProAndIdDis(String idPro, String idDis);
}
