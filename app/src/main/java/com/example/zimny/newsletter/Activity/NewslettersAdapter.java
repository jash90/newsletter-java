package com.example.zimny.newsletter.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zimny.newsletter.Model.Newsletter;
import com.example.zimny.newsletter.R;

import java.util.ArrayList;

/**
 * Created by ideo7 on 05.06.2017.
 */

public class NewslettersAdapter extends RecyclerView.Adapter<NewslettersAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Newsletter> newsletters;
    private String login_token;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;
        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_newsletter);
            date = (TextView) view.findViewById(R.id.date);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
            context = view.getContext();
        }
    }


    public NewslettersAdapter(ArrayList<Newsletter> newsletterList, String login_token) {
        this.newsletters = newsletterList;
        this.login_token = login_token;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_newsletter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Newsletter newsletter = newsletters.get(position);
        if (newsletter != null)
            holder.title.setText(newsletter.getTytul());
        holder.date.setText("data: " + newsletter.getData_wyslania());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context.getApplicationContext(), NewsletterActivity.class);
                intent.putExtra("login_token", login_token);
                intent.putExtra("id_newsletter", newsletter.getId());
                context.startActivity(intent);


            }
        });
        Log.d("ddd", newsletter.toString());


        if (position % 2 == 0) {
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        } else {
            holder.linearLayout.setBackgroundResource(R.color.grey_element);
        }
    }

    @Override
    public int getItemCount() {
        return newsletters.size();
    }
}