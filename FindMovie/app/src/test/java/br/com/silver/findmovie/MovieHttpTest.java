package br.com.silver.findmovie;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import br.com.silver.findmovie.model.Movie;
import br.com.silver.findmovie.model.MovieFull;
import br.com.silver.findmovie.model.Search;
import br.com.silver.findmovie.utils.SearchHttp;

/**
 * Created by silver on 02/04/16.
 */
@SmallTest
public class MovieHttpTest {

    @Test
    public void testDownloadMoviesByTitle(){
        String s = "Finding Nemo";
        Search search = SearchHttp.getMoviesFromServer(SearchHttp.PARAM_TITLE, s);
        Movie[] movies = search.search;
        assertThat(movies, notNullValue());
        assertThat(movies.length, is(not(lessThanOrEqualTo(0))));
    }

    @Test
    public void testDownloadMovieById(){
        String s = "tt0266543";
        MovieFull movie = SearchHttp.getMovieFullFromServer(SearchHttp.PARAM_IMDB, s);
        assertThat(movie, notNullValue());
    }
}
