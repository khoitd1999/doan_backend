package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AreaRepository extends JpaRepository<Area, Long>, AreaRepositoryCustom {

//    @Query(value = "select * from Area from parentcode = ?1 and name like ?2", nativeQuery = true)
//    Page<Area> getAllArea(String code, Pageable pageable);
}
