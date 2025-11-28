package com.koerber.order_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String productId;
    private int quantity;
    private Instant orderDate;
}
