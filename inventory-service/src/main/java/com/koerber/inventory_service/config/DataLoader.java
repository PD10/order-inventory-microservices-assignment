package com.koerber.inventory_service.config;

import com.koerber.inventory_service.entity.InventoryBatch;
import com.koerber.inventory_service.entity.ProductMaterial;
import com.koerber.inventory_service.repository.InventoryBatchRepository;
import com.koerber.inventory_service.repository.ProductMaterialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(ProductMaterialRepository productMaterialRepository, InventoryBatchRepository inventoryBatchRepository) {
        return args -> {
            productMaterialRepository.save(ProductMaterial.builder().productId("P1").productName("Paracetamol").build());
            productMaterialRepository.save(ProductMaterial.builder().productId("P2").productName("Cetrizine").build());
            productMaterialRepository.save(ProductMaterial.builder().productId("P3").productName("Flagyl").build());
            inventoryBatchRepository.save(InventoryBatch.builder().productId("P1").quantity(10).expiryDate(Instant.now().plusSeconds(5)).build());
            inventoryBatchRepository.save(InventoryBatch.builder().productId("P1").quantity(20).expiryDate(Instant.now().plusSeconds(10)).build());
            inventoryBatchRepository.save(InventoryBatch.builder().productId("P2").quantity(10).expiryDate(Instant.now().plusSeconds(15)).build());
            inventoryBatchRepository.save(InventoryBatch.builder().productId("P2").quantity(20).expiryDate(Instant.now().plusSeconds(20)).build());
            inventoryBatchRepository.save(InventoryBatch.builder().productId("P3").quantity(10).expiryDate(Instant.now().plusSeconds(25)).build());
            inventoryBatchRepository.save(InventoryBatch.builder().productId("P3").quantity(20).expiryDate(Instant.now().plusSeconds(30)).build());
        };
    }
}
