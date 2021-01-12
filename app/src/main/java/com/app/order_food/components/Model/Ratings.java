package com.app.order_food.components.Model;

import java.util.Date;

public class Ratings {
    private Integer idUser, idFood, rate;
    private String date;

    public Ratings(Integer idUser, Integer idFood, Integer rate, String date) {
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
