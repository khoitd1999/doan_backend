package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query(value = "select * from Inventory where idwar = ?1 ", nativeQuery = true)
    List<Inventory> getInventoriesByIdWar(Long idWar);

    @Modifying
    @Query(value = "delete from Inventory where idwar = ?1", nativeQuery = true)
    void removeInventoriesByIdWar(Long idWar);
}
