/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.model;

/**
 *
 * @author dnha1
 */
public class Color {

    private Integer id;
    private String color;
    private String status;

    public Color() {
    }

    public Color(int id, String color, String status) {
        this.id = id;
        this.color = color;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Colors{"
                + "id=" + id
                + ", color='" + color + '\''
                + ", status='" + status + '\''
                + '}';
    }
}
