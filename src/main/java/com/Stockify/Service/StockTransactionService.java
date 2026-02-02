package com.Stockify.Service;


import org.springframework.stereotype.Service;

import com.Stockify.Entity.Item;
import com.Stockify.Entity.StockTransaction;
import com.Stockify.Entity.TransactionType;
import com.Stockify.Exception.InsufficientStockException;
import com.Stockify.Repository.ItemRepository;
import com.Stockify.Repository.StockTransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class StockTransactionService {

    private final ItemRepository itemRepository;
    private final StockTransactionRepository transactionRepository;

    public StockTransactionService(ItemRepository itemRepository,
                                   StockTransactionRepository transactionRepository) {
        this.itemRepository = itemRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public StockTransaction stockIn(Long itemId, Integer quantity, String remark) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setQuantity(item.getQuantity() + quantity);

        StockTransaction tx = new StockTransaction();
        tx.setItem(item);
        tx.setQuantity(quantity);
        tx.setType(TransactionType.IN);
        tx.setRemark(remark);

        return transactionRepository.save(tx);
    }

    @Transactional
    public StockTransaction stockOut(Long itemId, Integer quantity, String remark) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (item.getQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock");
        }

        item.setQuantity(item.getQuantity() - quantity);

        StockTransaction tx = new StockTransaction();
        tx.setItem(item);
        tx.setQuantity(quantity);
        tx.setType(TransactionType.OUT);
        tx.setRemark(remark);

        return transactionRepository.save(tx);
    }
}
