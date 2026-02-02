package com.Stockify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Stockify.Entity.StockTransaction;

public interface StockTransactionRepository
        extends JpaRepository<StockTransaction, Long> {
}
