package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.*;
import com.doan.webbanhang.entity.*;
import com.doan.webbanhang.repository.AreaRepository;
import com.doan.webbanhang.repository.PolicyRepository;
import com.doan.webbanhang.repository.WareHouseRepository;
import com.doan.webbanhang.repository.WarehouseReceiptRepository;
import com.doan.webbanhang.service.WareHouseService;
import com.doan.webbanhang.utils.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class WareHouseServiceImpl implements WareHouseService {
    private final AreaRepository areaRepository;
    private final WareHouseRepository wareHouseRepository;
    private final WarehouseReceiptRepository warehouseReceiptRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Value(value = "${key}")
    private String key;

    public WareHouseServiceImpl(AreaRepository areaRepository,
                                WareHouseRepository wareHouseRepository,
                                WarehouseReceiptRepository warehouseReceiptRepository) {
        this.areaRepository = areaRepository;
        this.wareHouseRepository = wareHouseRepository;
        this.warehouseReceiptRepository = warehouseReceiptRepository;
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

    @Override
    public List<WarehouseDTO> loadWarehouse(SearchTermDTO searchTermDTO) {
        List<WareHouse> tmp = new ArrayList<>();
        if (searchTermDTO.getCodeDistrict() == null) {
            tmp = this.wareHouseRepository.findWarehouseByIdPro(searchTermDTO.getCodeProvince());
        } else {
            tmp = this.wareHouseRepository.findWarehouseByIdProAndIdDis(searchTermDTO.getCodeProvince(), searchTermDTO.getCodeDistrict());
        }
        List<WareHouseReceipt> wareHouseReceipts = warehouseReceiptRepository.loadWarehouseReceipt(tmp.stream().map(WareHouse::getId).collect(Collectors.toList()), searchTermDTO.getListID());
        Map<Long, WarehouseDTO> rs = new HashMap<>();
        for (int i = 0; i < wareHouseReceipts.size(); i++) {
            if (!rs.containsKey(wareHouseReceipts.get(i).getIdWar())) {
                WarehouseDTO warehouseDTO = new WarehouseDTO();
                warehouseDTO.setId(wareHouseReceipts.get(i).getIdWar());
                WareHouse wareHouse = tmp.stream().filter(n -> n.getId().equals(warehouseDTO.getId())).findFirst().get();
                warehouseDTO.setIdProvince(wareHouse.getIdPro());
                warehouseDTO.setIdDistrict(wareHouse.getIdDis());
                warehouseDTO.setAddress(wareHouse.getAddress());
                warehouseDTO.setAvailable(
                    wareHouseReceipts.get(i).getWareHouseReceiptDetails().stream().
                        map(WareHouseReceiptDetail::getIdPro).filter(n -> searchTermDTO.getListID().contains(n)).collect(Collectors.toSet())
                );
                rs.put(warehouseDTO.getId(), warehouseDTO);
            } else {
                WarehouseDTO warehouseDTO = rs.get(wareHouseReceipts.get(i).getIdWar());
                warehouseDTO.getAvailable().addAll(wareHouseReceipts.get(i).getWareHouseReceiptDetails().stream().
                        map(WareHouseReceiptDetail::getIdPro).filter(n -> searchTermDTO.getListID().contains(n)).collect(Collectors.toSet()));
            }
        }
        for (Long key: rs.keySet()) {
            WarehouseDTO warehouseDTO = rs.get(key);
            warehouseDTO.setUnavailable(searchTermDTO.getListID().stream().
                    filter(n -> !warehouseDTO.getAvailable().contains(n)).collect(Collectors.toList()));
            rs.put(key, warehouseDTO);
        }
        return new ArrayList<>(rs.values());
    }

    @Override
    public WarehouseDTO calculateFee(SearchTermDTO searchTermDTO) throws IOException {
        String addressClient = searchTermDTO.getCodeWard() + " " + searchTermDTO.getCodeDistrict() + " " + searchTermDTO.getCodeProvince();
        List<WarehouseDTO> warehouseInProvinces = this.wareHouseRepository.loadWarehouseHaveProduct(searchTermDTO);
        if (warehouseInProvinces.size() == 0) {
            return new WarehouseDTO();
        }
//        Map<Long, List<WarehouseDTO>> mapsFilter = warehouseInProvinces.stream().collect(Collectors.groupingBy(WarehouseDTO::getId));
//        List<WarehouseDTO> warehouseDTOS = mapsFilter.

        Map<String, List<WarehouseDTO>> mapAddress = new HashMap<>();
        List<String> addressWareHouses = new ArrayList<>();
        for (WarehouseDTO tmp: warehouseInProvinces) {
            if (!mapAddress.containsKey(tmp.getAddress())) {
                List<WarehouseDTO> value = new ArrayList<>();
                value.add(tmp);
                mapAddress.put(tmp.getAddress(), value);
                addressWareHouses.add(tmp.getAddress());
            } else {
                List<WarehouseDTO> value = mapAddress.get(tmp.getAddress());
                value.add(tmp);
                mapAddress.put(tmp.getAddress(), value);
            }
        }
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String origin = URLEncoder.encode(addressClient, "UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append(URLEncoder.encode(addressWareHouses.get(0), "UTF-8"));
        for (int i = 1; i < addressWareHouses.size(); i++) {
            String des = URLEncoder.encode(addressWareHouses.get(i), "UTF-8");
            sb.append("%7C").append(des);
        }
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin +"&destinations=" + sb.toString() + "&key=" + key)
                .method("GET", null)
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        GoogleAPI googleAPI = objectMapper.readValue(responseBody.string(), GoogleAPI.class);
        List<Elements> kcClientToWareHouse = googleAPI.getRows().get(0).getElements();
        int min = Integer.MAX_VALUE;
        int imin = 0;
        String km = "";
        for (int i = 0; i < kcClientToWareHouse.size(); i++) {
            Duration duration = kcClientToWareHouse.get(i).getDuration();
            String[] sequence = duration.getText().split(" ");
            int time = Integer.parseInt(sequence[0]);
            if (time < min) {
                min = time;
                imin = i;
                km = kcClientToWareHouse.get(i).getDistance().getText();
            }
        }
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setDistance(km);
        warehouseDTO.setId(mapAddress.get(addressWareHouses.get(imin)).get(0).getId());
        warehouseDTO.setAddress(mapAddress.get(addressWareHouses.get(imin)).get(0).getAddress());
        warehouseDTO.setAvailable(mapAddress.get(addressWareHouses.get(imin)).stream().map(WarehouseDTO::getIdProduct).collect(Collectors.toSet()));
        warehouseDTO.setUnavailable(searchTermDTO.getListID().stream().
                filter(n -> !warehouseDTO.getAvailable().contains(n)).collect(Collectors.toList()));
        List<Policy> policies = policyRepository.findAll().stream().sorted(Comparator.comparingInt(Policy::getFromDis)).collect(Collectors.toList());
        double dis = Double.parseDouble(km.split(" ")[0]);
        int ipol = 0;
        for (int i = 1; i < policies.size(); i++) {
            if (dis >= policies.get(i).getFromDis()) {
                ipol = i;
            } else {
                break;
            }
        }
        warehouseDTO.setFee(policies.get(ipol).getAmount());
        warehouseDTO.setIdPol(policies.get(ipol).getId());
        return warehouseDTO;
    }
}
