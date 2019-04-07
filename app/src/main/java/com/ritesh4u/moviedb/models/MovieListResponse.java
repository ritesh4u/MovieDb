package com.ritesh4u.moviedb.models;

import android.support.annotation.NonNull;

import java.util.List;

public class MovieListResponse {
    private String item_count;

    private String name;

    private String description;

    private String favorite_count;

    private String id;

    private String created_by;

    private List<Items> items;

    private String iso_639_1;

    private String poster_path;

    public String getItem_count ()
    {
        return item_count;
    }

    public void setItem_count (String item_count)
    {
        this.item_count = item_count;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getFavorite_count ()
    {
        return favorite_count;
    }

    public void setFavorite_count (String favorite_count)
    {
        this.favorite_count = favorite_count;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCreated_by ()
    {
        return created_by;
    }

    public void setCreated_by (String created_by)
    {
        this.created_by = created_by;
    }

    public List<Items> getItems ()
    {
        return items;
    }

    public void setItems (List<Items> items)
    {
        this.items = items;
    }

    public String getIso_639_1 ()
    {
        return iso_639_1;
    }

    public void setIso_639_1 (String iso_639_1)
    {
        this.iso_639_1 = iso_639_1;
    }

    public String getPoster_path ()
    {
        return poster_path;
    }

    public void setPoster_path (String poster_path)
    {
        this.poster_path = poster_path;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "ClassPojo [item_count = "+item_count+", name = "+name+", description = "+description+", favorite_count = "+favorite_count+", id = "+id+", created_by = "+created_by+", items = "+items+", iso_639_1 = "+iso_639_1+", poster_path = "+poster_path+"]";
    }

}
