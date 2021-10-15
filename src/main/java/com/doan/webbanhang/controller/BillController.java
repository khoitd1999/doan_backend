package com.doan.webbanhang.controller;

import com.doan.webbanhang.dto.Result;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.dto.WarehouseDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.Bill;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.service.BillService;
import com.doan.webbanhang.service.WareHouseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bill")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/save")
    public ResponseEntity<Result<Bill>> save(@RequestBody Bill bill) {
        Result<Bill> result = new Result<>();
        result.setBody(billService.save(bill));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
