package com.maxi.backstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxi.backstore.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
