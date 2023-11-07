package com.poly.model;

import java.util.Date;

public class Design {
 private Integer Id;
    private String Name;
    private String Description;
    private Date DateCreated;
    private boolean Activated;
    public Design() {
    }

    public Design(Integer Id, String Name, String Description, Date DateCreated, boolean Activated) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.DateCreated = DateCreated;
        this.Activated = Activated;
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

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date DateCreated) {
        this.DateCreated = DateCreated;
    }

    public boolean isActivated() {
        return Activated;
    }

    public void setActivated(boolean Activated) {
        this.Activated = Activated;
    }

  
}
