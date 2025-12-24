package com.example.demo.Controller;

import com.example.demo.Model.Deal;
import com.example.demo.Service.DealService;
import com.example.demo.Model.ImportResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("/import")
    public ResponseEntity<ImportResult> importDeals(@RequestBody List<Deal> deals) {
        ImportResult result = dealService.importDeals(deals);
        return ResponseEntity.ok(result);
    }
}
