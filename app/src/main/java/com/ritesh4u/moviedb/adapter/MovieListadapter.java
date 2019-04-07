package com.ritesh4u.moviedb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritesh4u.moviedb.R;
import com.ritesh4u.moviedb.models.Items;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import static com.ritesh4u.moviedb.AppConstants.BASE_IMAGE_URL;
import static com.ritesh4u.moviedb.AppConstants.SORT_BY_DATE;
import static com.ritesh4u.moviedb.AppConstants.SORT_BY_NONE;
import static com.ritesh4u.moviedb.AppConstants.SORT_BY_RATING;

public class MovieListadapter extends RecyclerView.Adapter<MovieListadapter.ViewHolder> {

    List<Items> movieList;
    Context mContext;

    public MovieListadapter(List<Items> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_movie_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Items currentItem = movieList.get(i);
        Picasso.get().load(BASE_IMAGE_URL + currentItem.getPoster_path()).into(viewHolder.movieImage);
        viewHolder.movieName.setText(currentItem.getTitle());
        viewHolder.moviePopularity.setText(currentItem.getVote_average());
        viewHolder.releaseDate.setText(currentItem.getRelease_date());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieName, moviePopularity, releaseDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieName = itemView.findViewById(R.id.movie_name_text_view);
            moviePopularity = itemView.findViewById(R.id.popularity_text_view);
            releaseDate = itemView.findViewById(R.id.release_date_text_view);
        }
    }

    // 1=by id (none) 2= by date 3=rating
    public void sortList() {
        if (movieList != null && movieList.size() > 0) {

            Collections.sort(movieList);
            notifyDataSetChanged();
        }
    }
}
