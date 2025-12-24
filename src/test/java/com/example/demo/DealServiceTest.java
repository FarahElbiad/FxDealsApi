package com.example.demo;

import com.example.demo.Model.Deal;
import com.example.demo.Model.ImportResult;
import com.example.demo.Repository.DealRepository;
import com.example.demo.Service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DealServiceTest {
    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealService dealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testImportDealsSuccess() {
        Deal deal = new Deal();
        deal.setDealUniqueId("D001");
        deal.setFromCurrency("USD");
        deal.setToCurrency("EUR");
        deal.setDealTimestamp(LocalDateTime.now());

        // Simulate no duplicate
        when(dealRepository.findByDealUniqueId("D001")).thenReturn(Optional.empty());

        ImportResult result = dealService.importDeals(Collections.singletonList(deal));

        assertEquals(1, result.getSuccessCount());
        assertEquals(0, result.getErrors().size());

        // Verify save was called
        verify(dealRepository, times(1)).save(deal);
    }

    @Test
    void testImportDealsDuplicate() {
        Deal deal = new Deal();
        deal.setDealUniqueId("D001");
        deal.setFromCurrency("USD");
        deal.setToCurrency("EUR");
        deal.setDealTimestamp(LocalDateTime.now());

        // Simulate duplicate
        when(dealRepository.findByDealUniqueId("D001")).thenReturn(Optional.of(deal));

        ImportResult result = dealService.importDeals(Collections.singletonList(deal));

        assertEquals(0, result.getSuccessCount());
        assertEquals(1, result.getErrors().size());
        assertTrue(result.getErrors().get(0).contains("Duplicate deal"));

        // Save should not be called because duplicate
        verify(dealRepository, never()).save(deal);
    }

    @Test
    void testSaveDealMissingFields() {
        Deal deal = new Deal(); // all fields null

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dealService.saveDeal(deal);
        });

        assertEquals("Missing required fields", exception.getMessage());
    }


}
