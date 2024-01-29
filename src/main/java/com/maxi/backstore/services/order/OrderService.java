package com.maxi.backstore.services.order;

import java.util.List;

import com.maxi.backstore.entities.Order;

public interface OrderService {
    public List<Order> findAllOrders();

    public Order createOrder(Order order);

    public Order updateOeder(Order order);

    public Order deleteOrder(Order order);

    public Order findOrderById(Long id);
}
