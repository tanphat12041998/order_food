package com.app.order_food.components.Model;

public class TypeFoods {
    private Integer id;
    private String name, img;

    public TypeFoods(){

    }

    public TypeFoods(Integer id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
