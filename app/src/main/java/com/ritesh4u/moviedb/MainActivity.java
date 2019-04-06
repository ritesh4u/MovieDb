package com.ritesh4u.moviedb;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;
import com.ritesh4u.moviedb.network.ApiClient;
import com.ritesh4u.moviedb.network.ApiInterface;

import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit client = ApiClient.getClient();

        ApiInterface reqInterface = client.create(ApiInterface.class);
        Call<JsonObject> call=reqInterface.getMovieList();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull  Call<JsonObject> call,@NonNull Response<JsonObject> response) {
                Log.d("TAG", "onResponse: "+ Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call,@NonNull Throwable t) {

            }
        });

    }
}
