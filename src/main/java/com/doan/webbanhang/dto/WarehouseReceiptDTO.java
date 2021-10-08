package com.doan.webbanhang.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseReceiptDTO {
    private Long id;
    private String code;
    private String nameEmp;
    private String nameWar;
    private LocalDate date;
    private Double totalAmount;
}
