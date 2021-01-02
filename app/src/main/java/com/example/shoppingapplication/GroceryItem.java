package com.example.shoppingapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class GroceryItem implements Parcelable
{

    protected GroceryItem(Parcel in) {
        id = in.readInt();
        availableAmount = in.readInt();
        rate = in.readInt();
        userPoint = in.readInt();
        popularity = in.readInt();
        name = in.readString();
        des = in.readString();
        imageUrl = in.readString();
        category = in.readString();
        price = in.readDouble();
    }

    public static final Creator<GroceryItem> CREATOR = new Creator<GroceryItem>() {
        @Override
        public GroceryItem createFromParcel(Parcel in) {
            return new GroceryItem(in);
        }

        @Override
        public GroceryItem[] newArray(int size) {
            return new GroceryItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(availableAmount);
        parcel.writeInt(rate);
        parcel.writeInt(userPoint);
        parcel.writeInt(popularity);
        parcel.writeString(name);
        parcel.writeString(des);
        parcel.writeString(imageUrl);
        parcel.writeString(category);
        parcel.writeDouble(price);
    }

    private int id, availableAmount, rate, userPoint, popularity;
    private String name, des, imageUrl, category;
    private double price;
    private ArrayList<Reviews> review;

    public GroceryItem(int availableAmount, String name, String des, String imageUrl, String category, double price) {
        this.id = Utils.getID();
        this.availableAmount = availableAmount;
        this.name = name;
        this.des = des;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
        rate= 0;
        popularity= 0;
        review= new ArrayList<>();
        userPoint= 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Reviews> getReview() {
        return review;
    }

    public void setReview(ArrayList<Reviews> review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + id +
                ", availableAmount=" + availableAmount +
                ", rate=" + rate +
                ", userPoint=" + userPoint +
                ", popularity=" + popularity +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", review=" + review +
                '}';
    }
}
