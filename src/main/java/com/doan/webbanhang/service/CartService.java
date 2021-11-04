package com.doan.webbanhang.service;

import com.doan.webbanhang.dto.BranchCategoryDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.ProductDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Brand;
import com.doan.webbanhang.entity.Cart;
import com.doan.webbanhang.entity.Comment;
import com.doan.webbanhang.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CartService {
    Cart addCart(Cart cart);

    Cart saveCart(Cart cart);

    void deleteCart(String id);

    List<CartDTO> getListCart(String idCli);
}
