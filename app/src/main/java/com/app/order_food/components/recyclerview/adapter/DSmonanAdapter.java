package com.app.order_food.components.recyclerview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DSmonanAdapter extends RecyclerView.Adapter<DSmonanAdapter.RecyclerViewHolder> {
    Context context;
    List<Foods> foodsList;
    Ratings ratings;
    List<Ratings> ratingsList = new ArrayList<>();
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);

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
