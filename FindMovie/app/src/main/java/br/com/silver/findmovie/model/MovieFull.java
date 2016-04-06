package br.com.silver.findmovie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by silver on 06/04/16.
 */
public class MovieFull extends Movie implements Serializable {
    @SerializedName("Rated")
    public String rated;
    @SerializedName("Released")
    public String released;
    @SerializedName("Runtime")
    public String runtime;
    @SerializedName("Genre")
    public String genre;
    @SerializedName("Director")
    public String director;
    @SerializedName("Writer")
    public String writer;
    @SerializedName("Actors")
    public String actors;
    @SerializedName("Plot")
    public String plot;
    @SerializedName("Language")
    public String language;
    @SerializedName("Awards")
    public String awards;
    @SerializedName("Metascore")
    public String metascore;
    public String imdbRating;
    public String imdbVotes;
    @SerializedName("Response")
    public String response;
}
