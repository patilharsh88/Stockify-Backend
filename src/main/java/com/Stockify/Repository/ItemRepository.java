package com.Stockify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Stockify.Entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
