package com.poly.model;

public class Category {

    private String Categories_Id, Name, Describe, Updated_Time;
    private int Updated_Person_Id;

    public Category() {
    }

    public Category(String Categories_Id, String Name, String Describe, String Updated_Time, int Updated_Person_Id) {
        this.Categories_Id = Categories_Id;
        this.Name = Name;
        this.Describe = Describe;
        this.Updated_Time = Updated_Time;
        this.Updated_Person_Id = Updated_Person_Id;
    }

    public String getCategories_Id() {
        return Categories_Id;
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

    public String getUpdated_Time() {
        return Updated_Time;
    }

    public void setUpdated_Time(String Updated_Time) {
        this.Updated_Time = Updated_Time;
    }

    public int getUpdated_Person_Id() {
        return Updated_Person_Id;
    }

    public void setUpdated_Person_Id(int Updated_Person_Id) {
        this.Updated_Person_Id = Updated_Person_Id;
    }

    public Object[] findAll() {
        return new Object[]{
            this.Categories_Id,
            this.Name,
            this.Describe,
            this.Updated_Time,
            this.Updated_Person_Id
        };
    }

}
