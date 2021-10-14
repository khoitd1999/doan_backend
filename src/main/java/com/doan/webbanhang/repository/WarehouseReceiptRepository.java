package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.entity.WareHouseReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseReceiptRepository extends JpaRepository<WareHouseReceipt, Long>, WarehouseReceiptRepositoryCustom {

    @Query(value = "select * from WareHouseReceipt where code = ?1", nativeQuery = true)
    WareHouseReceipt findByCode(String code);

    @Query(value = "select w.* from warehousereceipt w join warehousereceiptdetail wd on w.id = wd.idWare " +
            " where w.idWar in ?1 and wd.idPro in ?2 ", nativeQuery = true)
    List<WareHouseReceipt> loadWarehouseReceipt(List<Long> idWarehouse, List<Long> idPro);

    @Query(value = "select * from warehousereceipt where idWar = ?1 ", nativeQuery = true)
    List<WareHouseReceipt> loadWarehouseReceiptByIdWar(Long idWar);
}
