package com.doan.webbanhang.controller;

import com.doan.webbanhang.dto.Result;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.entity.Product;
import com.doan.webbanhang.entity.WareHouseReceipt;
import com.doan.webbanhang.service.ProductService;
import com.doan.webbanhang.service.WareHouseReceiptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/warehousereceipt")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WarehouseReceiptController {
    private final ProductService productService;
    private final WareHouseReceiptService wareHouseReceiptService;

    public WarehouseReceiptController(ProductService productService, WareHouseReceiptService wareHouseReceiptService) {
        this.productService = productService;
        this.wareHouseReceiptService = wareHouseReceiptService;
    }

    @GetMapping("/get-employee-warehouse")
    public ResponseEntity<Result<List<Object>>> getEmployeeAndCategory() throws JsonProcessingException {
        try {
            Result<List<Object>> rs = new Result<>();
            rs.setBody(wareHouseReceiptService.getEmployeeAndWareHouse());
            return new ResponseEntity<>(rs, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Result<WareHouseReceipt>> save(@RequestBody WareHouseReceipt wareHouseReceipt) {
        Result<WareHouseReceipt> result = new Result<>();
        wareHouseReceipt = wareHouseReceiptService.save(wareHouseReceipt);
        if (wareHouseReceipt == null) {
            result.setMessage("Tên đơn hàng đã tồn tại");
        }
        result.setBody(wareHouseReceipt);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create-export")
    public ResponseEntity<Result<WareHouseReceipt>> createExport(@RequestBody WareHouseReceipt wareHouseReceipt) {
        Result<WareHouseReceipt> result = new Result<>();
        wareHouseReceipt = wareHouseReceiptService.createExport(wareHouseReceipt);
        if (wareHouseReceipt == null) {
            result.setMessage("Tên đơn hàng đã tồn tại");
        }
        result.setBody(wareHouseReceipt);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Result<List<Object>>> loadDataAll(Pageable pageable, @RequestParam String searchTerm) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SearchTermDTO searchTermDTO = objectMapper.readValue(searchTerm, SearchTermDTO.class);
            Page<WarehouseReceiptDTO> pageTust = wareHouseReceiptService.loadPagination(searchTermDTO, pageable);
            Result<List<Object>> result = new Result<>();
            List<Object> list = new ArrayList<>();
            list.add(pageTust.getContent());
            list.add(pageTust.getTotalElements());
            result.setBody(list);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WareHouseReceipt> find(@PathVariable Long id) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            WareHouseReceipt wareHouseReceipt = wareHouseReceiptService.findOne(id);
            return new ResponseEntity<>(wareHouseReceipt, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination-detail")
    public ResponseEntity<Result<List<Object>>> loadDataAllDetail(Pageable pageable, @RequestParam String searchTerm) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SearchTermDTO searchTermDTO = objectMapper.readValue(searchTerm, SearchTermDTO.class);
            Page<WarehouseReceiptDTO> pageTust = wareHouseReceiptService.loadDetailPagination(searchTermDTO, pageable);
            Result<List<Object>> result = new Result<>();
            List<Object> list = new ArrayList<>();
            list.add(pageTust.getContent());
            list.add(pageTust.getTotalElements());
            result.setBody(list);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
