package degree.nano.udacity.abidhasan.com.popularmoviesstageone.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.PopularMoviesMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieViewHolder;

/**
 * Created by abidhasan on 3/5/17.
 */

public class TopRatedMovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private PopularMoviesMVP.ProvidedPresenterOps mPresenter;

    public TopRatedMovieAdapter(PopularMoviesMVP.ProvidedPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mPresenter.createTopRatedViewHolder(parent , viewType);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        mPresenter.bindTopratedViewHolder(holder , position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getTopRatedItemCount();
    }
}
