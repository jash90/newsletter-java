package com.example.zimny.newsletter.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zimny.newsletter.Fragment.ListNewslettersFragment;
import com.example.zimny.newsletter.Fragment.NewsletterFragment;
import com.example.zimny.newsletter.Model.Newsletter;
import com.example.zimny.newsletter.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by ideo7 on 05.06.2017.
 */

public class NewslettersAdapter extends RecyclerView.Adapter<NewslettersAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Newsletter> newsletters;

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


    public NewslettersAdapter(ArrayList<Newsletter> newsletterList) {
        this.newsletters = newsletterList;
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
        if (newsletter != null) {
            holder.title.setText(newsletter.getTytul());

            holder.date.setText("data: " + new SimpleDateFormat("dd.MM.yyyy").format(Timestamp.valueOf(newsletter.getData_wyslania()).getTime()));
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Attributes.setId_newsletter(newsletter.getId());
                NewsletterFragment newsletterFragment = new NewsletterFragment();
                fragmentTransaction.replace(R.id.content, newsletterFragment);
                fragmentTransaction.addToBackStack("Fragment");
                fragmentTransaction.commit();


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