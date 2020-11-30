package model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity @Data
public class Customer {
    final public static int SIN_PENDIENTES=1;
    final public static int FACTURA_PENDIENTE=1;
    final public static int IMPAGADO=1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int status;
    @Transient
    private List<Address> addressList;
}
