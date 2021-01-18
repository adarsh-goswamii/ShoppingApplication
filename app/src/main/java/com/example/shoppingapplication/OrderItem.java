package com.example.shoppingapplication;

import java.util.ArrayList;

public class OrderItem
{
    private String name, number, pin, landmark, state, city, area, houseno;
    private ArrayList<GroceryItem> items= new ArrayList<>();
    private int id;

    public OrderItem() {
    }

    public OrderItem(String name, String number, String pin, String landmark, String state, String city, String area, String houseno, ArrayList<GroceryItem> items) {
        id= Utils.getCartID();
        this.name = name;
        this.number = number;
        this.pin = pin;
        this.landmark = landmark;
        this.state = state;
        this.city = city;
        this.area = area;
        this.houseno = houseno;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public ArrayList<GroceryItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GroceryItem> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", pin='" + pin + '\'' +
                ", landmark='" + landmark + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", houseno='" + houseno + '\'' +
                ", items=" + items +
                ", id=" + id +
                '}';
    }
}
