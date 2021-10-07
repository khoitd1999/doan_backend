package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.WareHouse;
import com.doan.webbanhang.repository.AreaRepository;
import com.doan.webbanhang.repository.WareHouseRepository;
import com.doan.webbanhang.service.WareHouseService;
import com.doan.webbanhang.utils.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareHouseServiceImpl implements WareHouseService {
    private final AreaRepository areaRepository;
    private final WareHouseRepository wareHouseRepository;

    public WareHouseServiceImpl(AreaRepository areaRepository,
                                WareHouseRepository wareHouseRepository) {
        this.areaRepository = areaRepository;
        this.wareHouseRepository = wareHouseRepository;
    }

    @Override
    public List<Area> getAllArea(SearchTermDTO searchTermDTO) {
        return this.areaRepository.getAllArea(searchTermDTO);
    }

    @Override
    public WareHouse save(WareHouse wareHouse) {
        WareHouse check = wareHouseRepository.findByName(wareHouse.getNameWar());
        if (wareHouse.getId() != null) {
            if (check != null && check.getId() != null && !check.getId().equals(wareHouse.getId())) {
                return null;
            }
        } else {
            if (check != null && check.getId() != null) {
                return null;
            }
        }
        wareHouse = wareHouseRepository.save(wareHouse);
        return wareHouse;
    }

    @Override
    public Page<WareHouse> loadDataAll(Pageable pageable, SearchTermDTO searchTermDTO) {
        return this.wareHouseRepository.loadAllData(searchTermDTO, pageable);
    }
}
