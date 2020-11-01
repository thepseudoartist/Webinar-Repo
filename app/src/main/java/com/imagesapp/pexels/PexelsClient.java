package com.imagesapp.pexels;

import android.util.Log;

import com.imagesapp.models.ImageResponse;
import com.imagesapp.restapi.APIServices;
import com.imagesapp.restapi.AppClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PexelsClient {
    private static final String TAG = "PexelsClient";

    public interface ImageResponseCallback {
        void onResponse(ImageResponse imageResponse);
        void onFailure();
    }

    private static PexelsClient mInstance;
    private final APIServices apiServices;

    private PexelsClient() {
        apiServices = AppClient.getInstance().createService(APIServices.class);
    }

    public static synchronized PexelsClient getInstance() {
        if (mInstance == null) mInstance = new PexelsClient();
        return mInstance;
    }

    public void getCuratedImages(int page, int num, ImageResponseCallback callback) {
        Call<ImageResponse> call = apiServices.getCurated(page, num);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(@NotNull Call<ImageResponse> call, @NotNull Response<ImageResponse> response) {
                if (response != null && response.isSuccessful())
                    callback.onResponse(response.body());
                else {
                    if (response == null) Log.e(TAG, "getCuratedImages returned null response.");
                    else Log.e(TAG, "getCuratedImages GET request failed with code " + response.code());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ImageResponse> call, @NotNull Throwable t) {
                callback.onFailure();
                Log.e(TAG, "getCuratedImages function failed to fetch data successfully.");
            }
        });
    }

    public void getImagesBySearch(String query, int page, int num, ImageResponseCallback callback) {
        Call<ImageResponse> call = apiServices.getImagesBySearch(query, page, num);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(@NotNull Call<ImageResponse> call, @NotNull Response<ImageResponse> response) {
                if (response != null && response.isSuccessful())
                    callback.onResponse(response.body());
                else {
                    if (response == null) Log.e(TAG, "getImagesBySearch returned null response.");
                    else Log.e(TAG, "getImagesBySearch GET request failed with code " + response.code());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ImageResponse> call, @NotNull Throwable t) {
                callback.onFailure();
                Log.e(TAG, "getImagesBySearch function failed to fetch data successfully.");
            }
        });
    }
}
