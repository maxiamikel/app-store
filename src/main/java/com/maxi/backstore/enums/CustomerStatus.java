package com.maxi.backstore.enums;

public enum CustomerStatus {
    ACTIVATE("Activate"),
    DESACTIVATE("Desactivate");

    private String status;

    private CustomerStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
