package br.com.silver.findmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SinopseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopse);

        Bundle extras = getIntent().getExtras();
        String movieId = extras.getString("movie_id");

    }
}
