package com.example.entity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;


@Document("bill")
public class Bill {
    final public static int PAYMENT_1=1;
    final public static int PAYMENT_2=2;
    final public static int PAYMENT_3=3;
    private @Id String id;
    private String comment;
    @Column(precision = 2, length = 5)
    private float amount;
    private int payment;
}
