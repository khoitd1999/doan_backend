package com.doan.webbanhang.repository.impl;

import com.doan.webbanhang.dto.ProductDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Product;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.repository.ProductRepositoryCustom;
import com.doan.webbanhang.repository.WareHouseRepositoryCustom;
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

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @Autowired
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Page<Product> loadAllData(SearchTermDTO searchTermDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        List<Product> lst = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        sql.append(" From Product where 1 = 1 ");
        if (searchTermDTO.getNameSearch() != null && !searchTermDTO.getNameSearch().trim().isEmpty()) {
            sql.append(" and namePro like :name");
            params.put("name", "%" + searchTermDTO.getNameSearch() + "%");
        }
        if (searchTermDTO.getIdCat() != null) {
            sql.append(" and idCat = :idCat");
            params.put("idCat", searchTermDTO.getIdCat());
        }
        if (searchTermDTO.getIdBra() != null) {
            sql.append(" and idBra = :idBra");
            params.put("idBra", searchTermDTO.getIdBra());
        }
        Query countQuerry = entityManager.createNativeQuery("SELECT Count(1) " + sql.toString());
        Common.setParams(countQuerry, params);
        Number total = (Number) countQuerry.getSingleResult();
        if (total.longValue() > 0) {
            Query query = entityManager.createNativeQuery("SELECT id, namePro, price, idCat, idBra, description, " +
                    "screen,  os, ram, battery, date, image, status" + sql.toString() + " ORDER BY date desc ", "ProductDTO");
            Common.setParamsWithPageable(query, params, pageable, total);
            List<ProductDTO> lst1 = query.getResultList();
            for (ProductDTO t: lst1) {
                Product product = new Product();
                product.setId(t.getId());
                product.setNamePro(t.getNamePro());
                product.setPrice(t.getPrice());
                product.setIdCat(t.getIdCat());
                product.setIdBra(t.getIdBra());
                product.setDescription(t.getDescription());
                product.setScreen(t.getScreen());
                product.setOs(t.getOs());
                product.setRam(t.getRam());
                product.setBattery(t.getBattery());
                product.setDate(t.getDate());
                product.setImage(t.getImage());
                product.setStatus(t.getStatus());
                lst.add(product);
            }
            for (Product product: lst) {
                if (product.getImage() != null && product.getImage().length > 0) {
                    product.setImage(Common.decompressBytes(product.getImage()));
                }
            }
        }
        return new PageImpl<>(lst, pageable, total.longValue());
    }

    @Override
    public List<Product> loadAllDataForReceipt() {
        StringBuilder sql = new StringBuilder();
        List<Product> lst = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        sql.append(" From Product where 1 = 1 ");
        Query query = entityManager.createNativeQuery("SELECT id, namePro, price, idCat, idBra, description " + sql.toString() + " ORDER BY date desc ", "ProductDTOForReceipt");
        List<ProductDTO> lst1 = query.getResultList();
        for (ProductDTO t : lst1) {
            Product product = new Product();
            product.setId(t.getId());
            product.setNamePro(t.getNamePro());
            product.setPrice(t.getPrice());
            product.setIdBra(t.getIdBra());
            product.setDescription(t.getDescription());
            lst.add(product);
        }
        return lst;
    }
}
