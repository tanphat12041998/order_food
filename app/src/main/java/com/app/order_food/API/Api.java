package com.app.order_food.API;

import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoods;
import com.app.order_food.components.Model.PaymentMethods;
import com.app.order_food.components.Model.Ratings;
import com.app.order_food.components.Model.TypeFoods;
import com.app.order_food.components.Model.Users;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @GET("getalluser")
    Call<List<Users>> getAllUsers();

    @FormUrlEncoded
    @POST("adduser")
    Call<Users> addUser(@Field("email") String email, @Field("name") String name, @Field("password") String password
            , @Field("phone") String phone, @Field("address") String address, @Field("img") String img);

    @FormUrlEncoded
    @PUT("updateuser/info/{id}")
    Call<Users> updateUserInfo(@Path("id") int Id, @Field("name") String name, @Field("phone") String phone, @Field("address") String address);

    @FormUrlEncoded
    @PUT("updateuser/info/{id}")
    Call<Users> updateUserPass(@Path("id") int Id, @Field("password") String password);

    @FormUrlEncoded
    @PUT("updateuser/img/{id}")
    Call<Users> updateUserImg(@Field("img") String img);

    @GET("getallfood")
    Call<List<Foods>> getAllFood();

    @POST("getfood/{id}")
    Call<Foods> getFoodById(@Path("id") int Id);

    @POST("getfood/type/{idtype}")
    Call<List<Foods>> getAllFoodByIdType(@Path("idtype") int Id);

    @GET("getallorderfood")
    Call<List<OrderFoods>> getAllOrderFood();

    @POST("getorderfood/order/{id}")
    Call<List<OrderFoods>> getAllOrderFoodById(@Path("id") int Id);

    @POST("getorderfood/namefood/{id}")
    Call<List<OrderFoods>> getAllOrderFoodByIdID(@Path("id") int Id);

    @POST("getorderfood/{iduser}")
    Call<List<OrderFoods>> getOrderFoodById(@Path("iduser") int idUser);

    @FormUrlEncoded
    @POST("addorderfood")
    Call<OrderFoods> addOrderFood(@Field("id") int Id, @Field("iduser") int idUser, @Field("idpayment") int idPayment, @Field("idfood") int idFood
            , @Field("date") String date, @Field("total") int total, @Field("quantity") int quantity, @Field("note") String note
            , @Field("name") String name, @Field("phone") String phone, @Field("address") String address, @Field("namefood") String namefood, @Field("status") Integer status);

    @POST("getrating/{idfood}")
    Call<List<Ratings>> getRatingByIdFood(@Path("idfood") int Id);

    @FormUrlEncoded
    @POST("addrating")
    Call<Ratings> addRating(@Field("iduser") int idUser,@Field("idfood") int idFood
            ,@Field("rate") Float rate,@Field("date") String date);

}
