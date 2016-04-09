package br.com.silver.findmovie;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.silver.findmovie.model.Movie;
import br.com.silver.findmovie.model.Search;
import br.com.silver.findmovie.utils.MovieAdapter;
import br.com.silver.findmovie.utils.SearchHttp;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by silver on 03/04/16.
 */
public class TitleMovieFragment extends Fragment implements MovieAdapter.OnClickMovieListener{
    public static final String TAG = "Title";
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipe;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.txtTotalResults)
    TextView mTextResult;

    private Movie[] mMovies;
    private MovieDownloadTask mTask;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_title_movie, container, false);
        ButterKnife.bind(this, v);

        mRecyclerView.setTag("web");
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);

        return v;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void refreshList(){
        MovieAdapter adapter = new MovieAdapter(getActivity(), mMovies);
        adapter.setMovieClickListener(this);
        mRecyclerView.setAdapter(adapter);

        mTextResult.setText(getResources().getString(R.string.title_results, mMovies.length));

    }

    private void showProgress(){
        mSwipe.post(new Runnable() {
            @Override
            public void run() {
                mSwipe.setRefreshing(true);
            }
        });
    }

    public void search(String s){
        mTask = new MovieDownloadTask();
        mTask.execute(s);
    }

    @Override
    public void onMovieClick(View v, int position, Movie movie) {
        Activity act = getActivity();
        if(act instanceof  OnClickTitleMovie){
            OnClickTitleMovie listener = (OnClickTitleMovie) act;
            listener.clickTitleMovie(movie);
        }
    }

    /**
     * Interface
     */
    public interface OnClickTitleMovie {
        void clickTitleMovie(Movie movie);
    }

    /**
     * AsyncTask
     */
    public class MovieDownloadTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            showProgress();
        }

        @Override
        protected Movie[] doInBackground(String... params) {
            Search search = SearchHttp.getMoviesFromServer(SearchHttp.PARAM_TITLE, params[0]);
            if(search != null)
                return  search.search;
            return new Movie[0];
        }

        @Override
        protected void onPostExecute(Movie[] movies){
            super.onPostExecute(movies);
            mSwipe.setRefreshing(false);
            mTask = null;
            if(movies != null){
                mMovies = movies;
                refreshList();
            }
        }
    }
}
