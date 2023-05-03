package com.rohan.audiobookdemo.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PodcastResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("rss")
    private String rss;
    @SerializedName("link")
    private String link;
    @SerializedName("audio")
    private String audio;
    @SerializedName("image")
    private String image;
    @SerializedName("podcast")
    private Podcast podcast;
    @SerializedName("itunes_id")
    private int itunesId;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("pub_date_ms")
    private long pubDateMs;
    @SerializedName("guid_from_rss")
    private String guidFromRss;
    @SerializedName("title_original")
    private String titleOriginal;
    @SerializedName("listennotes_url")
    private String listennotesUrl;
    @SerializedName("audio_length_sec")
    private int audioLengthSec;
    @SerializedName("explicit_content")
    private boolean explicitContent;
    @SerializedName("title_highlighted")
    private String titleHighlighted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    public int getItunesId() {
        return itunesId;
    }

    public void setItunesId(int itunesId) {
        this.itunesId = itunesId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getPubDateMs() {
        return pubDateMs;
    }

    public void setPubDateMs(long pubDateMs) {
        this.pubDateMs = pubDateMs;
    }

    public String getGuidFromRss() {
        return guidFromRss;
    }

    public void setGuidFromRss(String guidFromRss) {
        this.guidFromRss = guidFromRss;
    }

    public String getTitleOriginal() {
        return titleOriginal;
    }

    public void setTitleOriginal(String titleOriginal) {
        this.titleOriginal = titleOriginal;
    }

    public String getListennotesUrl() {
        return listennotesUrl;
    }

    public void setListennotesUrl(String listennotesUrl) {
        this.listennotesUrl = listennotesUrl;
    }

    public int getAudioLengthSec() {
        return audioLengthSec;
    }

    public void setAudioLengthSec(int audioLengthSec) {
        this.audioLengthSec = audioLengthSec;
    }

    public boolean isExplicitContent() {
        return explicitContent;
    }

    public void setExplicitContent(boolean explicitContent) {
        this.explicitContent = explicitContent;
    }

    public String getTitleHighlighted() {
        return titleHighlighted;
    }

    public void setTitleHighlighted(String titleHighlighted) {
        this.titleHighlighted = titleHighlighted;
    }

}

class Podcast {
    @SerializedName("id")
    private final String id;
    @SerializedName("image")
    private final String image;
    @SerializedName("genre_ids")
    private final List<Integer> genreIds;
    @SerializedName("thumbnail")
    private final String thumbnail;
    @SerializedName("listen_score")
    private final int listenScore;
    @SerializedName("title_original")
    private final String titleOriginal;
    @SerializedName("listennotes_url")
    private final String listennotesUrl;
    @SerializedName("title_highlighted")
    private final String titleHighlighted;
    @SerializedName("publisher_original")
    private final String publisherOriginal;
    @SerializedName("publisher_highlighted")
    private final String publisherHighlighted;
    @SerializedName("listen_score_global_rank")
    private final String listenScoreGlobalRank;

    protected Podcast(Parcel in) {
        id = in.readString();
        image = in.readString();
        genreIds = new ArrayList<>();
        in.readList(genreIds, Integer.class.getClassLoader());
        thumbnail = in.readString();
        listenScore = in.readInt();
        titleOriginal = in.readString();
        listennotesUrl = in.readString();
        titleHighlighted = in.readString();
        publisherOriginal = in.readString();
        publisherHighlighted = in.readString();
        listenScoreGlobalRank = in.readString();
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(@NonNull Parcel dest, int flags) {
//        dest.writeString(id);
//        dest.writeString(image);
//        dest.writeList(genreIds);
//        dest.writeString(thumbnail);
//        dest.writeInt(listenScore);
//        dest.writeString(titleOriginal);
//        dest.writeString(listennotesUrl);
//        dest.writeString(titleHighlighted);
//        dest.writeString(publisherOriginal);
//        dest.writeString(publisherHighlighted);
//        dest.writeString(listenScoreGlobalRank);
//    }


    //getters and setters
}


