package com.rohan.audiobookdemo.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.gson.Gson;
import com.rohan.audiobookdemo.R;
import com.rohan.audiobookdemo.model.PodcastModel;

import java.util.Objects;

public class PodcastDetailFragment extends Fragment {

    private final boolean isFavorite = false;
    private PodcastModel podcastModel;
    private Button favouriteButton;
    private ImageView coverImageView;
    private TextView titleTextView;
    private TextView publisherTextView;
    private TextView descriptionTextView;


    public PodcastDetailFragment() {
        // Required empty public constructor
    }

    public static PodcastDetailFragment newInstance(PodcastModel podcast) {
        PodcastDetailFragment fragment = new PodcastDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("podcast", podcast);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            podcastModel = getArguments().getParcelable("podcast");
        }
    }


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_podcast_detail, container, false);

        ImageView imageView = view.findViewById(R.id.back_button_image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });


        titleTextView = view.findViewById(R.id.podcast_title);
        publisherTextView = view.findViewById(R.id.podcast_publisher);
        descriptionTextView = view.findViewById(R.id.podcast_description);
        coverImageView = view.findViewById(R.id.podcast_image);
        favouriteButton = view.findViewById(R.id.favorite_button);


        if (podcastModel != null) {
            titleTextView.setText(podcastModel.getTitle_original());
            publisherTextView.setText(podcastModel.getPublisher_original());

            descriptionTextView.setText(HtmlCompat.fromHtml(podcastModel.getDescription(), HtmlCompat.FROM_HTML_MODE_LEGACY));

            Glide.with(requireContext())
                    .load(podcastModel.getThumbnail())
                    .transform(new RoundedCorners(40))
                    .into(coverImageView);
        }

        updateFavoriteButton();
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite();
            }
        });

        return view;
    }

    private void updateFavoriteButton() {
        if (podcastModel.isFavourite()) {
            favouriteButton.setText(R.string.favourited);
        } else {
            favouriteButton.setText(R.string.favourite);
        }
    }

    private void toggleFavorite() {
        podcastModel.setFavourite(!podcastModel.isFavourite());
        updateFavoriteButton();

    }


}


