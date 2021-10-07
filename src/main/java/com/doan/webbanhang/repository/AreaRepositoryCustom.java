package com.doan.webbanhang.repository;

import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaRepositoryCustom {
    List<Area> getAllArea(SearchTermDTO searchTermDTO);
}
