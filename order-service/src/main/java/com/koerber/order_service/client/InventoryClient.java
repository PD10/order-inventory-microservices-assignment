package com.koerber.order_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class InventoryClient {
    @Autowired
    private RestTemplate restTemplate;

    private static final String INVENTORY_URL = "http://localhost:8081/inventory";

    public List<Map<String, Object>> getBatches(String productId) {
        return restTemplate.getForObject(INVENTORY_URL + "/" + productId, List.class);
    }

    public void updateInventory(String productId, int qty) {
        Map<String, Object> req = Map.of(
                "productId", productId,
                "quantity", qty
        );

        restTemplate.postForEntity(INVENTORY_URL + "/update", req, Void.class);
    }
}
