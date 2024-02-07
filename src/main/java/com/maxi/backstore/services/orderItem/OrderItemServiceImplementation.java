package com.maxi.backstore.services.orderItem;

import org.springframework.stereotype.Service;

import com.maxi.backstore.entities.OrderItem;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

    // @Autowired
    // private OrderItemRepository orderItemRepository;

    // @Autowired
    // private ProductServiceImplementation productService;

    // @Autowired
    // private OrderServiceImplementation orderService;

    // private Double quantity;
    // private Double price;

    // private Long productId;

    // @Transient
    // private Double subTotal;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {

        // Order order = orderService.findOrderById(orderItem.getOrderId());
        // Product product = productService.findPdoructById(orderItem.getProductId());
        // if (order == null) {
        // throw new RuntimeException("The order id not found");
        // }
        // OrderItem newOrderItem = new OrderItem();
        // if (orderItem.getQuantity() <= 0) {
        // throw new RuntimeException("The quantity value cannot be null or empty");
        // }
        // newOrderItem.setSubTotal(orderItem.getQuantity() * product.getPrice());
        // newOrderItem = orderItemRepository.save(newOrderItem);
        return null;
    }

}
