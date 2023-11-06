package com.poly.model;

import java.util.Date;

public class Brand {

    private Integer Id;
    private String brandName,description;
    private Date dateCreated ;
    private boolean activated;

    public Brand() {
    }

    public Brand(Integer Id, String brandName, String description, Date dateCreated, boolean activated) {
        this.Id = Id;
        this.brandName = brandName;
        this.description = description;
        this.dateCreated = dateCreated;
        this.activated = activated;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "Brand{" + "Id=" + Id + ", brandName=" + brandName + ", description=" + description + ", dateCreated=" + dateCreated + ", activated=" + activated + '}';
    }

    
   
}
