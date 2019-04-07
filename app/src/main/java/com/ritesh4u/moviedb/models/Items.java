package com.ritesh4u.moviedb.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.ritesh4u.moviedb.views.activity.MainActivity;

import java.util.Date;

@Entity(tableName = "items")
public class Items implements Comparable<Items> {
    public Items() {
    }

    @PrimaryKey()
    @NonNull
    private int id = 0;

    private String overview;

    private String original_language;

    private String original_title;

    private String video;

    private String title;


    private String poster_path;

    private String backdrop_path;

    private String media_type;

    private String release_date;

    private String vote_average;

    private String popularity;


    private String adult;

    private String vote_count;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    @NonNull
    @Override
    public String toString() {
        return "ClassPojo [overview = " + overview + ", original_language = " + original_language + ", original_title = " + original_title + ", video = " + video + ", title = " + title + ", poster_path = " + poster_path + ", backdrop_path = " + backdrop_path + ", media_type = " + media_type + ", release_date = " + release_date + ", vote_average = " + vote_average + ", popularity = " + popularity + ", id = " + id + ", adult = " + adult + ", vote_count = " + vote_count + "]";
    }

    @Override
    public int compareTo(Items o) {
        if (MainActivity.sortBy == 1) {
            //sorting date according to id
            if (getId() == o.getId()) {
                return 0;
            } else if (getId() < o.getId()) {
                return 1;
            } else {
                return -1;
            }
        } else if (MainActivity.sortBy == 2) {
            //sorting list according to release date
            Date thisDate = MainActivity.getDateObj(getRelease_date());
            Date objDate = MainActivity.getDateObj(o.getRelease_date());
            if (thisDate.getTime() == objDate.getTime()) {
                return 0;
            } else if (thisDate.getTime() < objDate.getTime()) {
                return 1;
            } else {
                return -1;
            }

        } else if (MainActivity.sortBy == 3) {
            //Sorting list in descending order of rating
            float thisAvg = Float.parseFloat(this.getVote_average());
            float objAvg = Float.parseFloat(o.getVote_average());
            if (thisAvg == objAvg) {
                return 0;
            } else if (thisAvg < objAvg) {
                return 1;
            } else {
                return -1;
            }

        } else {
            return 0;
        }
    }
}