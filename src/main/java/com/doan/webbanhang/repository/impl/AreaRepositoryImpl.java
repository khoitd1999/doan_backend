package com.doan.webbanhang.repository.impl;

import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.repository.AreaRepositoryCustom;
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

public class AreaRepositoryImpl implements AreaRepositoryCustom {
    @Autowired
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<Area> getAllArea(SearchTermDTO searchTermDTO) {
        StringBuilder sql = new StringBuilder();
        List<Area> lst = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        if (searchTermDTO.getCode() == null) {
            // parentcode của tỉnh thành là 0003
            searchTermDTO.setCode("0003");
        }
        sql.append(" From Area where parentcode = :code ");
        params.put("code", searchTermDTO.getCode());
        if (searchTermDTO.getNameSearch() != null && !searchTermDTO.getNameSearch().isEmpty()) {
            sql.append(" and name like :name");
            params.put("name", "%" + searchTermDTO.getNameSearch() + "%");
        }
        Query countQuerry = entityManager.createNativeQuery("SELECT Count(1) " + sql.toString());
        Common.setParams(countQuerry, params);
        Number total = (Number) countQuerry.getSingleResult();
        if (total.longValue() > 0) {
            Query query = entityManager.createNativeQuery("SELECT * " + sql.toString() + " ORDER BY name ", Area.class);
            Common.setParams(query, params);
            lst = query.getResultList();
        }
        return lst;
    }
}
