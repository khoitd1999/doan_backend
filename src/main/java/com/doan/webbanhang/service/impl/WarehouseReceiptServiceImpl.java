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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseReceiptServiceImpl implements WareHouseReceiptService {
    private final WareHouseRepository wareHouseRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private BillRepository billRepository;

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
        // lưu hàng tồn kho
        List<WareHouseReceipt> wareHouseReceipts = warehouseReceiptRepository.loadWarehouseReceiptByIdWar(wareHouseReceipt.getIdWar());
        List<WareHouseReceiptDetail> wareHouseReceiptDetails = new ArrayList<>();
        for (int i = 0; i < wareHouseReceipts.size(); i++) {
            for (int j = 0; j < wareHouseReceipts.get(i).getWareHouseReceiptDetails().size(); j++) {
                wareHouseReceipts.get(i).getWareHouseReceiptDetails().get(j).setType(wareHouseReceipts.get(i).getType());
                wareHouseReceiptDetails.add(wareHouseReceipts.get(i).getWareHouseReceiptDetails().get(j));
            }
        }
        Map<Long, Inventory> maps = new HashMap<>();
        for (int i = 0; i < wareHouseReceiptDetails.size(); i++) {
            if (maps.containsKey(wareHouseReceiptDetails.get(i).getIdPro())) {
                Inventory inventory = maps.get(wareHouseReceiptDetails.get(i).getIdPro());
                if (wareHouseReceiptDetails.get(i).getType() == 1) {
                    inventory.setQuantity(inventory.getQuantity() + wareHouseReceiptDetails.get(i).getQuantity());
                } else {
                    inventory.setQuantity(inventory.getQuantity() - wareHouseReceiptDetails.get(i).getQuantity());
                }
                maps.put(inventory.getIdPro(), inventory);
            } else {
                Inventory inventory = new Inventory();
                inventory.setIdWar(wareHouseReceipt.getIdWar());
                inventory.setIdPro(wareHouseReceiptDetails.get(i).getIdPro());
                inventory.setNamePro(wareHouseReceiptDetails.get(i).getNamePro());
                if (wareHouseReceiptDetails.get(i).getType() == 1) {
                    inventory.setQuantity(wareHouseReceiptDetails.get(i).getQuantity());
                } else {
                    inventory.setQuantity(-wareHouseReceiptDetails.get(i).getQuantity());
                }
                maps.put(inventory.getIdPro(), inventory);
            }
        }
        inventoryRepository.removeInventoriesByIdWar(wareHouseReceipt.getIdWar());
        inventoryRepository.saveAll(new ArrayList<>(maps.values()));
        return wareHouseReceipt;
    }

    public WareHouseReceipt createExport(WareHouseReceipt wareHouseReceipt) {
//        WareHouseReceipt check = warehouseReceiptRepository.findByCode(wareHouseReceipt.getCode());
//        if (wareHouseReceipt.getId() != null) {
//            if (check != null && check.getId() != null && !check.getId().equals(wareHouseReceipt.getId())) {
//                return null;
//            }
//        } else {
//            if (check != null && check.getId() != null) {
//                return null;
//            }
//        }
        Bill bill = billRepository.findById(wareHouseReceipt.getIdBil()).get();
        bill.setToDate(wareHouseReceipt.getDate());
        bill.setStatus(1);
        billRepository.updateOrder(bill.getStatus(), bill.getToDate(), bill.getId());
        wareHouseReceipt.getWareHouseReceiptDetails().forEach(n -> {
            n.setId(null);
        });
        wareHouseReceipt = warehouseReceiptRepository.save(wareHouseReceipt);
        // lưu hàng tồn kho
        List<WareHouseReceipt> wareHouseReceipts = warehouseReceiptRepository.loadWarehouseReceiptByIdWar(wareHouseReceipt.getIdWar());
        List<WareHouseReceiptDetail> wareHouseReceiptDetails = new ArrayList<>();
        for (int i = 0; i < wareHouseReceipts.size(); i++) {
            for (int j = 0; j < wareHouseReceipts.get(i).getWareHouseReceiptDetails().size(); j++) {
                wareHouseReceipts.get(i).getWareHouseReceiptDetails().get(j).setType(wareHouseReceipts.get(i).getType());
                wareHouseReceiptDetails.add(wareHouseReceipts.get(i).getWareHouseReceiptDetails().get(j));
            }
        }
        Map<Long, Inventory> maps = new HashMap<>();
        for (int i = 0; i < wareHouseReceiptDetails.size(); i++) {
            if (maps.containsKey(wareHouseReceiptDetails.get(i).getIdPro())) {
                Inventory inventory = maps.get(wareHouseReceiptDetails.get(i).getIdPro());
                if (wareHouseReceiptDetails.get(i).getType() == 1) {
                    inventory.setQuantity(inventory.getQuantity() + wareHouseReceiptDetails.get(i).getQuantity());
                } else {
                    inventory.setQuantity(inventory.getQuantity() - wareHouseReceiptDetails.get(i).getQuantity());
                }
                maps.put(inventory.getIdPro(), inventory);
            } else {
                Inventory inventory = new Inventory();
                inventory.setIdWar(wareHouseReceipt.getIdWar());
                inventory.setIdPro(wareHouseReceiptDetails.get(i).getIdPro());
                inventory.setNamePro(wareHouseReceiptDetails.get(i).getNamePro());
                if (wareHouseReceiptDetails.get(i).getType() == 1) {
                    inventory.setQuantity(wareHouseReceiptDetails.get(i).getQuantity());
                } else {
                    inventory.setQuantity(-wareHouseReceiptDetails.get(i).getQuantity());
                }
                maps.put(inventory.getIdPro(), inventory);
            }
        }
        inventoryRepository.removeInventoriesByIdWar(wareHouseReceipt.getIdWar());
        inventoryRepository.saveAll(new ArrayList<>(maps.values()));
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
