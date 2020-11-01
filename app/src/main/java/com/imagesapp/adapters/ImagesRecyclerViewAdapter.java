package com.imagesapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.imagesapp.ImageActivity;
import com.imagesapp.R;
import com.imagesapp.models.ImageMetaData;
import com.imagesapp.models.ImageResponse;

import java.util.List;

public class ImagesRecyclerViewAdapter extends RecyclerView.Adapter<ImagesRecyclerViewAdapter.ImagesViewHolder> {

    private final Context context;
    private List<ImageMetaData> imageMetaDataList;

    public ImagesRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        * LayoutInflater helps us to parse XML files directly to generate a view object.
        *
        * We needed context instance so that we can inflate the layout, attachToRoot is passed as false
        * because we are not responsible to attach the view to its parent, this is Adapter's job.
        *
        * Secondly, ViewHolder contains how a single view in list should look like hence we pass item
        * view to ImagesViewHolder class instance.
        * */

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_images, parent, false);
        return new ImagesViewHolder(view);
    }

    // When adapter will call this function we will have to attach correct data to its specific view holder
    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        if (imageMetaDataList != null) {
            String url = imageMetaDataList.get(position).imageUrl;
            String photographerName = imageMetaDataList.get(position).photographerName;

            holder.cardView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ImageActivity.class);
                intent.putExtra("IMAGE_URL", url);
                intent.putExtra("PHOTOGRAPHER_NAME", photographerName);

                context.startActivity(intent);
            });

            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .fitCenter()
                    .into(holder.imageView);

            holder.textView.setText(photographerName);
        }
    }

    @Override
    public int getItemCount() {
        // Pass the number of item your list is going to have
        return imageMetaDataList == null ? 0 : imageMetaDataList.size();
    }

    public void setImageMetaDataList(List<ImageMetaData> imageMetaDataList) {
        if (imageMetaDataList != null) this.imageMetaDataList = imageMetaDataList;
        notifyDataSetChanged();
    }

    // ViewHolder contains items and its metadata
    static class ImagesViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        ImageView imageView;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.rv_images_text_view);
            imageView = itemView.findViewById(R.id.rv_images_image_view);
            cardView = itemView.findViewById(R.id.rv_images_card_view);
        }
    }
}