package com.poly.model;

public class Design {

    private String  name, description;
    private boolean activated;
    private int design_Id;

    public Design() {
    }

    public Design(int design_Id, String name, String description, boolean activated) {
        this.design_Id = design_Id;
        this.name = name;
        this.description = description;
        this.activated = activated;
    }

    public int getDesign_Id() {
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
