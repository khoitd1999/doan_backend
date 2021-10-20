package com.doan.webbanhang.repository.impl;

import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.repository.WareHouseRepositoryCustom;
import com.doan.webbanhang.repository.WarehouseReceiptRepository;
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

public class WarehouseReceiptRepositoryImpl implements WarehouseReceiptRepositoryCustom {
    @Autowired
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Page<WarehouseReceiptDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        List<WarehouseReceiptDTO> warehouseReceiptDTOS = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        sql.append(" from warehousereceipt w " +
                "    left join employee e on w.idEmp = e.id " +
                "    left join warehouse wh on w.idWar = wh.id where 1 = 1 ");
        if (searchTermDTO.getIdEmp() != null) {
            sql.append(" and w.idEmp = :idEmp");
            params.put("idEmp", searchTermDTO.getIdEmp());
        }
        if (searchTermDTO.getIdWar() != null) {
            sql.append(" and w.idWar = :idWar");
            params.put("idWar", searchTermDTO.getIdWar());
        }
        if (searchTermDTO.getType() != null && searchTermDTO.getType() == 1) {
            sql.append(" and w.type = 1");
        } else if (searchTermDTO.getType() != null && searchTermDTO.getType() == 2) {
            sql.append(" and w.type = 2");
        }
        Query countQuery = entityManager.createNativeQuery("Select Count(1)" + sql.toString());
        Common.setParams(countQuery, params);
        Number total = (Number) countQuery.getSingleResult();

        if (total.longValue() > 0) {
            Query query = entityManager.createNativeQuery("Select w.id, w.date, w.code, e.fullname nameEmp, wh.nameWar nameWar, w.totalAmount " + sql.toString() + " order by w.date desc ", "WarehouseReceiptDTO");
            Common.setParamsWithPageable(query, params, pageable, total);
            warehouseReceiptDTOS = query.getResultList();
        }
        return new PageImpl<>(warehouseReceiptDTOS, pageable, total.longValue());
    }

    @Override
    public Page<WarehouseReceiptDTO> loadDetailPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        List<WarehouseReceiptDTO> warehouseReceiptDTOS = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        sql.append(" from warehousereceiptdetail wd " +
                "    left join product p on wd.idPro = p.id " +
                "    where wd.idWare = :idWare ");
        params.put("idWare", searchTermDTO.getIdWar());
        Query countQuery = entityManager.createNativeQuery("Select Count(1)" + sql.toString());
        Common.setParams(countQuery, params);
        Number total = (Number) countQuery.getSingleResult();

        if (total.longValue() > 0) {
            Query query = entityManager.createNativeQuery("Select wd.id, p.namePro namePro, wd.quantity, wd.price, wd.amount " + sql.toString() , "WarehouseReceiptDetailDTO");
            Common.setParamsWithPageable(query, params, pageable, total);
            warehouseReceiptDTOS = query.getResultList();
        }
        return new PageImpl<>(warehouseReceiptDTOS, pageable, total.longValue());
    }
}
