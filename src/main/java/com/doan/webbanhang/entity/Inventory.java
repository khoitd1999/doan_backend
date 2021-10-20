package com.doan.webbanhang.entity;

import com.doan.webbanhang.dto.InventoryDTO;
import com.doan.webbanhang.dto.ProductDTO;
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
@Table(name = "inventory")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "InventoryDTO",
                classes = {
                        @ConstructorResult(
                                targetClass = InventoryDTO.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "idPro", type = Long.class),
                                        @ColumnResult(name = "namePro", type = String.class),
                                        @ColumnResult(name = "image", type = byte[].class),
                                        @ColumnResult(name = "idWar", type = Long.class),
                                        @ColumnResult(name = "nameWar", type = String.class),
                                        @ColumnResult(name = "quantity", type = Integer.class),
                                }
                        )
                }
        ),
})
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
