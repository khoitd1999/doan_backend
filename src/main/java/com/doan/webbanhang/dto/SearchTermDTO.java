package com.doan.webbanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTermDTO {
    private String code;
    private String nameSearch;
    private String addressSearch;
    private Integer idCat;
    private Integer idBra;
    private Integer idEmp;
    private Integer idWar;
    private Integer sizeCurrent;
    private Integer priceFilter;
    private List<Long> listID;
    private String codeProvince;
    private String codeDistrict;
    private String codeWard;
}
