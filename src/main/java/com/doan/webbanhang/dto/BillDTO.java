package com.doan.webbanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class BillDTO {
    private Long id;
    private Double totalAmount;
    private Double fee;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String nameCli;
    private Long idCli;
    private String addressClient;
    private Long idWar;
    private String addressWarehouse;
    private Integer typeShip;
    private Integer idPol;
    private Integer status;

    public BillDTO(Long id, Double totalAmount, LocalDate fromDate, String addressClient, String addressWarehouse, Integer typeShip, Integer status) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.fromDate = fromDate;
        this.addressClient = addressClient;
        this.addressWarehouse = addressWarehouse;
        this.typeShip = typeShip;
        this.status = status;
    }
}
