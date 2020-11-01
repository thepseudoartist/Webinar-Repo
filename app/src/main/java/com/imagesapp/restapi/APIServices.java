package com.imagesapp.restapi;

import com.imagesapp.models.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface APIServices {

//  @POST("transaction/placeOrder/")
//  Call<PlaceOrderResponse> placeOrderRequest(@Header("Authorization") String token, @Body PlaceOrderPayload payload);

    @GET("curated/")
    Call<ImageResponse> getCurated(@Query("page") int page, @Query("per_page") int num);

    @GET("search/")
    Call<ImageResponse> getImagesBySearch(@Query("query") String query, @Query("page") int page, @Query("per_page") int num);
}
