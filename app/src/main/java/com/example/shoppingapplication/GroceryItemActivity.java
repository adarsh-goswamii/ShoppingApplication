package com.example.shoppingapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class GroceryItemActivity extends AppCompatActivity implements AddReviewDialog.AddReview
{
    @Override
    public void onAddReviewResults(Reviews review) {
        Utils.AddReview(this, review);
        ArrayList<Reviews> reviews= Utils.getReviewsById(this, review.getGroceryItemId());
        if(reviews!= null)
            adapter.setItems(reviews);
    }

    private RecyclerView recyclerViewReviews;
    private TextView productName, productPrice, addReview, productDescription;
    private RelativeLayout relFirstStar, relSecondStar, relThirdStar;
    private ImageView emptyStar1, emptyStar2, emptyStar3, filledStar1, filledStar2, filledStar3, productImage;
    private Button btn;
    private MaterialToolbar toolbar;
    public static String GROCERY_ITEM_KEY= "incoming_item";
    private GroceryItem product;
    private ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);
        initViews();
        adapter= new ReviewAdapter();
        setSupportActionBar(toolbar);

        Intent intent= getIntent();
        if(intent!= null)
        {
            product= intent.getParcelableExtra(GROCERY_ITEM_KEY);
            if(product!= null)
            {
                productName.setText(product.getName());
                productPrice.setText(product.getPrice()+" $");
                productDescription.setText(product.getDes());
                Glide.with(this).asBitmap().load(product.getImageUrl()).into(productImage);
                recyclerViewReviews.setAdapter(adapter);
                recyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));
                ArrayList<Reviews> reviews= Utils.getReviewsById(this, product.getId());
                if(reviews!= null) {
                    if (reviews.size() != 0) {
                        adapter.setItems(reviews);
                    }
                }

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Utils.addToCart(GroceryItemActivity.this, product);
                        Intent intent= new Intent(GroceryItemActivity.this, CartActivity.class);
                        startActivity(intent);
                    }
                });

                addReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddReviewDialog dialog= new AddReviewDialog();
                        Bundle bundle= new Bundle();
                        bundle.putParcelable(GROCERY_ITEM_KEY, product);
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "add review");
                    }
                });

                handleRatings();

            }
        }
    }

    private void handleRatings()
    {
        switch (product.getRate())
        {
            case 0:
                emptyStar1.setVisibility(View.VISIBLE);
                emptyStar2.setVisibility(View.VISIBLE);
                emptyStar3.setVisibility(View.VISIBLE);
                filledStar1.setVisibility(View.GONE);
                filledStar2.setVisibility(View.GONE);
                filledStar3.setVisibility(View.GONE);
                break;
            case 1:
                emptyStar1.setVisibility(View.GONE);
                emptyStar2.setVisibility(View.VISIBLE);
                emptyStar3.setVisibility(View.VISIBLE);
                filledStar1.setVisibility(View.VISIBLE);
                filledStar2.setVisibility(View.GONE);
                filledStar3.setVisibility(View.GONE);
                break;
            case 2:
                emptyStar1.setVisibility(View.GONE);
                emptyStar2.setVisibility(View.GONE);
                emptyStar3.setVisibility(View.VISIBLE);
                filledStar1.setVisibility(View.VISIBLE);
                filledStar2.setVisibility(View.VISIBLE);
                filledStar3.setVisibility(View.GONE);
                break;
            case 3:
                emptyStar1.setVisibility(View.GONE);
                emptyStar2.setVisibility(View.GONE);
                emptyStar3.setVisibility(View.GONE);
                filledStar1.setVisibility(View.VISIBLE);
                filledStar2.setVisibility(View.VISIBLE);
                filledStar3.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        relFirstStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getRate()!= 1)
                {
                    Utils.changeRate(GroceryItemActivity.this, product.getId(), 1);
                    product.setRate(1);
                    handleRatings();
                }
            }
        });

        relSecondStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getRate()!= 2)
                {
                    Utils.changeRate(GroceryItemActivity.this, product.getId(), 2);
                    product.setRate(2);
                    handleRatings();
                }
            }
        });

        relThirdStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getRate()!= 3)
                {
                    Utils.changeRate(GroceryItemActivity.this, product.getId(), 3);
                    product.setRate(3);
                    handleRatings();
                }
            }
        });

    }

    private void initViews() {
        recyclerViewReviews= findViewById(R.id.recycler_view_reviews);
        productName= findViewById(R.id.text_name);
        productPrice= findViewById(R.id.text_price);
        productImage= findViewById(R.id.image_view);
        emptyStar1= findViewById(R.id.image_view_first_empty_star);
        emptyStar2= findViewById(R.id.image_view_second_empty_star);
        emptyStar3= findViewById(R.id.image_view_third_empty_star);
        filledStar1= findViewById(R.id.image_view_first_filled_star);
        filledStar2= findViewById(R.id.image_view_second_filled_star);
        filledStar3= findViewById(R.id.image_view_third_filled_star);
        addReview= findViewById(R.id.text_add_a_text);
        btn= findViewById(R.id.btn_add_to_cart);
        toolbar= findViewById(R.id.toolbar);
        productDescription= findViewById(R.id.text_description);
        relFirstStar= findViewById(R.id.relative_layout_first_star);
        relSecondStar= findViewById(R.id.relative_layout_second_star);
        relThirdStar= findViewById(R.id.relative_layout_third_star);
    }
}