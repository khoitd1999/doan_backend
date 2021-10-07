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
@Table(name = "warehouse")
public class WareHouse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namewar")
    private String nameWar;

    @Column(name = "idwar")
    private String idWar;

    @Column(name = "iddis")
    private String idDis;

    @Column(name = "idpro")
    private String idPro;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "street")
    private String street;
}
