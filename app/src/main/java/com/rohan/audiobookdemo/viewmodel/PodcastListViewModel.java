package com.rohan.audiobookdemo.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rohan.audiobookdemo.model.PodcastModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PodcastListViewModel extends ViewModel {

    private final MutableLiveData<List<PodcastModel>> podcasts = new MutableLiveData<List<PodcastModel>>();
    private final List<PodcastModel> podcastList = new ArrayList<>();

    private int page = 1;

    public PodcastListViewModel() {
        loadMorePodcasts();
    }

    public LiveData<List<PodcastModel>> getPodcasts() {
        return podcasts;
    }

    public void loadMorePodcasts() {
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("listen-api-test.listennotes.com")
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("search")
                .addQueryParameter("page", Integer.toString(page))
                .addQueryParameter("q", "star wars")
                .addQueryParameter("sort_by_date", "0")
                .addQueryParameter("type", "episode")
                .addQueryParameter("offset", "0")
                .addQueryParameter("len_min", "10")
                .addQueryParameter("len_max", "30")
                .addQueryParameter("genre_ids", "68,82")
                .addQueryParameter("published_before", "1580172454000")
                .addQueryParameter("published_after", "0")
                .addQueryParameter("only_in", "title,description")
                .addQueryParameter("language", "English")
                .addQueryParameter("safe_mode", "0")
                .addQueryParameter("unique_podcasts", "0")
                .addQueryParameter("page_size", "20")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {


            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();

                    try {
                        JSONObject jsonResponse = new JSONObject(responseString);
                        JSONArray jsonArray = jsonResponse.getJSONArray("results");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonEpisode = jsonArray.getJSONObject(i);

                            String title = jsonEpisode.getString("title_original");
                            String publisher = jsonEpisode.getJSONObject("podcast").getString("publisher_original");
                            String thumbnail = jsonEpisode.getJSONObject("podcast").getString("thumbnail");
                            String description = jsonEpisode.getString("description_original");
                            // create a new Podcast object with the parsed values
                            PodcastModel podcast = new PodcastModel(title, publisher, thumbnail, description);
                            podcastList.add(podcast);

                        }
                        podcasts.postValue(podcastList);
                        page++;
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("PodcastListViewModel", "Failed to fetch podcasts", e);
            }
        });


    }
}


