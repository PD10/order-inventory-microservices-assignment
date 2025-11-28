package com.koerber.inventory_service.service;

import org.springframework.stereotype.Component;

@Component
public class InventoryHandlerFactory {

    private final DefaultInventoryHandler defaultInventoryHandler;

    public InventoryHandlerFactory(DefaultInventoryHandler defaultInventoryHandler) {
        this.defaultInventoryHandler = defaultInventoryHandler;
    }

    public InventoryHandler getInventoryHandler(String inventoryHandlerType) {
        // Future: return different handler based on inventoryHandlerType using switch case
        return switch (inventoryHandlerType.toUpperCase()) {
            // FEFO is First Expiry First Out
            case "FEFO" -> defaultInventoryHandler;
            default -> throw new IllegalArgumentException("Invalid strategy");
        };
    }
}
