package com.doan.webbanhang.controller;

import com.doan.webbanhang.dto.InventoryDTO;
import com.doan.webbanhang.dto.Result;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Policy;
import com.doan.webbanhang.service.InventoryService;
import com.doan.webbanhang.service.PolicyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/policy")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @GetMapping("/load-all")
    public ResponseEntity<List<Policy>> loadDataAll() {
        try {
            List<Policy> list = policyService.loadAllData();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
