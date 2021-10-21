package com.doan.webbanhang.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private Long idPro;
    private String namePro;
    private byte[] image;
    private Long idBil;
    private Integer quantity;
    private Double price;
    private Double amount;

    public CartDTO(Long id, Long idPro, String namePro, byte[] image, Integer quantity, Double price, Double amount) {
        this.id = id;
        this.idPro = idPro;
        this.namePro = namePro;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }
}