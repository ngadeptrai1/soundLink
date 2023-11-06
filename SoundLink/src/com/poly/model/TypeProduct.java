package com.poly.model;

import java.util.Date;

public class TypeProduct {
    private Integer Id;
    private String name, description ;
    private Date createdTime;

    public TypeProduct() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public TypeProduct(Integer Id, String name, String description, Date createdTime) {
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
    }

    

  
}
