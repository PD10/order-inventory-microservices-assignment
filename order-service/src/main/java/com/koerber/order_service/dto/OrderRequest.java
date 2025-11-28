package com.koerber.order_service.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String productId;
    private int quantity;
}
