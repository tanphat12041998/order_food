package com.app.order_food.components.recyclerview.adapter;


import android.app.Dialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoods;
import com.app.order_food.components.Model.Ratings;
import com.app.order_food.views.activities.main.MainActivity;
import com.app.order_food.views.fragments.LichSuFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DSlichsuAdapter extends RecyclerView.Adapter<DSlichsuAdapter.RecyclerViewHolder> {
    ListView recyclerview_lichsuchitiet;
    TextView text_tamtinhgiatien, text_tongconggiatien, text_tennguoidat, text_addressnguoidat, text_phonenguoidat;
    List<OrderFoods> orderFoodsLists = new ArrayList<>();
    List<OrderFoods> orderLists = new ArrayList<>();
    List<OrderFoods> orderFoodssLists = new ArrayList<>();
    List<OrderFoods> orderFoodsList;
    List<Foods> foodsList;
    List<Integer> foodsList1;
    public static List<OrderFoods> ListOrderNameFood;
    Context context;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    Integer a = 0, b = 1;
    Integer rate;
    Integer tongtien;
    LichSuChiTietAdapter lichSuChiTietAdapter;
    Button btn_danhgia, btn_danhgia_confirm;
    RatingBar ratingBar;

    public DSlichsuAdapter(Context context, List<OrderFoods> orderFoodsList) {
        this.context = context;
        this.orderFoodsList = orderFoodsList;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView text_ten_don_hang_lich_su, text_gia_tien_lich_su, text_da_giao, text_thoi_gian;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            text_ten_don_hang_lich_su = itemView.findViewById(R.id.text_ten_don_hang_lich_su);
            text_gia_tien_lich_su = itemView.findViewById(R.id.text_gia_tien_lich_su);
            text_da_giao = itemView.findViewById(R.id.text_da_giao);
            text_thoi_gian = itemView.findViewById(R.id.text_thoi_gian);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        final OrderFoods orderFoods = orderFoodsList.get(pos);
                        final Dialog dialog = new Dialog(itemView.getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.fragment_dat_lai);
                        btn_danhgia = dialog.findViewById(R.id.btn_danhgia);
                        recyclerview_lichsuchitiet = dialog.findViewById(R.id.listview_lichsuchitiet);
                        text_tamtinhgiatien = dialog.findViewById(R.id.text_tamtinhgiatien);
                        text_tongconggiatien = dialog.findViewById(R.id.text_tongconggiatien);
                        text_tennguoidat = dialog.findViewById(R.id.text_tennguoidat);
                        text_addressnguoidat = dialog.findViewById(R.id.text_addressnguoidat);
                        text_phonenguoidat = dialog.findViewById(R.id.text_phonenguoidat);
                        orderFoodssLists = new ArrayList<>();
                        orderLists = new ArrayList<>();
                        foodsList = new ArrayList<>();
                        foodsList1 = new ArrayList<>();
                        DecimalFormat decimalFor= new DecimalFormat("##,###,###");
                        api.getAllOrderFoodById(orderFoods.getId()).enqueue(new Callback<List<OrderFoods>>() {
                            @Override
                            public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
                                orderFoodsLists.clear();
                                orderFoodsLists = response.body();
                                ListOrderNameFood = response.body();
                                tongtien = 0;
                                for (int i = 0; i < orderFoodsLists.size(); i++) {

                                    tongtien += orderFoodsLists.get(i).getTotal();
                                    text_tamtinhgiatien.setText(decimalFor.format(tongtien) + " VNĐ");
                                    text_tongconggiatien.setText(decimalFor.format(tongtien) + " VNĐ");
                                }
                                lichSuChiTietAdapter = new LichSuChiTietAdapter(LichSuFragment.newInstance(), orderFoodsLists);
                                recyclerview_lichsuchitiet.setAdapter(lichSuChiTietAdapter);
                            }

                            @Override
                            public void onFailure(Call<List<OrderFoods>> call, Throwable t) {

                            }
                        });
                        text_tennguoidat.setText(orderFoods.getName());
                        text_phonenguoidat.setText(orderFoods.getPhone());
                        text_addressnguoidat.setText(orderFoods.getAddress());

                        if (orderFoods.getStatus().equals(a)) {
                            btn_danhgia.setVisibility(View.INVISIBLE);
                        } else if (orderFoods.getStatus().equals(b)) {
                            btn_danhgia.setVisibility(View.INVISIBLE);
                        } else {
                            btn_danhgia.setVisibility(View.VISIBLE);
                        }
                        btn_danhgia.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                final OrderFoods orderFood = orderFoodsList.get(pos);
                                final Dialog dialogs = new Dialog(itemView.getContext());
                                dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialogs.setContentView(R.layout.activity_danh_gia);
                                btn_danhgia_confirm = dialogs.findViewById(R.id.btn_danhgia_confirm);
                                ratingBar = dialogs.findViewById(R.id.ratingBar);

                                api.getAllFood().enqueue(new Callback<List<Foods>>() {
                                    @Override
                                    public void onResponse(Call<List<Foods>> call, Response<List<Foods>> response) {
                                        foodsList.clear();
                                        foodsList = response.body();
                                        api.getAllOrderFood().enqueue(new Callback<List<OrderFoods>>() {
                                            @Override
                                            public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
                                                orderFoodssLists.clear();
                                                orderFoodssLists = response.body();
                                                foodsList1.clear();
                                                for (int j = 0; j < 42; j++) {
                                                    for (OrderFoods orderFoods1 : orderFoodssLists) {
                                                        if (orderFood.getId().equals(orderFoods1.getId())) {
                                                            if (foodsList.get(j).getName().equals(orderFoods1.getNamefood())) {
                                                                foodsList1.add(foodsList.get(j).getId());
                                                                Log.d("TAG", foodsList1.toString());
                                                                btn_danhgia_confirm.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View view) {
                                                                        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                                                            @Override
                                                                            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                                                                                float rate = v;
                                                                                Calendar cal = Calendar.getInstance(Locale.getDefault());
                                                                                String date = DateFormat.format("dd/MM/yyyy hh:mm:ss", cal).toString();
                                                                                if(rate <= 0){
                                                                                    rate = 1;
                                                                                }
                                                                                for (int h = 0; h < foodsList1.size(); h++) {
                                                                                    api.addRating(MainActivity.ID, foodsList1.get(h), rate, date).enqueue(new Callback<Ratings>() {
                                                                                        @Override
                                                                                        public void onResponse(Call<Ratings> call, Response<Ratings> response) {
                                                                                            dialogs.dismiss();
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Call<Ratings> call, Throwable t) {

                                                                                        }
                                                                                    });
                                                                                }

                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        }

                                                    }
                                                }


                                            }

                                            @Override
                                            public void onFailure(Call<List<OrderFoods>> call, Throwable t) {
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Call<List<Foods>> call, Throwable t) {
                                    }
                                });


                                dialogs.show();

                            }
                        });


                        dialog.show();
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public DSlichsuAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsu, parent, false);
        return new DSlichsuAdapter.RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DSlichsuAdapter.RecyclerViewHolder holder, int position) {
        final OrderFoods orderFoods = this.orderFoodsList.get(position);
        DecimalFormat decimalFor= new DecimalFormat("##,###,###");
        holder.text_ten_don_hang_lich_su.setText("Mã đơn hàng số: #00" + orderFoods.getId());
        holder.text_gia_tien_lich_su.setText(decimalFor.format(orderFoods.getTotal()) + " VNĐ");
        holder.text_thoi_gian.setText(orderFoods.getDate()+"");
        if (orderFoods.getStatus().equals(a)) {
            holder.text_da_giao.setText("Đang xử lý");
            holder.text_da_giao.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check1, 0, 0, 0);
        } else if (orderFoods.getStatus().equals(b)) {
            holder.text_da_giao.setText("Đang giao hàng");
            holder.text_da_giao.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check1, 0, 0, 0);
        } else {
            holder.text_da_giao.setText("Đã giao hàng");
            holder.text_da_giao.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
        }
    }

    @Override
    public int getItemCount() {
        return orderFoodsList.size();
    }

}
