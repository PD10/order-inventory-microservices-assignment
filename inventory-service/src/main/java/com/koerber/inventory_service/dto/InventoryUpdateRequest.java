package com.koerber.inventory_service.dto;

import lombok.Data;

@Data
public class InventoryUpdateRequest {
    private String productId;
    private int quantity;
}
