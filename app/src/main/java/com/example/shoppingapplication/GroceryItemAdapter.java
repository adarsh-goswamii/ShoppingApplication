package com.example.shoppingapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class GroceryItemAdapter extends RecyclerView.Adapter<GroceryItemAdapter.ViewHolder>
{
    private List<GroceryItem> items= new ArrayList<>();
    private Context context;

    public GroceryItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.textName.setText(items.get(position).getName());
        holder.textPrice.setText(String.valueOf(items.get(position).getPrice())+"$");
        Glide.with(context).asBitmap().load(items.get(position).getImageUrl()).into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, GroceryItemActivity.class);
                intent.putExtra(GroceryItemActivity.GROCERY_ITEM_KEY, items.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items== null? 0: items.size();
    }

    public void setItems(ArrayList<GroceryItem> items)
    {
        this.items= items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView textName, textPrice;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image= itemView.findViewById(R.id.card_image);
            textName= itemView.findViewById(R.id.card_text_name);
            textPrice= itemView.findViewById(R.id.card_text_price);
            cardView= itemView.findViewById(R.id.card_view);
        }
    }

}
