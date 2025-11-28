package com.koerber.inventory_service.service;

import com.koerber.inventory_service.entity.InventoryBatch;
import com.koerber.inventory_service.repository.InventoryBatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Captor;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;

public class DefaultInventoryHandlerTest {
    @Mock
    private InventoryBatchRepository inventoryBatchRepository;

    @InjectMocks
    private DefaultInventoryHandler defaultInventoryHandler;

    @Captor
    private ArgumentCaptor<InventoryBatch> inventoryBatchArgumentCaptor;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateInventory() {
        Instant time1 = Instant.now().plusSeconds(5);
        Instant time2 = Instant.now().plusSeconds(10);

        List<InventoryBatch> inventoryBatches = List.of(
                new InventoryBatch(1L, "P1", 10, time1),
                new InventoryBatch(2L, "P1", 20, time2)
        );

        when(inventoryBatchRepository.findByProductIdOrderByExpiryDate("P1")).thenReturn(inventoryBatches);

        defaultInventoryHandler.updateInventory("P1", 15);

        verify(inventoryBatchRepository, times(2)).save(inventoryBatchArgumentCaptor.capture());

        List<InventoryBatch> savedInventoryBatches = inventoryBatchArgumentCaptor.getAllValues();

        List<InventoryBatch> inventoryBatchesResult = List.of(
                new InventoryBatch(1L, "P1", 0, time1),
                new InventoryBatch(2L, "P1", 15, time2)
        );

        assertThat(savedInventoryBatches).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrderElementsOf(inventoryBatchesResult);
    }
}
