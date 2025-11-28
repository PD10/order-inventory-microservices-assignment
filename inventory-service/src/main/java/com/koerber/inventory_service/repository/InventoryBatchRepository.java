package com.koerber.inventory_service.repository;

import com.koerber.inventory_service.entity.InventoryBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryBatchRepository extends JpaRepository<InventoryBatch, Long> {
    List<InventoryBatch> findByProductIdOrderByExpiryDate(String productId);
}
