package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PolicyRepository extends JpaRepository<Policy, Long> {


}
