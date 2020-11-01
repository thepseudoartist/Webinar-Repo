package com.imagesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.imagesapp.adapters.ImagesRecyclerViewAdapter;
import com.imagesapp.models.ImageMetaData;
import com.imagesapp.models.ImageResponse;
import com.imagesapp.pexels.PexelsClient;
import com.imagesapp.restapi.APIServices;
import com.imagesapp.restapi.AppClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editText;
    private TextView mainText;

    private PexelsClient pexelsClient;
    private ImagesRecyclerViewAdapter imagesRecyclerViewAdapter;
    private List<ImageMetaData> imageMetaDataList;

    private List<String> images = Arrays.asList(
            "https://images.pexels.com/photos/2539638/pexels-photo-2539638.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/3328340/pexels-photo-3328340.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5633723/pexels-photo-5633723.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5546123/pexels-photo-5546123.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4857499/pexels-photo-4857499.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4577423/pexels-photo-4577423.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5422779/pexels-photo-5422779.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5507740/pexels-photo-5507740.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5581939/pexels-photo-5581939.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5255104/pexels-photo-5255104.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5619326/pexels-photo-5619326.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4784171/pexels-photo-4784171.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4152096/pexels-photo-4152096.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5028844/pexels-photo-5028844.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4913366/pexels-photo-4913366.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4740560/pexels-photo-4740560.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5623698/pexels-photo-5623698.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4495708/pexels-photo-4495708.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4068029/pexels-photo-4068029.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5429087/pexels-photo-5429087.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5554235/pexels-photo-5554235.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5611966/pexels-photo-5611966.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5184238/pexels-photo-5184238.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4989092/pexels-photo-4989092.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5480326/pexels-photo-5480326.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4937821/pexels-photo-4937821.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5490493/pexels-photo-5490493.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5331973/pexels-photo-5331973.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5512664/pexels-photo-5512664.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4993100/pexels-photo-4993100.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/5333432/pexels-photo-5333432.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/3049353/pexels-photo-3049353.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4845906/pexels-photo-4845906.jpeg?auto=compress&cs=tinysrgb&h=350",
            "https://images.pexels.com/photos/4264047/pexels-photo-4264047.jpeg?auto=compress&cs=tinysrgb&h=350"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pexelsClient = PexelsClient.getInstance();

        initViews();

        imageMetaDataList = new ArrayList<>();

        for (String image: images) {
            imageMetaDataList.add(new ImageMetaData(image, "Steve Rogers"));
        }

        imagesRecyclerViewAdapter.setImageMetaDataList(imageMetaDataList);
//        getCuratedImages();
    }

    private void getImagesBySearch(String query) {
        pexelsClient.getImagesBySearch(query, 1, 50, new PexelsClient.ImageResponseCallback() {
            @Override
            public void onResponse(ImageResponse imageResponse) {
                if (imageMetaDataList == null) imageMetaDataList = new ArrayList<>();
                else imageMetaDataList.clear();

                for (ImageResponse.Photo photo: imageResponse.photos) {
                    imageMetaDataList.add(new ImageMetaData(photo.src.medium, photo.photographer));
                }

                imagesRecyclerViewAdapter.setImageMetaDataList(imageMetaDataList);
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "Unable to fetch images.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getCuratedImages() {
        pexelsClient.getCuratedImages(1, 50, new PexelsClient.ImageResponseCallback() {
            @Override
            public void onResponse(ImageResponse imageResponse) {
                if (imageMetaDataList == null) imageMetaDataList = new ArrayList<>();
                else imageMetaDataList.clear();

                for (ImageResponse.Photo photo: imageResponse.photos) {
                    imageMetaDataList.add(new ImageMetaData(photo.src.large, photo.photographer));
                }

                imagesRecyclerViewAdapter.setImageMetaDataList(imageMetaDataList);
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "Unable to fetch images.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view_images);
        imagesRecyclerViewAdapter = new ImagesRecyclerViewAdapter(this);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(imagesRecyclerViewAdapter);

        mainText = findViewById(R.id.main_text_view);
        editText = findViewById(R.id.main_edit_view);

        editText.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                String query = editText.getEditableText().toString();

                if (query != null) {
                    Toast.makeText(MainActivity.this, "Searching..", Toast.LENGTH_LONG).show();
                    mainText.setText("Showing results for: " + query);

                    getImagesBySearch(query);
                }
                return true;
            }

            return false;
        });
    }
}