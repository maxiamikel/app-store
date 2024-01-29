package com.maxi.backstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxi.backstore.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByCustomerId(Long customerId);

    public Order findByOrderNumber(String orderNumber);
}
