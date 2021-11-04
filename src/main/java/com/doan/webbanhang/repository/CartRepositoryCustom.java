package com.doan.webbanhang.repository;

import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartRepositoryCustom {
    List<CartDTO> getListCart(Long idCli);
}
