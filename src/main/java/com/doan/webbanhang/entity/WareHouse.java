package com.doan.webbanhang.entity;

import com.doan.webbanhang.dto.ProductDTO;
import com.doan.webbanhang.dto.WarehouseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "WarehouseDTO",
                classes = {
                        @ConstructorResult(
                                targetClass = WarehouseDTO.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "address", type = String.class),
                                        @ColumnResult(name = "idProduct", type = Long.class),
                                }
                        )
                }
        ),
})
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
