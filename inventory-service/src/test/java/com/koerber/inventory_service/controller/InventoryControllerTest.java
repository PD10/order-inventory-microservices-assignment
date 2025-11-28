package com.koerber.inventory_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koerber.inventory_service.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testUpdateInventoryEndpoint() throws Exception {
        String body = """
            { "productId": "P1", "quantity": 10 }
        """;

        mockMvc.perform(post("/inventory/update")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(inventoryService).updateInventory("P1", 10);
    }
}

