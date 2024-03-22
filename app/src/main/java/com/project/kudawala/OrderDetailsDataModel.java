package com.project.kudawala;

public class OrderDetailsDataModel {
    String orderedDate;
    String orderedItemsName;

    public OrderDetailsDataModel(String orderedDate, String orderedItemsName) {
        this.orderedDate = orderedDate;
        this.orderedItemsName = orderedItemsName;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getOrderedItemsName() {
        return orderedItemsName;
    }

    public void setOrderedItemsName(String orderedItemsName) {
        this.orderedItemsName = orderedItemsName;
    }
}
