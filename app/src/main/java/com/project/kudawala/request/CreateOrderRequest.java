package com.project.kudawala.request;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreateOrderRequest {
    private ArrayList<String> orderedItems;

    private String orderAddress;

    private String mobileNumber;

    private String email;

    private String userId;

    private String pickupDate;

    public ArrayList<String> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(ArrayList<String> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }
}
