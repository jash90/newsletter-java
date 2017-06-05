package com.example.zimny.newsletter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ideo7 on 05.06.2017.
 */

public class Newslettersss extends RecyclerView.Adapter<Newslettersss.ViewHolder> {
    @Override
    public Newslettersss.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View newsletterView = inflater.inflate(R.layout.row_newsletter, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(newsletterView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(Newslettersss.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Newsletter newsletter = newsletters.get(position);

        // Set item views based on your views and data model
        TextView title_newsletter = viewHolder.title_newsletter;
        title_newsletter.setText(newsletter.getName());
        TextView date = viewHolder.date;
        date.setText(newsletter.getDate_send().toString());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return newsletters.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title_newsletter;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            title_newsletter = (TextView) itemView.findViewById(R.id.title_newsletter);
            date = (TextView) itemView.findViewById(R.id.date);
        }

    }
    private List<Newsletter> newsletters;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public Newslettersss(Context context, List<Newsletter> newsletterList) {
        newsletters = newsletterList;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }
}
