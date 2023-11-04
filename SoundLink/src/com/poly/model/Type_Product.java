package com.poly.model;

public class Type_Product {

    private String type_Product_Id, name, description, update_Day;
    private int quantity;

    public Type_Product() {
    }

    public Type_Product(String type_Product_Id, String name, String description, String update_Day, int quantity) {
        this.type_Product_Id = type_Product_Id;
        this.name = name;
        this.description = description;
        this.update_Day = update_Day;
        this.quantity = quantity;
    }

    public String getType_Product_Id() {
        return type_Product_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdate_Day() {
        return update_Day;
    }

    public void setUpdate_Day(String update_Day) {
        this.update_Day = update_Day;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Object[] findAll() {
        return new Object[]{
            this.type_Product_Id,
            this.name,
            this.description,
            this.update_Day,
            this.quantity
        };
    }

}
