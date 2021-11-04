package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.InventoryDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Policy;
import com.doan.webbanhang.repository.InventoryRepository;
import com.doan.webbanhang.repository.PolicyRepository;
import com.doan.webbanhang.service.InventoryService;
import com.doan.webbanhang.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PolicyServiceImpl implements PolicyService {
    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public List<Policy> loadAllData() {
        return policyRepository.findAll();
    }
}
