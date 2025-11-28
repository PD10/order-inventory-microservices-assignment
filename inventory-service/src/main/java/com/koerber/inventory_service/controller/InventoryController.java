package com.koerber.inventory_service.controller;

import com.koerber.inventory_service.dto.InventoryUpdateRequest;
import com.koerber.inventory_service.entity.InventoryBatch;
import com.koerber.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{productId}")
    public List<InventoryBatch> getSortedByExpiryDateInventoryBatchesFor(@PathVariable String productId) {
        return inventoryService.getSortedByExpiryDateInventoryBatchesFor(productId);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateInventory(@RequestBody InventoryUpdateRequest request) {
        inventoryService.updateInventory(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok("Inventory Updated");
    }
}
