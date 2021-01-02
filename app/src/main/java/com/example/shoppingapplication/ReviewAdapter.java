package com.example.shoppingapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder2>
{
    private ArrayList<Reviews> reviews= new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_item, parent, false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        holder.username.setText(reviews.get(position).getName());
        holder.date.setText(reviews.get(position).getDate());
        holder.text.setText(reviews.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setItems(ArrayList<Reviews> reviews)
    {
        this.reviews= reviews;
        notifyDataSetChanged();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder
    {
        private TextView username, date, text;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);

            username= itemView.findViewById(R.id.text_user_name);
            text= itemView.findViewById(R.id.text_review_text);
            date= itemView.findViewById(R.id.text_date);
        }
    }
}
