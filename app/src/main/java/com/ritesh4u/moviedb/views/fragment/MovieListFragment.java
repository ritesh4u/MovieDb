package com.ritesh4u.moviedb.views.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ritesh4u.moviedb.R;
import com.ritesh4u.moviedb.adapter.MovieListadapter;
import com.ritesh4u.moviedb.callback.ApiResponseListener;
import com.ritesh4u.moviedb.models.Items;
import com.ritesh4u.moviedb.views.activity.MainActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment implements ApiResponseListener {

    View rootView;

    public MovieListFragment() {
        // Required empty public constructor
    }

    RecyclerView movieRecyclerView;
    TextView ntsTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ntsTextView = rootView.findViewById(R.id.nts_text_view);
        movieRecyclerView = rootView.findViewById(R.id.movie_lit_recycler_view);
        init();
        return rootView;
    }

    private void init() {
        ((MainActivity) getContext()).callMovieListApi(this);
    }

    @Override
    public void onMovieListFetched(List<Items> movieList) {
        if (movieList.size() == 0) {
            ntsTextView.setVisibility(View.VISIBLE);
        }else {
            ntsTextView.setVisibility(View.GONE);

        }
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieRecyclerView.setAdapter(new MovieListadapter(movieList));
    }
}
