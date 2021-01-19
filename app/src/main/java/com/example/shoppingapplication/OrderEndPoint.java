package com.example.shoppingapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderEndPoint
{
    @POST("posts")
    Call<OrderItem> newOrder(@Body OrderItem orderItem);
}
