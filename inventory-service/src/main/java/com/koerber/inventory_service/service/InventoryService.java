package com.koerber.inventory_service.service;

import com.koerber.inventory_service.entity.InventoryBatch;
import com.koerber.inventory_service.repository.InventoryBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryBatchRepository inventoryBatchRepository;

    @Autowired
    private InventoryHandlerFactory inventoryHandlerFactory;

    public List<InventoryBatch> getSortedByExpiryDateInventoryBatchesFor(String productId) {
        return inventoryBatchRepository.findByProductIdOrderByExpiryDate(productId);
    }

    public void updateInventory(String productId, int qty) {
        // FEFO is First Expiry First Out
        InventoryHandler inventoryHandler = inventoryHandlerFactory.getInventoryHandler("FEFO");
        inventoryHandler.updateInventory(productId, qty);
    }
}
