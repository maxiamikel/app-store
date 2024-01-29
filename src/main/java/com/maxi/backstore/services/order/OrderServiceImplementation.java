package com.maxi.backstore.services.order;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxi.backstore.entities.Order;
import com.maxi.backstore.entities.OrderItem;
import com.maxi.backstore.entities.Product;
import com.maxi.backstore.enums.OrderStatus;
import com.maxi.backstore.repositories.OrderItemRepository;
import com.maxi.backstore.repositories.OrderRepository;
import com.maxi.backstore.repositories.ProductRepository;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        Order orderDB = orderRepository.findByOrderNumber(order.getOrderNumber());
        if (orderDB != null) {
            return orderDB;
        }
        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOeder(Order order) {
        Order orderDB = this.findOrderById(order.getId());
        if (orderDB == null) {
            return null;
        }
        orderDB.setDescription(order.getDescription());
        orderDB.getItems().clear();
        orderDB.setItems(order.getItems());
        orderDB.setStatus(OrderStatus.RECEIVED);
        orderDB.setCustomerId(order.getCustomerId());
        return orderRepository.save(orderDB);
    }

    @Override
    public Order deleteOrder(Order order) {
        Order orderDB = findOrderById(order.getId());
        if (orderDB == null) {
            return null;
        }
        orderDB.setStatus(OrderStatus.DELETED);
        return orderRepository.save(orderDB);
    }

    @Override
    public Order findOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order id not found"));
        return order;
    }

}
