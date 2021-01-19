package com.example.shoppingapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class Utils
{
    private static int ID= 0, CART_ID= 0;
    private static final String ALL_ITEMS_KEY= "all_items_key";
    private static final String DB_NAME= "fake_database";
    private static final String CART_ITEMS_KEY= "cart";
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
                "Beverages",
                128.00);

        GroceryItem groceryItem2= new GroceryItem(
                100,
                "Cold Drink",
                "Pepsi is a carbonated soft drink manufactured by PepsiCo. Originally created and developed in 1893 by Caleb Bradham and introduced as Brad's Drink, it was renamed as Pepsi-Cola in 1898, and then shortened to Pepsi in 1961.",
                "https://i.pinimg.com/474x/3e/ed/4c/3eed4cda0a50a39563e856cde4d4518b.jpg",
                "Beverages",
                5.25);

        GroceryItem groceryItem3= new GroceryItem(
                56,
                "Butter",
                "Unsalted Butter is made from fresh cream and nothing else. It is Amul Butter in its purest form, and doesn't contain any salt at all. With lower moisture content than most other butters, it is an essential cooking and baking ingredient making it especially helpful when making pastries and puddings. 100% Natural !",
                "https://i.pinimg.com/474x/e0/b7/b5/e0b7b5dc4f133119fe7af28d6e5dfda0.jpg",
                "Cooking",
                1.35
        );

        GroceryItem groceryItem4= new GroceryItem(
                23,
                "ice cream",
                "Ice Cream was launched on 10th March, 1996 in Gujarat. The portfolio consisted of impulse products like sticks, cones, cups as well as take home packs and institutional/catering packs",
                "https://i.pinimg.com/474x/56/5c/d7/565cd7abcfb97191e9de1c35b49da5cb.jpg",
                "Packaged Foods",
                3.67
        );

        GroceryItem groceryItem5= new GroceryItem(
                23,
                "coffee",
                "Ice Cream was launched on 10th March, 1996 in Gujarat. The portfolio consisted of impulse products like sticks, cones, cups as well as take home packs and institutional/catering packs",
                "https://i.pinimg.com/474x/49/9c/4b/499c4b3ae57be1bd4af7aa22a17a178a.jpg",
                "Beverages",
                13.67
        );

        GroceryItem groceryItem6= new GroceryItem(
                12,
                "Shampoo",
                "",
                "https://i.pinimg.com/474x/8a/2b/b7/8a2bb72b76e4d47d8fb2d79e2a9183a8.jpg",
                "Household Supplies",
                25.16
        );


        allItem.add(groceryItem);
        allItem.add(groceryItem2);
        allItem.add(groceryItem3);
        allItem.add(groceryItem4);
        allItem.add(groceryItem5);
        allItem.add(groceryItem6);
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

    public static int getCartID() {
        CART_ID++;
        return CART_ID;
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

    public static void addToCart(Context context, GroceryItem item)
    {
        SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<GroceryItem> cartItems= gson.fromJson(sharedPreferences.getString(CART_ITEMS_KEY, null), groceryListType);
        if(cartItems== null)
            cartItems= new ArrayList<>();

        cartItems.add(item);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.remove(CART_ITEMS_KEY);
        editor.putString(CART_ITEMS_KEY, gson.toJson(cartItems));
        editor.commit();
    }

    public static ArrayList<GroceryItem> getCart(Context context)
    {
        SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, context.MODE_PRIVATE);
        ArrayList<GroceryItem> cartItems= gson.fromJson(sharedPreferences.getString(CART_ITEMS_KEY, null), groceryListType);
        return cartItems;
    }

    public static ArrayList<GroceryItem> searchByName(Context context, String find)
    {
        ArrayList<GroceryItem> allItems= getAllItems(context);
        if(allItems!= null)
        {
            ArrayList<GroceryItem> ret= new ArrayList<>();
            for(GroceryItem i: allItems)
            {
                if(i.getName().equalsIgnoreCase(find))
                {
                    ret.add(i);
                    continue;
                }
                String[] s= i.getName().split(" ");
                for(String j: s)
                    if(j.equalsIgnoreCase(find))
                    {
                        ret.add(i);
                        break;
                    }
            }
            return ret;
        }

        return null;
    }

    public static ArrayList<String> getAllCategories(Context context)
    {
        ArrayList<GroceryItem> allItems= getAllItems(context);
        HashSet<String> set= new HashSet<>();
        if(allItems!= null)
        {
            for(GroceryItem i: allItems)
                set.add(i.getCategory());
        }

        return new ArrayList<>(set);
    }

    public static ArrayList<GroceryItem> allCategoryItem(Context context, String category)
    {
        ArrayList<GroceryItem> allItems= getAllItems(context);
        if(allItems!= null)
        {
            ArrayList<GroceryItem> ret= new ArrayList<>();
            for(GroceryItem i: allItems)
                if(category.equalsIgnoreCase(i.getCategory()))
                    ret.add(i);

            return ret;
         }

        return null;
    }

    public static void DeleteItemFromCart(GroceryItem groceryItem, Context context)
    {
        ArrayList<GroceryItem> items= Utils.getCart(context);
        if(items!= null && items.size()>0)
        {
            ArrayList<GroceryItem> newItem= new ArrayList<>();
            for(GroceryItem i: items)
            {
                if(i.getId()!= groceryItem.getId())
                    newItem.add(i);
            }
            SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.remove(CART_ITEMS_KEY);
            editor.putString(CART_ITEMS_KEY, gson.toJson(newItem));
            editor.commit();
        }
    }

    public static void deleteCart(Context context)
    {
        SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.remove(CART_ITEMS_KEY);
        editor.commit();
    }

    public static void increasePopularity(Context context, GroceryItem groceryItem, int points)
    {
        ArrayList<GroceryItem> allItems= Utils.getAllItems(context);
        if(allItems!= null)
        {
            ArrayList<GroceryItem> newItems= new ArrayList<>();
            for(GroceryItem i: allItems)
            {
                if(i.getId()== groceryItem.getId())
                {
                    i.setPopularity(i.getPopularity()+ points);
                    newItems.add(i);
                }
                else
                    newItems.add(i);
            }

            SharedPreferences sharedPreferences= context.getSharedPreferences(DB_NAME, context.MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.remove(ALL_ITEMS_KEY);
            editor.putString(ALL_ITEMS_KEY, gson.toJson(newItems));
            editor.commit();
        }
    }
}
