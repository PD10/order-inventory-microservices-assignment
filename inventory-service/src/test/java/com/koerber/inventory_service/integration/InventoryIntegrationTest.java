package com.koerber.inventory_service.integration;

import com.koerber.inventory_service.entity.InventoryBatch;
import com.koerber.inventory_service.repository.InventoryBatchRepository;
import com.koerber.inventory_service.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
public class InventoryIntegrationTest {

    @Autowired
    private InventoryBatchRepository inventoryBatchRepository;

    @Autowired
    private InventoryService inventoryService;

    @Test
    void testInventoryUpdatesInH2Database() {
        InventoryBatch inventoryBatch1 = InventoryBatch.builder().id(1L).productId("P1").quantity(10).expiryDate(Instant.now().plusSeconds(5)).build();
        InventoryBatch inventoryBatch2 = InventoryBatch.builder().id(2L).productId("P1").quantity(20).expiryDate(Instant.now().plusSeconds(10)).build();

        inventoryBatchRepository.save(inventoryBatch1);
        inventoryBatchRepository.save(inventoryBatch2);

        inventoryService.updateInventory("P1", 15);

        InventoryBatch updatedInventoryBatch1 = inventoryBatchRepository.findByProductIdOrderByExpiryDate("P1").get(0);
        InventoryBatch updatedInventoryBatch2 = inventoryBatchRepository.findByProductIdOrderByExpiryDate("P1").get(1);

        assertEquals(0, updatedInventoryBatch1.getQuantity());
        assertEquals(15, updatedInventoryBatch2.getQuantity());
    }
}

