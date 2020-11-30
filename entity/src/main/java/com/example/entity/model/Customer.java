package com.example.entity.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Customer {
    final public static int SIN_PENDIENTES=1;
    final public static int FACTURA_PENDIENTE=1;
    final public static int IMPAGADO=1;
    @Id
    private String id;
    private String name;
    private String address_id;
    @Transient
    private List<Address> addressList;
}
