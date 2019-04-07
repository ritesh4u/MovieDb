package com.ritesh4u.moviedb.views.activity;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ritesh4u.moviedb.R;
import com.ritesh4u.moviedb.database.AppDatabase;
import com.ritesh4u.moviedb.network.ApiClient;
import com.ritesh4u.moviedb.network.ApiInterface;
import com.ritesh4u.moviedb.models.MovieListResponse;
import com.ritesh4u.moviedb.views.fragment.MovieListFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarTitle = findViewById(R.id.toolbarTitleTextView);

        if (isNetworkAvailable()) {
            //  callMovieListApi();
            launchMovieListFragment(getString(R.string.defaultTitle));
        } else {
            Toast.makeText(this, "No network ofline Data showing", Toast.LENGTH_SHORT).show();
//            ((TextView) findViewById(R.id.textView)).setText(AppDatabase.getDBInstance(getApplicationContext()).getItemsDAO().getMovieList().toString());


        }

    }

    private void launchMovieListFragment(@NonNull String title) {
        setToolbarTitle(title);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MovieListFragment()).commitAllowingStateLoss();
    }

    private void launchMovieDetailsFragment(@NonNull String title) {
        setToolbarTitle(title);
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new MovieListFragment())
                .addToBackStack(title).commitAllowingStateLoss();
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
    private void callMovieListApi() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading ");
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
                        //storing response in local database
                        AppDatabase.getDBInstance(getApplicationContext()).getItemsDAO().insertMovieInfo(listResponse.getItems());

                        progressDialog.dismiss();
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
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void setToolbarTitle(String title) {
        if (title != null)
            toolbarTitle.setText(title);
    }
}
