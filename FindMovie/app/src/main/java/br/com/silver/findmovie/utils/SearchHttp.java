package br.com.silver.findmovie.utils;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.concurrent.TimeUnit;

import br.com.silver.findmovie.model.Search;

/**
 * Created by silver on 02/04/16.
 */
public class SearchHttp {

    public static final String BASE_URL = "http://www.omdbapi.com/";
    public static final String PARAM_TITLE = "s";
    public static final String PARAM_IMDB = "i";

    /**
     * Executa a consulta no servidor
     * @param s Nome do filme
     * @return Um objeto Search
     */
    public static Search getMovieFromServer(String param, String s){
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(5, TimeUnit.SECONDS);
        client.setConnectTimeout(10, TimeUnit.SECONDS);

        Request request = new Request.Builder()
                .url(String.format("%s?%s=%s",BASE_URL,param,s))
                .build();
        try{
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            Gson gson = new Gson();
            return gson.fromJson(json, Search.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
