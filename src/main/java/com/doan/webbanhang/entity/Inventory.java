package com.doan.webbanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idpro")
    private Long idPro;

    @Column(name = "namepro")
    private String namePro;

    @Column(name = "idwar")
    private Long idWar;

    @Column(name = "quantity")
    private Integer quantity;
}
