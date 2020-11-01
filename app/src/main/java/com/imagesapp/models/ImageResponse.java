package com.imagesapp.models;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class ImageResponse implements Serializable {
    public String id;
    public String per_page;
    public List<Photo> photos;
    public String next_page;

    @NotNull
    @Override
    public String toString() {
        return "ImageResponse{" +
                "id='" + id + '\'' +
                ", per_page='" + per_page + '\'' +
                ", next_page='" + next_page + '\'' +
                ", photos=" + photos +
                '}';
    }

    public static class Photo implements Serializable {
        public String id;
        public String height;
        public String width;
        public String url;
        public String photographer;
        public String photographer_url;
        public String photographer_id;
        public PhotoSource src;
        public boolean liked;

        @NotNull
        @Override
        public String toString() {
            return "Photo{" +
                    "id='" + id + '\'' +
                    ", height='" + height + '\'' +
                    ", width='" + width + '\'' +
                    ", url='" + url + '\'' +
                    ", photographer='" + photographer + '\'' +
                    ", photographer_url='" + photographer_url + '\'' +
                    ", photographer_id='" + photographer_id + '\'' +
                    ", liked=" + liked +
                    ", photoSource=" + src +
                    '}';
        }

        public static class PhotoSource implements Serializable {
            public String original;
            public String large2x;
            public String large;
            public String medium;
            public String small;
            public String portrait;
            public String landscape;
            public String tiny;

            @NotNull
            @Override
            public String toString() {
                return "PhotoSource{" +
                        "original='" + original + '\'' +
                        ", large2x='" + large2x + '\'' +
                        ", large='" + large + '\'' +
                        ", medium='" + medium + '\'' +
                        ", small='" + small + '\'' +
                        ", portrait='" + portrait + '\'' +
                        ", landscape='" + landscape + '\'' +
                        ", tiny='" + tiny + '\'' +
                        '}';
            }
        }
    }
}