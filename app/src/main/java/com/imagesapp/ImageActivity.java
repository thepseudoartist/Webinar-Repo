package com.imagesapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {
    private String imageURL;
    private String photographerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Bundle bundle = getIntent().getExtras();
        imageURL = bundle.getString("IMAGE_URL");
        photographerName = bundle.getString("PHOTOGRAPHER_NAME");

        initViews();
    }

    private void initViews() {
        if (imageURL != null) {
            // tried a little hack to save time
            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();

            imageURL = imageURL.substring(0, imageURL.indexOf('?'));
            imageURL.concat("?auto=compress&cs=tinysrgb&h=" + metrics.heightPixels + "&w=" + metrics.widthPixels);

            Glide.with(this)
                    .load(imageURL)
                    .override(metrics.widthPixels, metrics.heightPixels)
                    .into((ImageView) findViewById(R.id.act_image_image_view));
        }

        else Toast.makeText(this, "IMAGE NOT FOUND", Toast.LENGTH_LONG).show();

        findViewById(R.id.act_image_button).setOnClickListener(view -> {
            Intent intent = new Intent(ImageActivity.this, MainActivity.class);
            startActivity(intent);
        });

        ((TextView) findViewById(R.id.act_image_text_view)).setText(photographerName);
    }
}
