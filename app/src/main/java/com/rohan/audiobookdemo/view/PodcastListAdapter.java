package com.rohan.audiobookdemo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.rohan.audiobookdemo.R;
import com.rohan.audiobookdemo.model.PodcastModel;

import java.util.ArrayList;
import java.util.List;

public class PodcastListAdapter extends RecyclerView.Adapter<PodcastListAdapter.ViewHolder> {

    private final List<PodcastModel> podcastList = new ArrayList<>();
    private OnPodcastClickListener onPodcastClickListener;

    public void setOnPodcastClickListener(OnPodcastClickListener onPodcastClickListener) {
        this.onPodcastClickListener = onPodcastClickListener;
    }

    public void setPodcasts(List<PodcastModel> podcasts) {
        podcastList.clear();
        podcastList.addAll(podcasts);
        notifyDataSetChanged();
    }

    public void updatePodcast(int position, PodcastModel podcastModel) {
        podcastList.set(position, podcastModel);
        notifyItemChanged(position);
    }

    public void setFavourite(int position, boolean isFavourite) {
        PodcastModel podcast = podcastList.get(position);
        podcast.setFavourite(isFavourite);
        notifyItemChanged(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PodcastModel podcastModel = podcastList.get(position);
        holder.titleTextView.setText(podcastModel.getTitle_original());
        holder.publisherTextView.setText(podcastModel.getPublisher_original());
        Glide.with(holder.thumbnailImageView.getContext()).load(podcastModel.getThumbnail()).transform(new RoundedCorners(40)).into(holder.thumbnailImageView);

        boolean isFavorite = podcastModel.isFavourite(); // update the isFavorite field
        if (isFavorite) {
            holder.favouriteTextView.setVisibility(View.VISIBLE);
        } else {
            holder.favouriteTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return podcastList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface OnPodcastClickListener {
        void onPodcastClick(int position, PodcastModel podcastModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView titleTextView;
        private final TextView publisherTextView;
        private final ImageView thumbnailImageView;
        private final TextView favouriteTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            publisherTextView = itemView.findViewById(R.id.publisher_text_view);
            thumbnailImageView = itemView.findViewById(R.id.thumbnail_image_view);
            this.favouriteTextView = itemView.findViewById(R.id.favourite_label);

            // Set the click listener to the item view
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            // Call the onPodcastClickListener callback with the clicked podcast object
            int position = getBindingAdapterPosition();
            if (position != RecyclerView.NO_POSITION && onPodcastClickListener != null) {
                PodcastModel podcast = podcastList.get(position);

                onPodcastClickListener.onPodcastClick(position, podcast);
            }
        }

    }
}


