package com.poly.model;

public class Design {
 private Integer Id;
    private String  name, description;
    private boolean activated;
   

    public Design() {
    }

    public Design(Integer Id, String name, String description, boolean activated) {
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.activated = activated;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

  
   

}
