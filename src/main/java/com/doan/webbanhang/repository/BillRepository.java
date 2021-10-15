package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Bill;
import com.doan.webbanhang.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
