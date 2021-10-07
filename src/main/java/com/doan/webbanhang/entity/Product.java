package com.doan.webbanhang.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namepro")
    private String namePro;

    @Column(name = "price")
    private Double price;

    @Column(name = "idcat")
    private Long idCat;

    @Column(name = "idbra")
    private Long idBra;

    @Column(name = "description")
    private String description;

    @Column(name = "screen")
    private String screen;

    @Column(name = "os")
    private String os;

    @Column(name = "ram")
    private String ram;

    @Column(name = "battery")
    private String battery;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "status")
    private boolean status;

}
