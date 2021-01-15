package com.example.shoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static com.example.shoppingapplication.AllCategoriesDialog.ALL_CATEGORIES;
import static com.example.shoppingapplication.AllCategoriesDialog.CALLING_ACTIVITY;

public class SearchActivity extends AppCompatActivity implements AllCategoriesDialog.GetCategory{

    @Override
    public void onGetCategoryResult(String category) {
        ArrayList<GroceryItem> items= Utils.allCategoryItem(this, category);
        Log.e("Category= ", category);
        if(items!= null) {
            adapter.setItems(items);
        }
    }

    private EditText editText;
    private Button first, second, third, fourth;
    private TextView all;
    private RecyclerView recyclerView;
    private ImageView search;
    private GroceryItemAdapter adapter;
    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();
        initBottomNavView();
        adapter= new GroceryItemAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Intent intent= getIntent();
        if(intent!= null)
        {
            String category= intent.getStringExtra("category");
            if(category!= null) {
                ArrayList<GroceryItem> items= Utils.allCategoryItem(this, category);
                adapter.setItems(items);
            }
        }

        setSupportActionBar(toolbar);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSearch();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                initSearch();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ArrayList<String> categories= Utils.getAllCategories(this);
        if(categories!= null)
        {
            first.setText(categories.get(0));
            second.setText(categories.get(1));
            third.setText(categories.get(2));
            fourth.setText(categories.get(3));
        }

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<GroceryItem> items= Utils.allCategoryItem(SearchActivity.this, first.getText().toString());
                if(items!= null)
                {
                    adapter.setItems(items);
                }
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<GroceryItem> items= Utils.allCategoryItem(SearchActivity.this, second.getText().toString());
                if(items!= null){
                    adapter.setItems(items);
                }
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<GroceryItem> items= Utils.allCategoryItem(SearchActivity.this, third.getText().toString());
                if(items!= null)
                {
                    adapter.setItems(items);
                }
            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<GroceryItem> items= Utils.allCategoryItem(SearchActivity.this, fourth.getText().toString());
                if(items!= null)
                {
                    adapter.setItems(items);
                }
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllCategoriesDialog dialog= new AllCategoriesDialog();
                Bundle bundle= new Bundle();
                bundle.putStringArrayList(ALL_CATEGORIES, Utils.getAllCategories(SearchActivity.this));
                bundle.putString(CALLING_ACTIVITY, "search_activity");
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "all_categories_dialog");
            }
        });
    }

    private void initSearch()
    {
        String text= editText.getText().toString();
        if(!text.equals(""))
        {
            ArrayList<GroceryItem> items= Utils.searchByName(this, text);
            if(items!= null)
                adapter.setItems(items);
        }
    }

    private void initViews()
    {
        editText= findViewById(R.id.edit_text_search);
        first= findViewById(R.id.first_category);
        second= findViewById(R.id.second_category);
        third= findViewById(R.id.third_category);
        fourth= findViewById(R.id.fourth_category);
        all= findViewById(R.id.text_see_all_categories);
        recyclerView= findViewById(R.id.recycler_view_search);
        search= findViewById(R.id.image_search);
        bottomNavigationView= findViewById(R.id.bottomNavigationView);
        toolbar= findViewById(R.id.toolbar);
    }

    private void initBottomNavView()
    {
        bottomNavigationView.setSelectedItemId(R.id.search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.home:
                        Intent intent= new Intent(SearchActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;

                    case R.id.search:
                        break;

                    case R.id.cart:
                        Intent intent1= new Intent(SearchActivity.this, CartActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                        break;

                    default:
                        break;
                }
                return false;
            }
        });
    }
}