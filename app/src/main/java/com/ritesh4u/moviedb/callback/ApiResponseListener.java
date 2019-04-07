package com.ritesh4u.moviedb.callback;

import com.ritesh4u.moviedb.models.Items;

import java.util.List;

public interface ApiResponseListener {
    void onMovieListFetched(List<Items> movieList);
}
