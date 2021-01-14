package com.app.order_food.components.Model;

import java.util.Date;

public class Ratings {
    private Integer idUser, idFood;
    private Float rate;
    private String date;

    public Ratings(Integer idUser, Integer idFood, Float rate, String date) {
        this.idUser = idUser;
        this.idFood = idFood;
        this.rate = rate;
        this.date = date;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdFood() {
        return idFood;
    }

    public void setIdFood(Integer idFood) {
        this.idFood = idFood;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
