package com.example.zimny.newsletter.Activity;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zimny.newsletter.Class.Newsletter;
import com.example.zimny.newsletter.Class.NewsletterContent;
import com.example.zimny.newsletter.Class.Newsletters;
import com.example.zimny.newsletter.R;
import com.example.zimny.newsletter.Retrofit.BeinsuredClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ideo7 on 05.06.2017.
 */

public class NewsletterAdapter extends RecyclerView.Adapter<NewsletterAdapter.MyViewHolder> {

    private ArrayList<Newsletter> newsletters;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;
        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_newsletter);
            date = (TextView) view.findViewById(R.id.date);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        }
    }


    public NewsletterAdapter(ArrayList<Newsletter> newsletterList) {
        this.newsletters = newsletterList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_newsletter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Newsletter newsletter = newsletters.get(position);
        if (newsletter!=null)
        holder.title.setText(newsletter.getTytul());
        holder.date.setText("data: "+newsletter.getData_wyslania());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("dddd", Main2Activity.getLogin_token());
                    OkHttpClient.Builder okbuilder = new OkHttpClient.Builder();
                    okbuilder.addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            Request.Builder newrequest = request.newBuilder().addHeader("Authtoken", Main2Activity.getLogin_token());
                            Log.d("dddd", Main2Activity.getLogin_token());
                            return chain.proceed(newrequest.build());
                        }
                    });

                    Retrofit.Builder builder = new Retrofit.Builder()
                            .baseUrl("http://www.beinsured.t.ideo/api/v1/1/pl/")
                            .client(okbuilder.build())
                            .addConverterFactory(GsonConverterFactory.create());
                    Retrofit retrofit = builder.build();
                    BeinsuredClient beinsuredClient = retrofit.create(BeinsuredClient.class);
                    Log.d("ddd",retrofit.baseUrl().toString());
                    Call<NewsletterContent> call = beinsuredClient.getNewsletter();
                    call.enqueue(new Callback<NewsletterContent>() {
                        @Override
                        public void onResponse(Call<NewsletterContent> call, Response<NewsletterContent> response) {
                            Log.d("ddd",response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<NewsletterContent> call, Throwable t) {

                        }
                    });
                }
                catch (Exception ex)
                {
                    Log.d("error",ex.getLocalizedMessage());
                }

    }});
        Log.d("ddd",newsletter.toString());


        if (position %2 ==0)
        {
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        }
        else {
            holder.linearLayout.setBackgroundResource(R.color.grey_element);
        }
    }

    @Override
    public int getItemCount() {
        return newsletters.size();
    }
}