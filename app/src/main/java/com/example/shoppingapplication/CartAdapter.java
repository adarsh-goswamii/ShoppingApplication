package com.example.shoppingapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
    public interface TotalPrice{
        void totalPriceResult(String s);
    }

    public interface DeleteButton
    {
        void ondeleteResult(GroceryItem groceryItem);
    }

    private DeleteButton deleteButton;
    private TotalPrice totalPrice;
    private Fragment fragment;
    private Context context;
    private ArrayList<GroceryItem> items= new ArrayList<>();

    public CartAdapter(Context context, Fragment fragment)
    {
        this.context= context;
        this.fragment= fragment;
    }

    public void setItems(ArrayList<GroceryItem> items)
    {
        this.items= items;
        double ret= 0.0;
        for(GroceryItem i: items)
            ret+= i.getPrice();

        ret= ((int)(ret*100))/100.0;
        try{
            totalPrice= (TotalPrice) fragment;
            totalPrice.totalPriceResult(ret+" $");
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(items.get(position).getName());
        holder.cost.setText(items.get(position).getPrice()+" $");
        Glide.with(context).asBitmap().load(items.get(position).getImageUrl()).into(holder.image);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context).setTitle("Deleting")
                        .setMessage("Are you sure you want to delete this item?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try{
                                    DeleteButton deleteButton= (DeleteButton)fragment;
                                    deleteButton.ondeleteResult(items.get(position));
                                }
                                catch (ClassCastException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView name, cost;
        private Button btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.image_cart);
            name= itemView.findViewById(R.id.text_product_name);
            cost= itemView.findViewById(R.id.text_price);
            btn= itemView.findViewById(R.id.btn_delete);
        }
    }
}
