package degree.nano.udacity.abidhasan.com.popularmoviesstageone.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.PopularMoviesMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieViewHolder;

/**
 * Created by VutkaBilai on 3/1/17.
 * mail : la4508@gmail.com
 */

public class TopRatedMovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private PopularMoviesMVP.ProvidedPresenterOps mPresenter;

    public TopRatedMovieAdapter(PopularMoviesMVP.ProvidedPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mPresenter.createTopMovieViewHolder(parent , viewType);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        mPresenter.bindTopMovieViewHolder(holder , position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getTopItemCount();
    }
}
