package com.maxi.backstore.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxi.backstore.entities.Customer;
import com.maxi.backstore.entities.Order;
import com.maxi.backstore.entities.OrderItem;
import com.maxi.backstore.entities.Product;
import com.maxi.backstore.enums.OrderStatus;
import com.maxi.backstore.services.customer.CustomerServiceImplementation;
import com.maxi.backstore.services.order.OrderServiceImplementation;
import com.maxi.backstore.services.orderItem.OrderItemServiceImplementation;
import com.maxi.backstore.services.product.ProductServiceImplementation;

@RestController
@RequestMapping("/api/v1/stock")
public class HomeController {

    @Autowired
    private ProductServiceImplementation productService;

    @Autowired
    private OrderServiceImplementation orderService;

    @Autowired
    private OrderItemServiceImplementation orderItemService;

    @Autowired
    private CustomerServiceImplementation customerService;

    List<OrderItem> items = new ArrayList<OrderItem>();

    Order order = new Order();

    @GetMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestParam(name = "id") Long id,
            @RequestParam(name = "quantity") Integer quantity) {
        Customer customer = customerService.findByCustomerId(2L);
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        double total = 0;

        Product productDB = productService.findPdoructById(id);
        product = productDB;

        orderItem.setId(null);
        orderItem.setProductId(product.getId());
        orderItem.setPrice(product.getPrice());

        productService.substractStock(product.getId(), quantity);

        orderItem.setQuantity(quantity);
        orderItem.setSubTotal(product.getPrice() * quantity);

        items.add(orderItem);

        total = items.stream().mapToDouble(i -> i.getSubTotal()).sum();
        order.setTotal(total);
        order.setCustomerId(customer.getId());
        order.setCreateAt(LocalDate.now());
        order.setDescription(product.getDescription());
        order.setItems(items);
        order.setStatus(OrderStatus.CREATED);

        return ResponseEntity.ok().body(order);
    }
}
