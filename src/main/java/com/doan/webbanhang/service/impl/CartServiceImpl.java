package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.BranchCategoryDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.ProductDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.*;
import com.doan.webbanhang.repository.*;
import com.doan.webbanhang.service.CartService;
import com.doan.webbanhang.service.ProductService;
import com.doan.webbanhang.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addCart(Cart cart) {
        Cart tmp = cartRepository.getCartNotOrderFollowClient(cart.getIdCli(), cart.getIdPro());
        if (tmp != null) {
            tmp.setQuantity(tmp.getQuantity() + cart.getQuantity());
            tmp.setAmount(tmp.getAmount() + cart.getAmount());
            cart = cartRepository.save(tmp);
        } else {
            cart = cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public Cart saveCart(Cart cart) {
        cart = cartRepository.save(cart);
        return cart;
    }

    @Override
    public void deleteCart(String id) {
        cartRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public List<CartDTO> getListCart(String idCli) {
        return cartRepository.getListCart(Long.parseLong(idCli));
    }
}
