package br.com.silver.findmovie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by silver on 02/04/16.
 */
public class Movie implements Serializable {
    @SerializedName("Title")
    public String title;
    @SerializedName("Year")
    public int year;
    public String imdbID;
    @SerializedName("Type")
    public String type;
    @SerializedName("Poster")
    public String poster;
}
