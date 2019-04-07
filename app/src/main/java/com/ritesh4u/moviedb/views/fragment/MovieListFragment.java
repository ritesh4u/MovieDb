package com.ritesh4u.moviedb.views.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ritesh4u.moviedb.R;
import com.ritesh4u.moviedb.adapter.MovieListadapter;
import com.ritesh4u.moviedb.callback.ApiResponseListener;
import com.ritesh4u.moviedb.models.Items;
import com.ritesh4u.moviedb.views.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ritesh4u.moviedb.AppConstants.SORT_BY_DATE_LATEST;
import static com.ritesh4u.moviedb.AppConstants.SORT_BY_DATE_OLDER;
import static com.ritesh4u.moviedb.AppConstants.SORT_BY_NONE;
import static com.ritesh4u.moviedb.AppConstants.SORT_BY_RATING_HIGH;
import static com.ritesh4u.moviedb.AppConstants.SORT_BY_RATING_LOW;

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
    MovieListadapter movieListadapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ntsTextView = rootView.findViewById(R.id.nts_text_view);
        movieRecyclerView = rootView.findViewById(R.id.movie_lit_recycler_view);
        movieListadapter = new MovieListadapter(new ArrayList<Items>());
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
        } else {
            ntsTextView.setVisibility(View.GONE);

        }
        movieListadapter = new MovieListadapter(movieList);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieRecyclerView.setAdapter(movieListadapter);
        movieListadapter.sortList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.sort_by_none:
                MainActivity.sortBy = SORT_BY_NONE;
                ((MainActivity) Objects.requireNonNull(getContext())).showToast("by none");
                movieListadapter.sortList();
                return true;

            case R.id.sort_by_date_older:
                ((MainActivity) Objects.requireNonNull(getContext())).showToast("by date");
                MainActivity.sortBy = SORT_BY_DATE_OLDER;
                movieListadapter.sortList();
                return true;

            case R.id.sort_by_date_latest:
                ((MainActivity) Objects.requireNonNull(getContext())).showToast("by date");
                MainActivity.sortBy = SORT_BY_DATE_LATEST;
                movieListadapter.sortList();
                return true;
            case R.id.sort_by_rating_low:
                ((MainActivity) Objects.requireNonNull(getContext())).showToast("by rating");
                MainActivity.sortBy = SORT_BY_RATING_LOW;
                movieListadapter.sortList();
                return true;
            case R.id.sort_by_rating_high:
                ((MainActivity) Objects.requireNonNull(getContext())).showToast("by rating");
                MainActivity.sortBy = SORT_BY_RATING_HIGH;
                movieListadapter.sortList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
