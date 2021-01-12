package com.app.order_food.components.Model;

import java.io.Serializable;
import java.util.Date;

public class OrderFoodDetails implements Serializable {
    private Integer id, sl;
    private String mota, ten, img;
    private Integer gia, giatong;

    public OrderFoodDetails(Integer id, Integer sl, String mota, String ten, String img, Integer gia, Integer giatong) {
        this.id = id;
        this.sl = sl;
        this.mota = mota;
        this.ten = ten;
        this.img = img;
        this.gia = gia;
        this.giatong = giatong;
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

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public Integer getGiatong() {
        return giatong;
    }

    public void setGiatong(Integer giatong) {
        this.giatong = giatong;
    }

    public void remove(int i) {
    }
}
