package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.entity.WareHouseReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WarehouseReceiptRepository extends JpaRepository<WareHouseReceipt, Long>, WarehouseReceiptRepositoryCustom {

    @Query(value = "select * from WareHouseReceipt where code = ?1", nativeQuery = true)
    WareHouseReceipt findByCode(String code);
}
