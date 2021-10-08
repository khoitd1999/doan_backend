package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.entity.*;
import com.doan.webbanhang.repository.*;
import com.doan.webbanhang.service.ProductService;
import com.doan.webbanhang.service.WareHouseReceiptService;
import com.doan.webbanhang.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WarehouseReceiptServiceImpl implements WareHouseReceiptService {
    private final WareHouseRepository wareHouseRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;

    public WarehouseReceiptServiceImpl(WareHouseRepository wareHouseRepository,
                                       EmployeeRepository employeeRepository) {
        this.wareHouseRepository = wareHouseRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Object> getEmployeeAndWareHouse() {
        List<Object> resObjects = new ArrayList<>();
        List<WareHouse> wareHouses = wareHouseRepository.findAll();
        List<Employee> employees = employeeRepository.findAllByRole("EMPLOYEE");
        resObjects.add(wareHouses);
        resObjects.add(employees);
        return resObjects;
    }

    public WareHouseReceipt save(WareHouseReceipt wareHouseReceipt) {
        WareHouseReceipt check = warehouseReceiptRepository.findByCode(wareHouseReceipt.getCode());
        if (wareHouseReceipt.getId() != null) {
            if (check != null && check.getId() != null && !check.getId().equals(wareHouseReceipt.getId())) {
                return null;
            }
        } else {
            if (check != null && check.getId() != null) {
                return null;
            }
        }
        wareHouseReceipt.getWareHouseReceiptDetails().forEach(n -> {
            n.setId(null);
        });
        wareHouseReceipt = warehouseReceiptRepository.save(wareHouseReceipt);
        return wareHouseReceipt;
    }

    @Override
    public Page<WarehouseReceiptDTO> loadPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        return warehouseReceiptRepository.loadPagination(searchTermDTO, pageable);
    }

    @Override
    public WareHouseReceipt findOne(Long id) {
        return warehouseReceiptRepository.findById(id).get();
    }

    @Override
    public Page<WarehouseReceiptDTO> loadDetailPagination(SearchTermDTO searchTermDTO, Pageable pageable) {
        return warehouseReceiptRepository.loadDetailPagination(searchTermDTO, pageable);
    }
}
