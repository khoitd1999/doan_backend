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
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehousereceiptdetail")
public class WareHouseReceiptDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idware")
    private Long idWare;

    @Column(name = "idpro")
    private Long idPro;

    @Column(name = "namepro")
    private String namePro;

    @Column(name = "price")
    private Double price;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "quantity")
    private Integer quantity;

    @Transient
    private Integer type;
}
