package com.ritesh4u.moviedb.views.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritesh4u.moviedb.AppConstants;
import com.ritesh4u.moviedb.R;
import com.ritesh4u.moviedb.models.Items;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment implements AppConstants {


    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    Items moviewDetails;

    public MovieDetailsFragment setMovieDetails(Items movieDetails) {
        this.moviewDetails = movieDetails;

        return this;
    }

    View rootView;
    ImageView moviePosterImageView;
    TextView popularity, voteCount, voteAverage, language, overview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_moview_details, container, false);
        init();
        return rootView;
    }

    private void init() {
        moviePosterImageView = rootView.findViewById(R.id.movie_poster_imageView);
        popularity = rootView.findViewById(R.id.popularity);
        voteAverage = rootView.findViewById(R.id.voteAverage);
        voteCount = rootView.findViewById(R.id.voteCount);
        language = rootView.findViewById(R.id.language);
        overview = rootView.findViewById(R.id.overview);


        Picasso.get().load(BASE_IMAGE_URL + moviewDetails.getPoster_path()).fit().into(moviePosterImageView);
        popularity.setText(moviewDetails.getPopularity());
        voteAverage.setText(moviewDetails.getVote_average());
        voteCount.setText(moviewDetails.getVote_count());
        language.setText(moviewDetails.getOriginal_language());
        overview.setText(moviewDetails.getOverview());
    }

}
