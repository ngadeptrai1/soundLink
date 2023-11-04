package com.poly.model;

public class Color {

    private String  color, status;
    private int color_Id;

    public Color() {
    }

    public Color(int color_Id, String color, String status) {
        this.color_Id = color_Id;
        this.color = color;
        this.status = status;
    }

    public int getColor_Id() {
        return color_Id;
    }

    public void setColor_Id(int color_Id) {
        this.color_Id = color_Id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        if (status) {
            this.status = "Còn Hàng";
        } else {
            this.status = "Hết Hàng";
        }
    }

    public Object[] findAll() {
        return new Object[]{
            this.color_Id,
            this.color_Id,
            this.status
        };
    }

}
