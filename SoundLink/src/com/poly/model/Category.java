package com.poly.model;

import java.util.Date;

public class Category {
  private Integer Id;
    private String Name, Describe;
  
private Date CreatedTime;
    public Category() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String Describe) {
        this.Describe = Describe;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Date getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(Date CreatedTime) {
        this.CreatedTime = CreatedTime;
    }

    public Category(Integer Id, String Name, String Describe, Date CreatedTime) {
        this.Id = Id;
        this.Name = Name;
        this.Describe = Describe;
        this.CreatedTime = CreatedTime;
    }

   

   
   
}
