package com.app.order_food.components.Model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class OrderFoods implements Serializable {
    private Integer id, idUser, idPayment, idFood, quantity;
    private String date;
    private Integer total;
    private Integer status;
    private String name, phone, address;
    private String note;
    private String namefood;

    public OrderFoods(Integer id, Integer idUser, Integer idPayment, Integer idFood, Integer quantity, String date, Integer total, Integer status, String name, String phone, String address, String note, String namefood) {
        this.id = id;
        this.idUser = idUser;
        this.idPayment = idPayment;
        this.idFood = idFood;
        this.quantity = quantity;
        this.date = date;
        this.total = total;
        this.status = status;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.namefood = namefood;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNamefood() {
        return namefood;
    }

    public void setNamefood(String namefood) {
        this.namefood = namefood;
    }
}
