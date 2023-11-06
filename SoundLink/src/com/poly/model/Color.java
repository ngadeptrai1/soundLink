package com.poly.model;

public class Color {
private Integer Id;
    private String  color;
    private boolean activated;
    

    public Color() {
    }

    public Color(Integer Id, String color, boolean activated) {
        this.Id = Id;
        this.color = color;
        this.activated = activated;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

   
}
