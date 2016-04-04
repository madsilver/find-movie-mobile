package br.com.silver.findmovie.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.silver.findmovie.R;
import br.com.silver.findmovie.model.Movie;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by silver on 02/04/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private Movie[] mMovies;
    private OnClickMovieListener mListener;

    public MovieAdapter(Context ctx, Movie[] movies){
        mContext = ctx;
        mMovies = movies;
    }

    public void setMovieClickListener(OnClickMovieListener l){
        mListener = l;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        v.setTag(mvh);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    MovieViewHolder mvh = (MovieViewHolder) v.getTag();
                    int position = mvh.getAdapterPosition();
                    mListener.onMovieClick(v, position, mMovies[position]);
                }
            }
        });
        return mvh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovies[position];
        Picasso.with(mContext).load(movie.poster).into(holder.imgMovie);
        holder.txtSinopse.setText(movie.title);
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.length : 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgMovie)
        public ImageView imgMovie;
        @Bind(R.id.txtSinopse)
        public TextView txtSinopse;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickMovieListener{
        void onMovieClick(View v, int position, Movie movie);
    }
}
