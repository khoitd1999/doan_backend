package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Bill;
import com.doan.webbanhang.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface BillRepository extends JpaRepository<Bill, Long>, BillRepositoryCustom {
    @Modifying
    @Query(value = "update Bill set status = ?1 where id = ?2 ", nativeQuery = true)
    void cancelOrder(Integer status, Long id);

    @Modifying
    @Query(value = "update Bill set status = ?1, toDate = ?2 where id = ?3 ", nativeQuery = true)
    void updateOrder(Integer status, LocalDate toDate, Long id);
}
