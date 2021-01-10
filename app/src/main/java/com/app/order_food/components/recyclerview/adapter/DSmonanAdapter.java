package com.app.order_food.components.recyclerview.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.Ratings;
import com.app.order_food.views.activities.main.MenuFoodActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DSmonanAdapter extends RecyclerView.Adapter<DSmonanAdapter.RecyclerViewHolder> {
    Context context;
    List<Foods> foodsList;
    List<Ratings> ratingsList = new ArrayList<>();
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    ImageView image_hinhmonan;
    TextView text_food, text_price, text_rating, text_description;
    Button btn_tru, btn_1, btn_cong, btn_tien;
    Integer sl;
    Double slmn;
    public DSmonanAdapter(Context context, List<Foods> foodsList) {
        this.context = context;
        this.foodsList = foodsList;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_dsmonan;
        TextView titleFood, description, price, rating;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_dsmonan = itemView.findViewById(R.id.image_dsmonan);
            titleFood = itemView.findViewById(R.id.titleFood);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        final Foods foods = foodsList.get(pos);

                        final Dialog dialog = new Dialog(itemView.getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_dat_mon);
                        image_hinhmonan = dialog.findViewById(R.id.image_hinhmonan);
                        text_food = dialog.findViewById(R.id.text_food);
                        text_price = dialog.findViewById(R.id.text_price);
                        text_rating = dialog.findViewById(R.id.text_rating);
                        text_description = dialog.findViewById(R.id.text_description);
                        btn_tru = dialog.findViewById(R.id.btn_tru);
                        btn_1 = dialog.findViewById(R.id.btn_1);
                        btn_cong = dialog.findViewById(R.id.btn_cong);
                        btn_tien = dialog.findViewById(R.id.btn_tien);
                        new GetImage(image_hinhmonan).execute(foods.getImg());
                        text_food.setText(foods.getName());
                        text_price.setText(String.valueOf(foods.getPrice())+ " VND");
                        btn_tien.setText(String.valueOf(foods.getPrice())+ " VND");
                        text_description.setText(foods.getDescription());
                        api.getRatingByIdFood(foods.getId()).enqueue(new Callback<List<Ratings>>() {
                            @Override
                            public void onResponse(Call<List<Ratings>> call, Response<List<Ratings>> response) {
                                ratingsList.clear();
                                ratingsList = response.body();
                                float total = 0;
                                float count = 0;
                                for (Ratings ratings : ratingsList) {
                                    double diem = ratings.getRate();
                                    total = (float) (total + diem);
                                    count++;

                                }
                                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                                if (total != 0 || count != 0) {
                                    text_rating.setText(""+ Float.valueOf(decimalFormat.format(total / count)));
                                }
                                else {
                                    text_rating.setText("0.0");
                                }
                            }
                            @Override
                            public void onFailure(Call<List<Ratings>> call, Throwable t) {

                            }
                        });
                        sl = 1;
                        btn_cong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sl = sl + 1;
                                slmn = Double.valueOf(sl * foods.getPrice());
                                btn_1.setText(sl+ "");
                                btn_tien.setText(String.valueOf(slmn)+ " VND");
                                if(sl >= 10){
                                    btn_cong.setVisibility(View.INVISIBLE);
                                    btn_tru.setVisibility(View.VISIBLE);
                                }else if(sl <=1){
                                    btn_tru.setVisibility(View.INVISIBLE);
                                }else if(sl >=1){
                                    btn_cong.setVisibility(View.VISIBLE);
                                    btn_tru.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        btn_tru.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sl = sl - 1;
                                slmn = Double.valueOf(sl * foods.getPrice());
                                btn_tien.setText(String.valueOf(slmn)+ " VND");
                                btn_1.setText(sl+ "");
                                if(sl >= 10){
                                    btn_cong.setVisibility(View.INVISIBLE);
                                    btn_tru.setVisibility(View.VISIBLE);
                                }else if(sl <=1){
                                    btn_tru.setVisibility(View.INVISIBLE);
                                }else if(sl >=1){
                                    btn_cong.setVisibility(View.VISIBLE);
                                    btn_tru.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        btn_tien.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

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
    public DSmonanAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monan_chitiet, parent, false);
        return new DSmonanAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSmonanAdapter.RecyclerViewHolder holder, int position) {
        final Foods foods = this.foodsList.get(position);
        new GetImage(holder.imageView_dsmonan).execute(foods.getImg());
        holder.titleFood.setText("Tên: " + foods.getName());
        holder.description.setText("Mô tả: " + foods.getDescription());
        holder.price.setText("Giá: " + foods.getPrice() + " VND");
        api.getRatingByIdFood(foods.getId()).enqueue(new Callback<List<Ratings>>() {
            @Override
            public void onResponse(Call<List<Ratings>> call, Response<List<Ratings>> response) {
                ratingsList.clear();
                ratingsList = response.body();
                float total = 0;
                float count = 0;
                for (Ratings ratings : ratingsList) {
                    double diem = ratings.getRate();
                    total = (float) (total + diem);
                    count++;

                }
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                if (total != 0 || count != 0) {
                    holder.rating.setText(""+ Float.valueOf(decimalFormat.format(total / count)));
                }
                else {
                    holder.rating.setText("0.0");
                }
            }

            @Override
            public void onFailure(Call<List<Ratings>> call, Throwable t) {

            }
        });


    }


    public class GetImage extends AsyncTask<String, Void, Bitmap> {

        Bitmap bitmap = null;
        ImageView imageView;

        public GetImage(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }


    @Override
    public int getItemCount() {
        return foodsList.size();
    }

}
