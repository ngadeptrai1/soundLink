/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author dnha1
 */
public class ProductDetails {
    Integer id,quantity,productId;
    String colorName,thumnail;
    Boolean activated;
    Long Price;
  Locale locale = new Locale("vi", "VN");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
    public ProductDetails() {
    }

    public ProductDetails(Integer id, Integer quantity, Integer productId, String colorName, String thumnail, Boolean activated, Long Price) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.colorName = colorName;
        this.thumnail = thumnail;
        this.activated = activated;
        this.Price = Price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Long getPrice() {
        return Price;
    }

    public void setPrice(Long Price) {
        this.Price = Price;
    }
    
    public Object[] toObject(){
    return new Object[]{
    this.colorName,formatter.format(this.Price),this.quantity,this.activated
    };
        
        
    }

    @Override
    public String toString() {
        return "ProductDetails{" + "id=" + id + ", quantity=" + quantity + ", productId=" + productId + ", colorName=" + colorName + ", thumnail=" + thumnail + ", activated=" + activated + ", Price=" + Price + '}';
    }
    
}
