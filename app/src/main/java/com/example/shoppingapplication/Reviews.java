package com.example.shoppingapplication;

public class Reviews
{
    private String name, text, date;
    private int groceryItemId;

    public Reviews(String name, String text, String date, int groceryItemId) {
        this.name = name;
        this.text = text;
        this.date = date;
        this.groceryItemId = groceryItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGroceryItemId() {
        return groceryItemId;
    }

    public void setGroceryItemId(int groceryItemId) {
        this.groceryItemId = groceryItemId;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", groceryItemId=" + groceryItemId +
                '}';
    }
}
