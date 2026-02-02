package com.Stockify.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Stockify.Entity.StockTransaction;
import com.Stockify.Service.StockTransactionService;

@RestController
@RequestMapping("/transactions")
public class StockTransactionController {

    private final StockTransactionService service;

    public StockTransactionController(StockTransactionService service) {
        this.service = service;
    }

    @PostMapping("/in")
    public ResponseEntity<StockTransaction> stockIn(
            @RequestParam Long itemId,
            @RequestParam Integer quantity,
            @RequestParam(required = false) String remark) {

        return ResponseEntity.ok(service.stockIn(itemId, quantity, remark));
    }

    @PostMapping("/out")
    public ResponseEntity<StockTransaction> stockOut(
            @RequestParam Long itemId,
            @RequestParam Integer quantity,
            @RequestParam(required = false) String remark) {

        return ResponseEntity.ok(service.stockOut(itemId, quantity, remark));
    }
}
