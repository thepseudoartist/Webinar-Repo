package com.imagesapp.models;

import org.jetbrains.annotations.NotNull;

public class ImageMetaData {
    public String imageUrl;
    public String photographerName;

    public ImageMetaData(String imageUrl, String photographerName) {
        this.imageUrl = imageUrl;
        this.photographerName = photographerName;
    }

    @NotNull
    @Override
    public String toString() {
        return "ImageMetaData{" +
                "imageUrl='" + imageUrl + '\'' +
                ", photographerName='" + photographerName + '\'' +
                '}';
    }
}
