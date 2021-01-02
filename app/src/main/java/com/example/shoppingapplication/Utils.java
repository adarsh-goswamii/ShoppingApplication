package com.example.shoppingapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils
{
    private static int ID= 0;
    private static final String ALL_ITEMS_KEY= "all_items_key";
    private static final String DB_NAME= "fake_database";
    private static Gson gson= new Gson();
    private static Type groceryListType= new TypeToken<ArrayList<GroceryItem>>(){}.getType();

    public static void initSharedPreferences(Context context)
    {
        SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItem= gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        if(allItem== null)
        {
            initAllItems(context);
        }
    }

    private static void initAllItems(Context context) {
        ArrayList<GroceryItem> allItem= new ArrayList<>();
        GroceryItem groceryItem= new GroceryItem(
                200 ,
                "Milk",
                "Milk is a nutrient-rich liquid food produced by the mammary glands of mammals. It is the primary source of nutrition for young mammals, including breastfed human infants before they are able to digest solid food",
                "https://5.imimg.com/data5/XH/TF/GLADMIN-26636619/amul-taaza-toned-milk-1-lt-carton-500x500.jpg",
                "drinks",
                128.00);

        GroceryItem groceryItem2= new GroceryItem(
                100,
                "Pepsi",
                "Pepsi is a carbonated soft drink manufactured by PepsiCo. Originally created and developed in 1893 by Caleb Bradham and introduced as Brad's Drink, it was renamed as Pepsi-Cola in 1898, and then shortened to Pepsi in 1961.",
                "https://i.pinimg.com/474x/3e/ed/4c/3eed4cda0a50a39563e856cde4d4518b.jpg",
                "drinks",
                5.25);

        GroceryItem groceryItem3= new GroceryItem(
                56,
                "Butter",
                "Unsalted Butter is made from fresh cream and nothing else. It is Amul Butter in its purest form, and doesn't contain any salt at all. With lower moisture content than most other butters, it is an essential cooking and baking ingredient making it especially helpful when making pastries and puddings. 100% Natural !",
                "https://i.pinimg.com/474x/e0/b7/b5/e0b7b5dc4f133119fe7af28d6e5dfda0.jpg",
                "foods",
                1.35
        );

        allItem.add(groceryItem);
        allItem.add(groceryItem2);
        allItem.add(groceryItem3);
        SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(ALL_ITEMS_KEY, gson.toJson(allItem));
        editor.commit();
    }

    public static ArrayList<GroceryItem> getAllItems(Context context)
    {
        SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItems= gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        return allItems;
    }

    public static int getID() {
        ID++;
        return ID;
    }

    public static void changeRate(Context context, int itemId, int rate)
    {
        SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        ArrayList<GroceryItem> items= gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        if(items!= null)
        {
            ArrayList<GroceryItem> newItem= new ArrayList<>();
            for(GroceryItem i: items)
            {
                if(i.getId()== itemId)
                {
                    i.setRate(rate);
                    newItem.add(i);
                }
                else
                    newItem.add(i);
            }

            editor.remove(ALL_ITEMS_KEY);
            editor.putString(ALL_ITEMS_KEY, gson.toJson(newItem));
            editor.commit();
        }
    }

    public static void AddReview(Context context, Reviews reviews)
    {
        SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        ArrayList<GroceryItem> items= getAllItems(context);
        if(items!= null) {
            ArrayList<GroceryItem> newItems = new ArrayList<>();
            for (GroceryItem i : items) {
                if (i.getId() == reviews.getGroceryItemId()) {
                    ArrayList<Reviews> temp = i.getReview();
                    temp.add(reviews);
                    i.setReview(temp);
                    newItems.add(i);
                } else
                    newItems.add(i);
            }

            editor.remove(ALL_ITEMS_KEY);
            editor.putString(ALL_ITEMS_KEY, gson.toJson(newItems));
            editor.commit();
        }
    }

    public static ArrayList<Reviews> getReviewsById(Context context, int itemId)
    {
        SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        ArrayList<GroceryItem> items= getAllItems(context);
        if(items!= null) {
            for (GroceryItem i : items) {
                if (itemId == i.getId())
                    return i.getReview();
            }
        }

        return null;
    }
}
