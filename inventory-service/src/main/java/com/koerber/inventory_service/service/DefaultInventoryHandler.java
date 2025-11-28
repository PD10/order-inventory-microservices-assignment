package com.koerber.inventory_service.service;

import com.koerber.inventory_service.entity.InventoryBatch;
import com.koerber.inventory_service.repository.InventoryBatchRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultInventoryHandler implements InventoryHandler{
    @Autowired
    private InventoryBatchRepository inventoryBatchRepository;

    @Override
    @Transactional
    public void updateInventory(String productId, int quantity) {
        List<InventoryBatch> batches = inventoryBatchRepository.findByProductIdOrderByExpiryDate(productId);

        int remaining = quantity;

        for (InventoryBatch batch : batches) {
            if (remaining <= 0) break;

            int used = Math.min(batch.getQuantity(), remaining);
            batch.setQuantity(batch.getQuantity() - used);
            remaining -= used;

            inventoryBatchRepository.save(batch);
        }
    }
}
