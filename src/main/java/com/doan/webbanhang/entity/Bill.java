package com.doan.webbanhang.entity;

import com.doan.webbanhang.dto.BillDTO;
import com.doan.webbanhang.dto.CartDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDTO;
import com.doan.webbanhang.dto.WarehouseReceiptDetailDTO;
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
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bill")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "BillDTO",
                classes = {
                        @ConstructorResult(
                                targetClass = BillDTO.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "totalAmount", type = Double.class),
                                        @ColumnResult(name = "fromDate", type = LocalDate.class),
                                        @ColumnResult(name = "addressClient", type = String.class),
                                        @ColumnResult(name = "addressWarehouse", type = String.class),
                                        @ColumnResult(name = "typeShip", type = Integer.class),
                                        @ColumnResult(name = "status", type = Integer.class),
                                        @ColumnResult(name = "idWare", type = Long.class),
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = "CartDTO",
                classes = {
                        @ConstructorResult(
                                targetClass = CartDTO.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "idPro", type = Long.class),
                                        @ColumnResult(name = "namePro", type = String.class),
                                        @ColumnResult(name = "image", type = byte[].class),
                                        @ColumnResult(name = "quantity", type = Integer.class),
                                        @ColumnResult(name = "price", type = Double.class),
                                        @ColumnResult(name = "amount", type = Double.class),
                                }
                        )
                }
        ),
})
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "totalamount")
    private Double totalAmount;

    @Column(name = "fee")
    private Double fee;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fromdate")
    private LocalDate fromDate;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "todate")
    private LocalDate toDate;

    @Column(name = "namecli")
    private String nameCli;

    @Column(name = "idcli")
    private Long idCli;

    @Column(name = "addressclient")
    private String addressClient;

    @Column(name = "idwar")
    private Long idWar;

    @Column(name = "addresswarehouse")
    private String addressWarehouse;

    @Column(name = "typeship")
    private Integer typeShip;

    @Column(name = "idpol")
    private Integer idPol;

    @Column(name = "status")
    private Integer status;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "idbil")
    private List<Cart> carts = new ArrayList<>();
}
