package com.doan.webbanhang.repository.impl;

import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.repository.BillRepositoryCustom;
import com.doan.webbanhang.repository.CartRepositoryCustom;
import com.doan.webbanhang.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartRepositoryImpl implements CartRepositoryCustom {
    @Autowired
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;


    @Override
    public List<CartDTO> getListCart(Long idCli) {
        StringBuilder sql = new StringBuilder();
        List<CartDTO> cartDTOS = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        sql.append(" from cart c join product p on c.idPro = p.id where idCli = :idCli and idBil is null ");
        params.put("idCli", idCli);
        Query query = entityManager.createNativeQuery("Select c.id, c.idPro, c.namePro, p.image, c.quantity, c.price, c.amount " + sql.toString(), "CartDTO");
        Common.setParams(query, params);
        cartDTOS = query.getResultList();
        for (CartDTO cartDTO: cartDTOS) {
            if (cartDTO.getImage() != null && cartDTO.getImage().length > 0) {
                cartDTO.setImage(Common.decompressBytes(cartDTO.getImage()));
            }
            cartDTO.setIdCli(idCli);
        }
        return cartDTOS;
    }
}
