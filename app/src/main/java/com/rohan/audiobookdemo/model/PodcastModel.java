package com.rohan.audiobookdemo.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class PodcastModel implements Parcelable {
    public static final Creator<PodcastModel> CREATOR = new Creator<PodcastModel>() {
        @Override
        public PodcastModel createFromParcel(Parcel in) {
            return new PodcastModel(in);
        }

        @Override
        public PodcastModel[] newArray(int size) {
            return new PodcastModel[size];
        }
    };
    private String thumbnail;
    private String title_original;
    private String publisher_original;
    private String description;
    private boolean isFavourite;


    public PodcastModel( String title_original, String publisher_original, String thumbnail, String description) {
        this.thumbnail = thumbnail;
        this.title_original = title_original;
        this.publisher_original = publisher_original;
        this.description = description;
        this.isFavourite = false;
    }

    protected PodcastModel(Parcel in) {
        thumbnail = in.readString();
        title_original = in.readString();
        publisher_original = in.readString();
        description = in.readString();
        isFavourite = in.readByte() != 0;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle_original() {
        return title_original;
    }

    public void setTitle_original(String title_original) {
        this.title_original = title_original;
    }

    public String getPublisher_original() {
        return publisher_original;
    }

    public void setPublisher_original(String publisher_original) {
        this.publisher_original = publisher_original;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thumbnail);
        dest.writeString(title_original);
        dest.writeString(publisher_original);
        dest.writeString(description);
        dest.writeByte((byte) (isFavourite ? 1 : 0));
    }


}

