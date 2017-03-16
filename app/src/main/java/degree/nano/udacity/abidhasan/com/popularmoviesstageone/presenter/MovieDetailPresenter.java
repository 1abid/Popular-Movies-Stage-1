package degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ToastMaker;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.PopularMoviesMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.adapters.TrailerAdpter;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.interfaces.OnItemClickListener;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel.MovieTrailer;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.API_URLS;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.GridSpacingItemDecoration;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieTrailerViewHolder;

/**
 * Created by abidhasan on 3/6/17.
 */

public class MovieDetailPresenter implements MovieDetailMVP.ProviedPresenterOps, MovieDetailMVP.RequiredPresenterOps {

    private WeakReference<MovieDetailMVP.RequiredViewOps> mView;

    private MovieDetailMVP.ProvidedModelOps mModel;

    private ArrayList<MovieTrailer> trailers;

    public MovieDetailPresenter(MovieDetailMVP.RequiredViewOps mView) {
        this.mView = new WeakReference<MovieDetailMVP.RequiredViewOps>(mView);

        trailers = new ArrayList<>();
    }

    /**
     * called by activity every time during
     * setting up MVP , only called once
     *
     * @param mModel
     */
    public void setModel(MovieDetailMVP.ProvidedModelOps mModel) {
        this.mModel = mModel;
    }

    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        //view should be null every time onDestroy is called
        mView = null;

        //inform model about the event
        mModel.onDestroy(isChangingConfigurations);

        //activity destroyed
        if (!isChangingConfigurations) {
            mModel = null;
        }
    }

    @Override
    public void onConfigurationChanged(MovieDetailMVP.RequiredViewOps view) {
        setView(view);
    }


    /**
     * called by {@link degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieDetailActivity}
     *
     * @param view
     */
    @Override
    public void setView(MovieDetailMVP.RequiredViewOps view) {
        this.mView = new WeakReference<MovieDetailMVP.RequiredViewOps>(view);
    }


    @Override
    public void setProgressDialogMsg(String msg) {
        getView().getProgressDialog().setMessage(msg);
    }

    @Override
    public void loadMovieDetail(MovieGridItem movie) {

        setProgressDialogMsg("Loading...");
        getView().showProgressDialog();

        getView().getTitleTv().setText(movie.getMovieTitle());
        getView().getRatingTv().setText(movie.getMovieAvg_vote());
        getView().getRelaseDateTv().setText("Release date : " + movie.getMovieReleaseDate());
        getView().getOverViewTv().setText(movie.getMovieOverView());
        loadImage(getView().getBackdropImageView(), movie.getMovieBackDropPath());
        loadImage(getView().getPosterImageView(), movie.getMoviePosterPath());

        if (trailers.size() == 0)
            mModel.getMovieTrailers(getView().getmovieId());
    }

    @Override
    public void onTrailerDownload(ArrayList<MovieTrailer> trailerArrayList) {

        trailers = trailerArrayList;

        InitializeRv(getView().getTrailerRV());
    }

    private void InitializeRv(RecyclerView trailerRV) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivityContext(), LinearLayoutManager.HORIZONTAL, false);
        trailerRV.setLayoutManager(layoutManager);
        trailerRV.setAdapter(new TrailerAdpter(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(trailerRV.getContext(),
                layoutManager.getOrientation());
        trailerRV.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public MovieTrailerViewHolder createTrailerViewHolder(ViewGroup parent, int viewType) {

        MovieTrailerViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.movie_trailer_card, parent, false);

        viewHolder = new MovieTrailerViewHolder(v);

        return viewHolder;
    }

    @Override
    public void bindTrailerViewHolder(MovieTrailerViewHolder holder, int position) {
        MovieTrailer trailer = trailers.get(position);
        bindTrailerData(holder , trailer);
    }

    private void bindTrailerData(MovieTrailerViewHolder holder, final MovieTrailer trailer) {
        holder.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                getView().showToast(ToastMaker.makeToast(getActivityContext() , trailer.getTrailerTitle()));
            }
        });
    }

    @Override
    public int getTrailerCount() {
        return trailers.size();
    }

    /**
     * load image into a
     * imageView with glide
     *
     * @param imageView
     * @param imagePath
     */
    private void loadImage(ImageView imageView, String imagePath) {


        ViewTarget target = new ViewTarget<ImageView, GlideDrawable>(imageView) {

            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImageDrawable(resource.getCurrent());
            }
        };


        Glide.with(getView().getAppContext())
                .load(API_URLS.IMAGE_PATH + imagePath).crossFade().fitCenter()
                .into(target);
    }


    @Override
    public Context getAppContext() {
        try {

            return getView().getAppContext();

        } catch (NullPointerException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public Context getActivityContext() {
        try {

            return getView().getActivityContext();

        } catch (NullPointerException e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * return the view reference.
     * could throw nullpinter exception
     * if view is null
     *
     * @return {@link MovieDetailMVP.RequiredViewOps}
     * @throws NullPointerException
     */
    public MovieDetailMVP.RequiredViewOps getView() throws NullPointerException {

        if (mView != null)
            return mView.get();
        else
            throw new NullPointerException("view is unavailable");

    }
}
