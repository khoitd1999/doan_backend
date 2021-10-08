package com.doan.webbanhang.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String namePro;

    private Double price;

    private Long idCat;

    private Long idBra;

    private String description;

    private String screen;

    private String os;

    private String ram;

    private String battery;

    private LocalDate date;

    private byte[] image;

    private Boolean status;

    public ProductDTO(Long id, String namePro, Double price, Long idCat, Long idBra, String description,
                      String screen, String os, String ram, String battery, LocalDate date, byte[] image, Boolean status) {
        this.id = id;
        this.namePro = namePro;
        this.price = price;
        this.idCat = idCat;
        this.idBra = idBra;
        this.description = description;
        this.screen = screen;
        this.os = os;
        this.ram = ram;
        this.battery = battery;
        this.date = date;
        this.image = image;
        this.status = status;
    }

    public ProductDTO(Long id, String namePro, Double price, Long idCat, Long idBra, String description) {
        this.id = id;
        this.namePro = namePro;
        this.price = price;
        this.idCat = idCat;
        this.idBra = idBra;
        this.description = description;
    }
}
