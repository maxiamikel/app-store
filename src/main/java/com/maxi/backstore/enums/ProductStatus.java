package com.maxi.backstore.enums;

public enum ProductStatus {
    CREATED("Created"),
    DELETED("Deleted"),
    ACTIVE("Active");

    private String status;

    private ProductStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
