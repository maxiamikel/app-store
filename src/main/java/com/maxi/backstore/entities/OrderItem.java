package com.maxi.backstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_order_items")
@AllArgsConstructor
@Getter
@Setter
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;
    private Double price;

    private Long productId;

    @Transient
    private Double subTotal;

    public Double getSubTotal() {
        if (this.quantity > 0 && this.price > 0) {
            return this.quantity * this.price;
        } else {
            return (double) 0;
        }
    }

    public OrderItem() {
        this.quantity = (double) 0;
        this.price = (double) 0;
    }

}
