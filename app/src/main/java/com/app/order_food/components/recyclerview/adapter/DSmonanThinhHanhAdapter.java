package com.app.order_food.components.recyclerview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.Ratings;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DSmonanThinhHanhAdapter extends RecyclerView.Adapter<DSmonanThinhHanhAdapter.RecyclerViewHolder> {
    Context context;
    List<Foods> foodsList;
    Ratings ratings;
    List<Ratings> ratingsList = new ArrayList<>();
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_dsmonanthinhhanh;
        TextView nameFood, price, rating;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_dsmonanthinhhanh = itemView.findViewById(R.id.imageView_food);
            nameFood = itemView.findViewById(R.id.textView_name);
            price = itemView.findViewById(R.id.textView_price);
            rating = itemView.findViewById(R.id.textView_like);
            ratingsList = new ArrayList<>();
        }
    }

    public DSmonanThinhHanhAdapter(Context context, List<Foods> foodsList) {
        this.context = context;
        this.foodsList = foodsList;
    }

    @NonNull
    @Override
    public DSmonanThinhHanhAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monan_home, parent, false);
        return new DSmonanThinhHanhAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSmonanThinhHanhAdapter.RecyclerViewHolder holder, int position) {
        final Foods foods = this.foodsList.get(position);
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
                    holder.rating.setText("Star: " + Float.valueOf(decimalFormat.format(total / count)));
                }
                else {
                    holder.rating.setText("Star: 0.0");
                }
            }

            @Override
            public void onFailure(Call<List<Ratings>> call, Throwable t) {

            }
        });


        new GetImage(holder.imageView_dsmonanthinhhanh).execute(foods.getImg());
        holder.nameFood.setText("Tên: " + foods.getName());
        holder.price.setText("Giá: " + foods.getPrice() + " VND");

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
