package com.ritesh4u.moviedb.network;

import com.google.gson.JsonObject;
import com.ritesh4u.moviedb.AppConstants;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.ritesh4u.moviedb.AppConstants.API_KEY;

public interface ApiInterface  {
    @GET("list/1?api_key=" + API_KEY + "&language=en-US")
    Call<JsonObject> getMovieList();
}
