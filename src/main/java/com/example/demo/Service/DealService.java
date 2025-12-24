package com.example.demo.Service;

import com.example.demo.Model.Deal;
import com.example.demo.Model.ImportResult;
import com.example.demo.Repository.DealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealService {
    private static final Logger log = LoggerFactory.getLogger(DealService.class);
    private final DealRepository dealRepository;
    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }
    public ImportResult importDeals(List<Deal> deals) {
        ImportResult result = new ImportResult();
        for (Deal deal : deals) {
            try {
                // This is where the "Processing deal: null" log happens
                log.info("Processing deal: {}", deal.getDealUniqueId());

                // 1. Validate - This will throw the IllegalArgumentException
                saveDeal(deal);

                // 2. Check for Duplicates
                if (dealRepository.findByDealUniqueId(deal.getDealUniqueId()).isPresent()) {
                    result.addError("Duplicate deal: " + deal.getDealUniqueId());
                    continue;
                }

                // 3. Save
                dealRepository.save(deal);
                result.addSuccess();

            } catch (IllegalArgumentException e) {
                // This CATCHES the error from saveDeal and prevents the 500
                log.error("Caught validation error: {}", e.getMessage());
                result.addError("Validation failed: " + e.getMessage());
                // The loop continues to the next deal now
            } catch (Exception e) {
                log.error("Caught unexpected error: {}", e.getMessage());
                result.addError("System error: " + e.getMessage());
            }
        }
        return result;
    }

    public void saveDeal(Deal deal){
        if (deal.getDealUniqueId() == null
                || deal.getFromCurrency() == null
                || deal.getToCurrency() == null
                || deal.getDealTimestamp() == null) {
            log.error("Deal missing required fields: {}", deal);
            throw new IllegalArgumentException("Missing required fields");
        }
    }
}
