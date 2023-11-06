package com.poly.model;

public class Brands {

    private int brand_Id;
    private String brand_Name, date_Created, description;
    private boolean activated;

    public Brands() {
    }

    public Brands(int brand_Id, String brand_Name, String date_Created, String description, boolean activated) {
        this.brand_Id = brand_Id;
        this.brand_Name = brand_Name;
        this.date_Created = date_Created;
        this.description = description;
        this.activated = activated;
    }

    public int getBrand_Id() {
        return brand_Id;
    }

    public String getBrand_Name() {
        return brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        this.brand_Name = brand_Name;
    }

    public String getDate_Created() {
        return date_Created;
    }

    public void setDate_Created(String date_Created) {
        this.date_Created = date_Created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Object[] findAll() {
        return new Object[]{
            this.brand_Id,
            this.brand_Name,
            this.date_Created,
            this.description,
            this.activated
        };
    }
    public static void main(String[] args) {
        
    }
}
