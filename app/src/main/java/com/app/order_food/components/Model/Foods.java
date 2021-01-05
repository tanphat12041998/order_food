package com.app.order_food.components.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Foods {

    private Integer id;
    @SerializedName("idtype")
    private Integer idTypeFood;

    private String name,description, img;
    private Double price;

    public Foods(Integer id, Integer idTypeFood, String name, String description, String img, Double price) {
        this.id = id;
        this.idTypeFood = idTypeFood;
        this.name = name;
        this.description = description;
        this.img = img;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTypeFood() {
        return idTypeFood;
    }

    public void setIdTypeFood(Integer idTypeFood) {
        this.idTypeFood = idTypeFood;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
