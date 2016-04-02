package br.com.silver.findmovie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by silver on 02/04/16.
 */
public class Search implements Serializable {

    @SerializedName("Search")
    public Movie[] search;
    public int totalResults;
    @SerializedName("Response")
    public boolean response;
}
