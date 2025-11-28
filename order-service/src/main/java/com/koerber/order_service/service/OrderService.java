package com.koerber.order_service.service;

import com.koerber.order_service.client.InventoryClient;
import com.koerber.order_service.entity.Order;
import com.koerber.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;

    public Order placeOrder(String productId, int qty) {

        inventoryClient.updateInventory(productId, qty);

        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(qty);
        order.setOrderDate(Instant.now());

        return orderRepository.save(order);
    }
}
