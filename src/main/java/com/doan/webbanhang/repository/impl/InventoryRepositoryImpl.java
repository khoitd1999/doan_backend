package com.doan.webbanhang.repository.impl;

import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.InventoryDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Product;
import com.doan.webbanhang.repository.BillRepositoryCustom;
import com.doan.webbanhang.repository.InventoryRepositoryCustom;
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

public class InventoryRepositoryImpl implements InventoryRepositoryCustom {
    @Autowired
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Page<InventoryDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        List<InventoryDTO> inventoryDTOS = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        sql.append(" from inventory i " +
                "    join product p on i.idPro = p.id " +
                "    join warehouse w on i.idWar = w.id where 1 = 1 ");
        if (searchTermDTO.getIdPro() != null) {
            sql.append(" and i.idPro = :idPro ");
            params.put("idPro", searchTermDTO.getIdPro());
        }
        if (searchTermDTO.getIdWar() != null) {
            sql.append(" and i.idWar = :idWar ");
            params.put("idWar", searchTermDTO.getIdWar());
        }
        Query countQuery = entityManager.createNativeQuery("Select Count(1)" + sql.toString());
        Common.setParams(countQuery, params);
        Number total = (Number) countQuery.getSingleResult();
        if (total.longValue() > 0) {
            Query query = entityManager.createNativeQuery("select i.id, i.idPro, i.namePro, p.image, i.idWar, w.nameWar, i.quantity " + sql.toString(), "InventoryDTO");
            Common.setParamsWithPageable(query, params, pageable, total);
            inventoryDTOS = query.getResultList();
            for (InventoryDTO item: inventoryDTOS) {
                if (item.getImage() != null && item.getImage().length > 0) {
                    item.setImage(Common.decompressBytes(item.getImage()));
                }
            }
        }
        return new PageImpl<>(inventoryDTOS, pageable, total.longValue());
    }

}
