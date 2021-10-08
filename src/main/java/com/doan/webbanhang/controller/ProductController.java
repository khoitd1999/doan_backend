package com.doan.webbanhang.controller;

import com.doan.webbanhang.dto.Result;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.Product;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.service.ProductService;
import com.doan.webbanhang.service.WareHouseService;
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
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-brand-category")
    public ResponseEntity<Result<List<Object>>> getBrandAndCategory() throws JsonProcessingException {
        try {
            Result<List<Object>> rs = new Result<>();
            rs.setBody(productService.getBrandAndCategory());
            return new ResponseEntity<>(rs, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Result<Product>> save(@RequestParam(required = false) MultipartFile imageFile, @RequestParam String productString) throws IOException {
        Result<Product> result = new Result<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(productString, Product.class);
        product = productService.save(product, imageFile);
        if (product == null) {
            result.setMessage("Tên sản phẩm đã tồn tại");
        }
        result.setBody(product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Result<List<Object>>> loadDataAll(Pageable pageable, @RequestParam String searchTerm) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SearchTermDTO searchTermDTO = objectMapper.readValue(searchTerm, SearchTermDTO.class);
            Page<Product> pageTust = productService.loadAllData(searchTermDTO, pageable);
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

    @GetMapping("/load-all")
    public ResponseEntity<Result<List<Product>>> loadDataAll() {
        try {
            List<Product> pageTust = productService.loadAll();
            Result<List<Product>> result = new Result<>();
            result.setBody(pageTust);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
