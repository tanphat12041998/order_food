package com.app.order_food.components.Model;

import java.util.Date;

public class OrderFoods {
    private Integer id, idUser, idPayment, idFood;
    private Date date;
    private Double price;
    private Boolean status;

    public OrderFoods(Integer id, Integer idUser, Integer idPayment, Integer idFood, Date date, Double price, Boolean status) {
        this.id = id;
        this.idUser = idUser;
        this.idPayment = idPayment;
        this.idFood = idFood;
        this.date = date;
        this.price = price;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Integer getIdFood() {
        return idFood;
    }

    public void setIdFood(Integer idFood) {
        this.idFood = idFood;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
