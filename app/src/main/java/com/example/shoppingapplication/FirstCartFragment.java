package com.example.shoppingapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FirstCartFragment extends Fragment implements CartAdapter.DeleteButton, CartAdapter.TotalPrice
{
    @Override
    public void totalPriceResult(String s) {
        total.setText(s);
    }

    @Override
    public void ondeleteResult(GroceryItem groceryItem) {
        Utils.DeleteItemFromCart(groceryItem, getActivity());
        ArrayList<GroceryItem> allItems= Utils.getCart(getActivity());
        if(allItems!= null && allItems.size()>0)
        {
            noItemRelative.setVisibility(View.GONE);
            itemRelative.setVisibility(View.VISIBLE);
            totalRelative.setVisibility(View.VISIBLE);
            adapter.setItems(allItems);
        }
        else
        {
            itemRelative.setVisibility(View.GONE);
            totalRelative.setVisibility(View.GONE);
            noItemRelative.setVisibility(View.VISIBLE);
            lottieAnimationView.animate().start();
        }
    }

    private TextView total, placeOrder;
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private RelativeLayout noItemRelative, itemRelative, totalRelative;
    private LottieAnimationView lottieAnimationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.first_cart_item, container, false);
        initViews(view);
        adapter= new CartAdapter(getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        ArrayList<GroceryItem> allItems= Utils.getCart(getActivity());
        if(allItems== null || allItems.size()==0)
        {
            noItemRelative.setVisibility(View.VISIBLE);
            itemRelative.setVisibility(View.GONE);
            totalRelative.setVisibility(View.GONE);
        }
        else
        {
            noItemRelative.setVisibility(View.GONE);
            itemRelative.setVisibility(View.VISIBLE);
            totalRelative.setVisibility(View.VISIBLE);
            adapter.setItems(allItems);
        }

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new SecondCartFragment());
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void initViews(View view) {
        total= view.findViewById(R.id.text_sum);
        recyclerView= view.findViewById(R.id.recycler_view);
        placeOrder= view.findViewById(R.id.text_place_order);
        noItemRelative= view.findViewById(R.id.relative_layout_noitem);
        itemRelative= view.findViewById(R.id.relative_layout_items);
        totalRelative= view.findViewById(R.id.relative_layout_total);
        lottieAnimationView= view.findViewById(R.id.lottie_nothing);
    }
}
