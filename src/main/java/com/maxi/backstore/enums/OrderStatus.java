package com.maxi.backstore.enums;

public enum OrderStatus {
    DELIVERED("Delivered"),
    CANCELLED("Cancelled"),
    RECEIVED("Received"),
    CREATED("Created"),
    DELETED("Deleted");

    private String status;

    private OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
