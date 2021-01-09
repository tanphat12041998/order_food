package com.app.order_food.API;

import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoods;
import com.app.order_food.components.Model.PaymentMethods;
import com.app.order_food.components.Model.Ratings;
import com.app.order_food.components.Model.TypeFoods;
import com.app.order_food.components.Model.Users;

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

    @POST("getuseremail/{email}")
    Call<Users> getUserbyEmail(@Path("email") String email);

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

    @GET("getalltypefood")
    Call<List<TypeFoods>> getAllTypeFood(@Body TypeFoods typeFoods);

    @POST("gettypefood/{id}")
    Call<TypeFoods> getTypeFoodById(@Path("id") int Id);

    @GET("getallfood")
    Call<List<Foods>> getAllFood();

    @POST("getfood/{id}")
    Call<Foods> getAllFoodById(@Path("id") int Id);

    @POST("getfood/type/{idtype}")
    Call<List<Foods>> getAllFoodByIdType(@Path("idtype") int Id);

    @GET("getallpayment")
    Call<List<PaymentMethods>> getAllPayment(@Body PaymentMethods paymentMethods);

    @POST("getpayment/{id}")
    Call<PaymentMethods> getPaymentById(@Path("id") int Id);

    @POST("getorderfood/{id}")
    Call<OrderFoods> getOrderFoodById(@Path("id") int Id);

    @POST("getrating/{idfood}")
    Call<List<Ratings>> getRatingByIdFood(@Path("idfood") int Id);

}
