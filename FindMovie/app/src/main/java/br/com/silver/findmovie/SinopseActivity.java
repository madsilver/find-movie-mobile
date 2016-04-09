package br.com.silver.findmovie;

import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import br.com.silver.findmovie.model.MovieFull;
import br.com.silver.findmovie.utils.SearchHttp;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SinopseActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.imgSinopse)
    ImageView imgSinose;
    @Bind(R.id.txtTitleSinopse)
    TextView titleSinopse;
    @Bind(R.id.imgReturn)
    ImageView imgReturn;
    @Bind(R.id.txtDescriptionSinopse)
    TextView textDescriptionSinopse;
    @Bind(R.id.txtActors)
    TextView textActors;
    @Bind(R.id.txtDirector)
    TextView textDirector;
    @Bind(R.id.txtWriter)
    TextView textWriter;
    @Bind(R.id.txtReleased)
    TextView textReleased;
    @Bind(R.id.txtRuntime)
    TextView textRuntime;
    @Bind(R.id.txtGenre)
    TextView textGenre;
    @Bind(R.id.txtMetascore)
    TextView textMetascore;
    @Bind(R.id.txtAwards)
    TextView textAwards;
    @Bind(R.id.txtCountry)
    TextView textCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopse);
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

            titleSinopse.setText(movieFull.title);
            textDescriptionSinopse.setText(movieFull.plot);
            textActors.setText(movieFull.actors);
            textDirector.setText(movieFull.director);
            textWriter.setText(movieFull.writer);
            textReleased.setText(movieFull.released);
            textRuntime.setText(movieFull.runtime);
            textGenre.setText(movieFull.genre);
            textMetascore.setText(movieFull.metascore);
            textAwards.setText(movieFull.awards);
            textCountry.setText(movieFull.country);

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
