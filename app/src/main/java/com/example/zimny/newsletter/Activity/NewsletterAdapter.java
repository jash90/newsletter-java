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
import com.example.zimny.newsletter.Class.Newsletters;
import com.example.zimny.newsletter.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Newsletter newsletter = newsletters.get(position);
        if (newsletter!=null)
        holder.title.setText(newsletter.getTytul());
        holder.date.setText("data: "+newsletter.getData_wyslania());
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