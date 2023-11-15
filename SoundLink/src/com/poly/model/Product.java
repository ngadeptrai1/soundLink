/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.model;

import java.util.Date;

/**
 *
 * @author dnha1
 */
public class Product {
    Integer Id;
    String Name;
    String Description;
    Boolean Activated;
    String Thumnail;
    Integer totalQuantity;
    String frequencyRangeName;
    String brandName;
    String typeProductName;
    String categorieName;
    String designName;
    Date updateTime;
    Integer accountId;
    String totalPowerName;

    public Product() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Boolean getActivated() {
        return Activated;
    }

    public void setActivated(Boolean Activated) {
        this.Activated = Activated;
    }

    public String getThumnail() {
        return Thumnail;
    }

    public void setThumnail(String Thumnail) {
        this.Thumnail = Thumnail;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getFrequencyRangeName() {
        return frequencyRangeName;
    }

    public void setFrequencyRangeName(String frequencyRangeName) {
        this.frequencyRangeName = frequencyRangeName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTypeProductName() {
        return typeProductName;
    }

    public void setTypeProductName(String typeProductName) {
        this.typeProductName = typeProductName;
    }

    public String getCategorieName() {
        return categorieName;
    }

    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getTotalPowerName() {
        return totalPowerName;
    }

    public void setTotalPowerName(String totalPowerName) {
        this.totalPowerName = totalPowerName;
    }

    @Override
    public String toString() {
        return "Product{" + "Id=" + Id + ", Name=" + Name + ", Description=" + Description + ", Activated=" + Activated + ", Thumnail=" + Thumnail + ", totalQuantity=" + totalQuantity + ", frequencyRangeName=" + frequencyRangeName + ", brandName=" + brandName + ", typeProductName=" + typeProductName + ", categorieName=" + categorieName + ", designName=" + designName + ", updateTime=" + updateTime + ", accountId=" + accountId + ", totalPowerName=" + totalPowerName + '}';
    }
    
    public Object[] toData(){
    return new Object[]{
        this.getName(),this.getCategorieName(),this.getBrandName(),this.getFrequencyRangeName(),this.getTotalPowerName(),this.getDesignName(),this.getTypeProductName(),this.getActivated() ? "Đang bán ": "Ngừng bán"
    };
}
}

