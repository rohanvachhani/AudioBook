package com.rohan.audiobookdemo.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rohan.audiobookdemo.R;
import com.rohan.audiobookdemo.model.PodcastModel;
import com.rohan.audiobookdemo.viewmodel.PodcastListViewModel;

import java.lang.reflect.Type;
import java.util.List;

public class PodcastListFragment extends Fragment {

    private PodcastListAdapter adapter;
    private ProgressBar progressBar;
    private PodcastListViewModel viewModel;
    private boolean isLoading = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_podcast_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.podcast_list);
        progressBar = root.findViewById(R.id.progress_bar);

        adapter = new PodcastListAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PodcastListViewModel.class);
        viewModel.getPodcasts().observe(getViewLifecycleOwner(), podcasts -> {
            adapter.setPodcasts(podcasts);
            progressBar.setVisibility(View.GONE);
            isLoading = false;
        });

        adapter.setOnPodcastClickListener(((position, podcastModel) -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("podcast", podcastModel);
            Navigation.findNavController(requireView()).navigate(R.id.action_podcastListFragment_to_podcastDetailFragment, bundle);
        }));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                assert layoutManager != null;
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + 1)) {
                    isLoading = true;
                    viewModel.loadMorePodcasts();
                }
            }
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        // update the list to reflect any changes in favorite status
        adapter.notifyDataSetChanged();
    }

}




