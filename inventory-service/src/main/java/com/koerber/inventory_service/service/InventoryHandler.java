package com.koerber.inventory_service.service;

import com.koerber.inventory_service.entity.InventoryBatch;

import java.util.List;

public interface InventoryHandler {
    void updateInventory(String productId, int quantity);
}
