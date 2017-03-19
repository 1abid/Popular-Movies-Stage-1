package degree.nano.udacity.abidhasan.com.popularmoviesstageone.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieTrailerViewHolder;

/**
 * Created by VutkaBilai on 3/17/17.
 * mail : la4508@gmail.com
 */

public class TrailerAdpter extends RecyclerView.Adapter<MovieTrailerViewHolder> {

    MovieDetailMVP.ProviedPresenterOps mPresnter;

    public TrailerAdpter(MovieDetailMVP.ProviedPresenterOps mPresnter) {
        this.mPresnter = mPresnter;
    }

    @Override
    public MovieTrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mPresnter.createTrailerViewHolder(parent , viewType);
    }

    @Override
    public void onBindViewHolder(MovieTrailerViewHolder holder, int position) {
        mPresnter.bindTrailerViewHolder(holder , position);
    }

    @Override
    public int getItemCount() {
        return mPresnter.getTrailerCount();
    }
}
