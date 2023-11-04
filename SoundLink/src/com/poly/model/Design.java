package com.poly.model;

public class Design {

    private String design_Id, name, description;
    private boolean activated;

    public Design() {
    }

    public Design(String design_Id, String name, String description, boolean activated) {
        this.design_Id = design_Id;
        this.name = name;
        this.description = description;
        this.activated = activated;
    }

    public String getDesign_Id() {
        return design_Id;
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

    public String getActivated() {
        if (activated) {
            return "Hoạt Động";
        } else {
            return "Không Hoạt Động";
        }
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Object[] findAll() {
        return new Object[]{
            this.design_Id,
            this.name,
            this.description,
            this.getActivated()
        };
    }

}
