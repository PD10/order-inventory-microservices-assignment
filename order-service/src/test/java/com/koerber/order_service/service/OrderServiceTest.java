package com.koerber.order_service.service;

import com.koerber.order_service.client.InventoryClient;
import com.koerber.order_service.entity.Order;
import com.koerber.order_service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private InventoryClient inventoryClient;

    @InjectMocks
    private OrderService orderService;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceOrder() {
        Order savedOrder = new Order();
        savedOrder.setProductId("P123");
        savedOrder.setQuantity(5);
        savedOrder.setOrderDate(Instant.now());

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        Order result = orderService.placeOrder("P123", 5);

        verify(inventoryClient, times(1)).updateInventory("P123", 5);

        verify(orderRepository).save(orderArgumentCaptor.capture());

        Order captured = orderArgumentCaptor.getValue();

        assertThat(result).usingRecursiveComparison().ignoringFields("orderDate").isEqualTo(captured);
    }
}

