package com.app.order_food.components.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Foods {

    private Integer id;
    @SerializedName("idtype")
    private Integer idTypeFood;
    private Integer price;

    private String name,description, img;


    public Foods(Integer id, Integer idTypeFood, Integer price, String name, String description, String img) {
        this.id = id;
        this.idTypeFood = idTypeFood;
        this.price = price;
        this.name = name;
        this.description = description;
        this.img = img;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
}
