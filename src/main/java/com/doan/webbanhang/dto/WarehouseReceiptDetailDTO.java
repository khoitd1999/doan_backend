package com.doan.webbanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseReceiptDetailDTO {
    private Long id;
    private String namePro;
    private Integer quantity;
    private Double price;
    private Double amount;
}
