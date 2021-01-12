package com.example.shoppingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainFragment extends Fragment
{

    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView_new_items, recyclerView_popular_items, recyclerView_suggested_items;
    private GroceryItemAdapter adapter_new_items, adapter_popular_items, adapter_suggested_items;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, container, false);

        initView(view);
        initBottomNavView();

        return view;
    }

    private void initRecView()
    {
        adapter_new_items= new GroceryItemAdapter(getActivity());
        adapter_popular_items= new GroceryItemAdapter(getActivity());
        adapter_suggested_items= new GroceryItemAdapter(getActivity());

        recyclerView_new_items.setAdapter(adapter_new_items);
        recyclerView_popular_items.setAdapter(adapter_popular_items);
        recyclerView_suggested_items.setAdapter(adapter_suggested_items);

        recyclerView_new_items.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView_popular_items.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView_suggested_items.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        ArrayList<GroceryItem> newItems= Utils.getAllItems(getActivity());
        if(newItems!= null)
        {
            Comparator<GroceryItem> comparatorNewItems= new Comparator<GroceryItem>() {
                @Override
                public int compare(GroceryItem a, GroceryItem b) {
                    return b.getId()- a.getId();
                }
            };
            Collections.sort(newItems, comparatorNewItems);
            adapter_new_items.setItems(newItems);
        }

        ArrayList<GroceryItem> popularItems= Utils.getAllItems(getActivity());
        if(popularItems!= null)
        {
            Comparator<GroceryItem> comparatorPopularItems= new Comparator<GroceryItem>() {
                @Override
                public int compare(GroceryItem a, GroceryItem b) {
                    return b.getPopularity()- a.getPopularity();
                }
            };
            Collections.sort(popularItems, comparatorPopularItems);
            adapter_popular_items.setItems(popularItems);
        }

        ArrayList<GroceryItem> suggestedItems= Utils.getAllItems(getActivity());
        if(suggestedItems!= null)
        {
            Comparator<GroceryItem> comparatorSuggestedItems= new Comparator<GroceryItem>() {
                @Override
                public int compare(GroceryItem a, GroceryItem b) {
                    return b.getUserPoint()- a.getUserPoint();
                }
            };
            Collections.sort(suggestedItems, comparatorSuggestedItems);
            adapter_suggested_items.setItems(suggestedItems);
        }
    }

    private void initView(View view)
    {
        bottomNavigationView= view.findViewById(R.id.bottomNavigationView);
        recyclerView_new_items= view.findViewById(R.id.recycler_new_item);
        recyclerView_popular_items= view.findViewById(R.id.recycler_popular_items);
        recyclerView_suggested_items= view.findViewById(R.id.recycler_suggested_items);
    }

    private void initBottomNavView()
    {
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.home:
                        Toast.makeText(getActivity(), "Home selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.search:
                        Intent intent= new Intent(getActivity(), SearchActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;

                    case R.id.cart:
                        Intent intent2= new Intent(getActivity(), CartActivity.class);
                        intent2.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK));
                        startActivity(intent2);
                        break;

                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecView();
    }
}
