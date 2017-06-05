package com.example.zimny.newsletter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ideo7 on 05.06.2017.
 */

public class NewsletterAdapter extends RecyclerView.Adapter<NewsletterAdapter.MyViewHolder> {

    private List<Newsletter> newsletterList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_newsletter);
            date = (TextView) view.findViewById(R.id.date);
        }
    }


    public NewsletterAdapter(List<Newsletter> newsletterList) {
        this.newsletterList = newsletterList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_newsletter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Newsletter newsletter = newsletterList.get(position);
        holder.title.setText(newsletter.getName());
        holder.date.setText(newsletter.getDate_send().toString());
    }

    @Override
    public int getItemCount() {
        return newsletterList.size();
    }
}