package com.app.order_food.components.Model;

import java.io.Serializable;
import java.util.Date;

public class OrderFoodDetails implements Serializable {
    private Integer id, sl;
    private String mota, ten, img;
    private Double gia;


    public OrderFoodDetails(Integer id, Integer sl, String mota, String ten, String img, Double gia) {
        this.id = id;
        this.sl = sl;
        this.mota = mota;
        this.ten = ten;
        this.img = img;
        this.gia = gia;
    }

    public OrderFoodDetails(Integer id, Integer sl, String mota, String ten, Double gia) {
        this.id = id;
        this.sl = sl;
        this.mota = mota;
        this.ten = ten;
        this.gia = gia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public void remove(Integer id, String ten, Integer sl, String mota, Double gia, String img) {
        this.id = id;
        this.ten = ten;
        this.sl = sl;
        this.mota = mota;
        this.gia = gia;
        this.img = img;
    }

    public void remove(int i) {
    }
}
