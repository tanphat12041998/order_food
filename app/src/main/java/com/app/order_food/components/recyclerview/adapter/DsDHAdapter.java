package com.app.order_food.components.recyclerview.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoodDetails;
import com.app.order_food.components.Model.Ratings;
import com.app.order_food.views.activities.main.MainActivity;
import com.app.order_food.views.fragments.CartFragment;

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

public class DsDHAdapter extends BaseAdapter {
    CartFragment context;
    ArrayList<OrderFoodDetails> foodDetailsArrayList;
    ImageView image_hinhmonan;
    TextView text_food, text_price, text_rating, text_description;
    Button btn_tru, btn_1, btn_cong, btn_tien;
    List<Ratings> ratingsList = new ArrayList<>();
    Integer sl, slht;
    Double slmn;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    public DsDHAdapter(CartFragment context, ArrayList<OrderFoodDetails> foodDetailsArrayList) {
        this.context = context;
        this.foodDetailsArrayList = foodDetailsArrayList;
    }

    @Override
    public int getCount() {
        return foodDetailsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodDetailsArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public class ViewHolder {
        public TextView text_so_luong, text_ten_mon_an, text_mo_ta, text_gia_tien;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context.getContext()).inflate(R.layout.item_thanh_toan, viewGroup, false);

            viewHolder.text_ten_mon_an = (TextView) view.findViewById(R.id.text_ten_mon_an);
            viewHolder.text_so_luong = (TextView) view.findViewById(R.id.text_sol_uong);
            viewHolder.text_mo_ta = (TextView) view.findViewById(R.id.text_mo_ta);
            viewHolder.text_gia_tien = (TextView) view.findViewById(R.id.text_gia_tien);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        OrderFoodDetails orderFoodDetails = (OrderFoodDetails) getItem(i);
        viewHolder.text_ten_mon_an.setText(orderFoodDetails.getTen());
        viewHolder.text_so_luong.setText(orderFoodDetails.getSl()+"x");
        viewHolder.text_mo_ta.setText(orderFoodDetails.getMota());
        viewHolder.text_gia_tien.setText(orderFoodDetails.getGia()+" VNĐ");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final OrderFoodDetails orderFoodDetails1 = foodDetailsArrayList.get(i);
                final Dialog dialog = new Dialog(view.getContext());
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
                new DsDHAdapter.GetImage(image_hinhmonan).execute(orderFoodDetails1.getImg());
                text_food.setText(orderFoodDetails1.getTen());
                text_price.setText(String.valueOf(orderFoodDetails1.getGia()) + " VND");
                btn_tien.setText(String.valueOf(orderFoodDetails1.getGia()) + " VND");
                text_description.setText(orderFoodDetails1.getMota());
                api.getRatingByIdFood(orderFoodDetails1.getId()).enqueue(new Callback<List<Ratings>>() {
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
                            text_rating.setText("" + Float.valueOf(decimalFormat.format(total / count)));
                        } else {
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
                        slmn = Double.valueOf(sl * orderFoodDetails1.getGia());
                        btn_1.setText(sl + "");
                        btn_tien.setText(String.valueOf(slmn) + " VND");
                        if (sl >= 10) {
                            btn_cong.setVisibility(View.INVISIBLE);
                            btn_tru.setVisibility(View.VISIBLE);
                        }else if (sl <= 1) {
                            btn_cong.setVisibility(View.VISIBLE);
                            btn_tru.setVisibility(View.INVISIBLE);
                        }else if (sl >= 1) {
                            btn_cong.setVisibility(View.VISIBLE);
                            btn_tru.setVisibility(View.VISIBLE);
                        }
                    }
                });
                btn_tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sl = sl - 1;
                        slmn = Double.valueOf(sl * orderFoodDetails1.getGia());
                        btn_tien.setText(String.valueOf(slmn) + " VND");
                        btn_1.setText(sl + "");
                        if (sl >= 10) {
                            btn_cong.setVisibility(View.INVISIBLE);
                            btn_tru.setVisibility(View.VISIBLE);
                        }else if (sl <= 1) {
                            btn_cong.setVisibility(View.VISIBLE);
                            btn_tru.setVisibility(View.INVISIBLE);
                        } else if (sl >= 1) {
                            btn_cong.setVisibility(View.VISIBLE);
                            btn_tru.setVisibility(View.VISIBLE);
                        }
                    }
                });
                btn_tien.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (MainActivity.ListFoodDetail.size() > 0) {
                            slht = sl;
                            boolean exists = false;
                            for (int i = 0; i < MainActivity.ListFoodDetail.size(); i++) {
                                if (MainActivity.ListFoodDetail.get(i).getId() == orderFoodDetails1.getId()) {
                                    MainActivity.ListFoodDetail.get(i).setSl(slht);
                                    if (MainActivity.ListFoodDetail.get(i).getSl() >= 10) {
                                        MainActivity.ListFoodDetail.get(i).setSl(10);
                                    }
                                    MainActivity.ListFoodDetail.get(i).setGia(orderFoodDetails1.getGia() * MainActivity.ListFoodDetail.get(i).getSl());
                                    exists = true;
                                    notifyDataSetChanged();
                                }
                            }
                            if (exists == false) {
                                slht = sl;
                                Double giamoi = orderFoodDetails1.getGia() * slht;
                                MainActivity.ListFoodDetail.add(new OrderFoodDetails(orderFoodDetails1.getId(), slht, orderFoodDetails1.getMota(), orderFoodDetails1.getTen(),orderFoodDetails1.getImg(), giamoi));
                                notifyDataSetChanged();
                            }
                        } else {
                            slht = sl;
                            Double giamoi = orderFoodDetails1.getGia() * slht;
                            MainActivity.ListFoodDetail.add(new OrderFoodDetails(orderFoodDetails1.getId(), slht, orderFoodDetails1.getMota(), orderFoodDetails1.getTen(),orderFoodDetails1.getImg(), giamoi));
                            notifyDataSetChanged();
                        }


                        dialog.dismiss();

                    }

                });
                dialog.show();
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context.getContext());
                builder.setTitle("Bạn muốn xóa món này?");
                builder.setCancelable(true);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.ListFoodDetail.remove(i);
                        Toast.makeText(context.getContext(), "Đã xóa món ăn", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Cancel
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
        return view;
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
}
