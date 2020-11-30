package com.example.entity.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    private String id;
    private String street;
    private String number;
    private String town;
    private String state;
    private String customer_id;
}
