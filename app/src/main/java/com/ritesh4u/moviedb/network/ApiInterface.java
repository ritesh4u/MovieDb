package com.ritesh4u.moviedb.network;

import com.google.gson.JsonObject;
import com.ritesh4u.moviedb.AppConstants;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.ritesh4u.moviedb.AppConstants.API_KEY;

public interface ApiInterface {
    @GET("list/{list_id}?api_key=" + API_KEY )
    Call<JsonObject> getMovieList(@Path(value = "list_id", encoded = true) String list_id,
                                  @Query(value = "language", encoded = true) String language);
}
