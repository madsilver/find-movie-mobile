package br.com.silver.findmovie;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import br.com.silver.findmovie.databinding.ActivitySinopseBinding;
import br.com.silver.findmovie.model.MovieFull;
import br.com.silver.findmovie.utils.SearchHttp;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SinopseActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.imgSinopse)
    ImageView imgSinose;
    @Bind(R.id.imgReturn)
    ImageView imgReturn;

    ActivitySinopseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sinopse);

        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        String movieId = extras.getString("movie_id");
        imgReturn.setOnClickListener(this);
        loadSinopse(movieId);
    }

    private void loadSinopse(String movieId){
        try {
            MovieFull movieFull = new MovieFullDownloadTask().execute(movieId).get();
            Picasso.with(this)
                    .load(movieFull.poster)
                    .into(imgSinose);

            binding.setMovie(movieFull);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        ActivityCompat.finishAfterTransition(this);
    }

    public class MovieFullDownloadTask extends AsyncTask<String, Void, MovieFull> {
        @Override
        protected MovieFull doInBackground(String... params) {
            MovieFull movieFull = SearchHttp.getMovieFullFromServer(SearchHttp.PARAM_IMDB, params[0]);
            return movieFull;
        }
    }
}
