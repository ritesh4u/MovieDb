package com.ritesh4u.moviedb.views.activity;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ritesh4u.moviedb.R;
import com.ritesh4u.moviedb.callback.ApiResponseListener;
import com.ritesh4u.moviedb.database.AppDatabase;
import com.ritesh4u.moviedb.models.Items;
import com.ritesh4u.moviedb.network.ApiClient;
import com.ritesh4u.moviedb.network.ApiInterface;
import com.ritesh4u.moviedb.models.MovieListResponse;
import com.ritesh4u.moviedb.views.fragment.MovieListFragment;
import com.ritesh4u.moviedb.views.fragment.MovieDetailsFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    TextView toolbarTitle;
    Toolbar toolbar;
    //1=none 2=by date 3=by rating
    public static int sortBy = 1;
    String title = "";
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getString(R.string.defaultTitle);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        backButton = findViewById(R.id.backButton);
        setSupportActionBar(toolbar);
        toolbarTitle = findViewById(R.id.toolbarTitleTextView);
        //toolbar.inflateMenu(R.menu.sort_menu_list);
        launchMovieListFragment(getString(R.string.defaultTitle));
        setupBackstackListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isNetworkAvailable() || AppDatabase.getDBInstance(this).getItemsDAO().getMovieList().size() > 0) {

            getMenuInflater().inflate(R.menu.sort_menu_list, menu);
            return true;
        }
        return false;

    }

    //launching moview list fragment
    private void launchMovieListFragment(@NonNull String title) {
        setToolbarTitle(title);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MovieListFragment()).commitAllowingStateLoss();
    }

    //launching movie details fragment
    public void launchMovieDetailsFragment(@NonNull Items moviewDetails) {
        setToolbarTitle(moviewDetails.getTitle());
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new MovieDetailsFragment().setMovieDetails(moviewDetails))
                .addToBackStack(moviewDetails.getTitle()).commitAllowingStateLoss();
    }

    //Return true if network is available
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }

        NetworkInfo.State state = info.getState();

        return (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING);

    }

    //fetching movie list from movieDB api
    public void callMovieListApi(final ApiResponseListener callback) {
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "No network ofline Data showing", Toast.LENGTH_SHORT).show();
            callback.onMovieListFetched(AppDatabase.getDBInstance(getApplicationContext()).getItemsDAO().getMovieList());
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading ");
        progressDialog.setCancelable(false);
        Retrofit client = ApiClient.getClient();

        progressDialog.show();
        ApiInterface reqInterface = client.create(ApiInterface.class);
        Call<JsonObject> call = reqInterface.getMovieList("1", "en-US");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {

                try {

                    if (response.body() != null) {
                        //converting json response to POJO
                        MovieListResponse listResponse = new Gson().fromJson(response.body(), MovieListResponse.class);
                        setToolbarTitle(listResponse.getName());
                        title = listResponse.getName();
                        //storing response in local database
                        AppDatabase.getDBInstance(getApplicationContext()).getItemsDAO().insertMovieInfo(listResponse.getItems());

                        progressDialog.dismiss();
                        callback.onMovieListFetched(listResponse.getItems());
                        showToast("size = " + AppDatabase.getDBInstance(getApplicationContext()).getItemsDAO().getMovieList().size());

                    } else {
                        showToast("Something went wrong ");
                    }
                } catch (Exception e) {
                    showToast("Something went wrong ");
                    Log.d(TAG, "onResponse: " + e.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    //for showing Toast
    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void setToolbarTitle(String title) {
        if (title != null)
            toolbarTitle.setText(title);
    }

    public static Date getDateObj(String yyyyMMdd) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private void setupBackstackListener() {
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fragManager = getSupportFragmentManager();
                if (fragManager.getBackStackEntryCount() == 0) {
                    setToolbarTitle(title);
                    backButton.setVisibility(View.GONE);

                } else {
                    setToolbarTitle(fragManager.getBackStackEntryAt(fragManager.getBackStackEntryCount() - 1).getName());
                    backButton.setVisibility(View.VISIBLE);
                }

            }
        });
        backButton.setOnClickListener(v -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                getSupportFragmentManager().popBackStack();
        });
    }
}
