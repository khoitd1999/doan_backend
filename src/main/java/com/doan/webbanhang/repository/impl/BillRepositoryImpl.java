package com.doan.webbanhang.repository.impl;

import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.entity.Product;
import com.doan.webbanhang.repository.BillRepositoryCustom;
import com.doan.webbanhang.repository.WarehouseReceiptRepositoryCustom;
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

public class BillRepositoryImpl implements BillRepositoryCustom {
    @Autowired
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Page<BillDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        List<BillDTO> billDTOS = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        sql.append(" from bill b left join warehousereceipt w on b.id = w.idBil where 1 = 1 ");
        if (searchTermDTO.getIdCli() != null) {
            sql.append(" and b.idCli = :idCli ");
            params.put("idCli", searchTermDTO.getIdCli());
        }
        if (searchTermDTO.getFromDateSearch() != null) {
            sql.append(" and b.fromDate = :fromDate ");
            params.put("fromDate", searchTermDTO.getFromDateSearch());
        }
        if (searchTermDTO.getTypeShipSearch() != null) {
            sql.append(" and b.typeShip = :typeShip ");
            params.put("typeShip", searchTermDTO.getTypeShipSearch());
        }
        if (searchTermDTO.getStatusSearch() != null) {
            sql.append(" and b.status = :status ");
            params.put("status", searchTermDTO.getStatusSearch());
        }
        Query countQuery = entityManager.createNativeQuery("Select Count(1)" + sql.toString());
        Common.setParams(countQuery, params);
        Number total = (Number) countQuery.getSingleResult();
        if (total.longValue() > 0) {
            Query query = entityManager.createNativeQuery("Select b.id, b.totalAmount, b.fromDate, b.addressClient, b.addressWarehouse, b.typeShip, b.status, w.id idWare " + sql.toString() + " order by fromDate desc ", "BillDTO");
            Common.setParamsWithPageable(query, params, pageable, total);
            billDTOS = query.getResultList();
        }
        return new PageImpl<>(billDTOS, pageable, total.longValue());
    }

    @Override
    public Page<CartDTO> loadDetailPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        List<CartDTO> cartDTOS = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        sql.append(" from cart c join product p on c.idPro = p.id where idBil = :idBil");
        params.put("idBil", searchTermDTO.getIdBil());
        Query countQuery = entityManager.createNativeQuery("Select Count(1) " + sql.toString());
        Common.setParams(countQuery, params);
        Number total = (Number) countQuery.getSingleResult();

        if (total.longValue() > 0) {
            Query query = entityManager.createNativeQuery("Select c.id, c.idPro, c.namePro, p.image, c.quantity, c.price, c.amount " + sql.toString() , "CartDTO");
            Common.setParamsWithPageable(query, params, pageable, total);
            cartDTOS = query.getResultList();
            for (CartDTO cartDTO: cartDTOS) {
                if (cartDTO.getImage() != null && cartDTO.getImage().length > 0) {
                    cartDTO.setImage(Common.decompressBytes(cartDTO.getImage()));
                }
            }
        }
        return new PageImpl<>(cartDTOS, pageable, total.longValue());
    }
}
