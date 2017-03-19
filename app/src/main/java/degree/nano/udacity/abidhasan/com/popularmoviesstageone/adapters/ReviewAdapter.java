package degree.nano.udacity.abidhasan.com.popularmoviesstageone.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.ReviewViewHolder;

/**
 * Created by VutkaBilai on 3/20/17.
 * mail : la4508@gmail.com
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private MovieDetailMVP.ProviedPresenterOps mPresenter;

    public ReviewAdapter(MovieDetailMVP.ProviedPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mPresenter.createReviewViewHolder(parent , viewType);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        mPresenter.bindReviewViewHolder(holder , position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getReviewCount();
    }
}
