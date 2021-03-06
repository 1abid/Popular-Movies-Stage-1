package degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ThumbnailListener;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels.MovieDetailResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieReviewsModel.Reviews;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel.MovieTrailer;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel.MovieTrailerResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieTrailerViewHolder;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.ReviewViewHolder;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.YoutubeVideoFrgment;

/**
 * Created by VutkaBilai on 3/5/17.
 * mail : la4508@gmail.com
 */

public class MovieDetailMVP {


    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     * (presenter -> view)
     */
    public interface RequiredViewOps{

        Context getAppContext();
        Context getActivityContext();

        ProgressDialog getProgressDialog();
        void showProgressDialog();
        void hideProgressDialog();
        void showToast(Toast toast);
        void showAlert(AlertDialog alertDialog);

        ImageView getBackdropImageView();
        ImageView getPosterImageView();
        TextView getTitleTv();
        TextView getRatingTv();
        TextView getRelaseDateTv();
        TextView getOverViewTv();
        RecyclerView getTrailerRV();
        RecyclerView getReviewRv();
        FloatingActionButton getFab();
        MovieGridItem getSelectedMovie();
        String getmovieId();


        //youtube video playing
        YoutubeVideoFrgment getVideoContainer();
        View getVideoBox();
        View getCloseButton();
        void playVideo(String videKey);

        ThumbnailListener getThumbListener();
        Map<YouTubeThumbnailView, YouTubeThumbnailLoader> getThumbMap();

    }



    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     * (presenter -> view)
     */
    public interface ProviedPresenterOps{
        void onDestroy(boolean isChangingConfigurations);
        void onConfigurationChanged(RequiredViewOps view);
        void setView(RequiredViewOps view);


        void checkYourYoutubeApi();
        void setProgressDialogMsg(String msg );

        void loadMovieDetail(MovieGridItem movie);

        void onTrailerDownload(ArrayList<MovieTrailer> trailerArrayList , ArrayList<Reviews> reviewsArrayList);



        MovieTrailerViewHolder createTrailerViewHolder(ViewGroup parent , int viewType);
        void bindTrailerViewHolder(MovieTrailerViewHolder holder , int position);
        int getTrailerCount();

        ReviewViewHolder createReviewViewHolder(ViewGroup parent , int viewType);
        void bindReviewViewHolder(ReviewViewHolder holder , int position);
        int getReviewCount();


        void onClickClose();
        void onMovieFbClick();
        void configLayout();

    }


    /**
     * required Presenter operation available
     * to model (model -> presenter)
     */
    public interface RequiredPresenterOps{
        Context getAppContext();
        Context getActivityContext();
        void onMovieLiked(MovieGridItem item);
        public void changeFABState(boolean faved);
    }


    /**
     * Operations offered to model to communicate with presenter
     * Handles all data business logic
     * (presenter -> model)
     */
    public interface ProvidedModelOps{
        void onDestroy(boolean isConfigurationChanging);

        MovieDetailResponse loadMovieDetail(int movieId);

        void getMovieTrailers(String movieId);
        void getMovieReviews(String movieId);

        int getTrailerItemCount();


        void insertMovieToFavoriteList(MovieGridItem item);
        boolean isAlreadyFaved(MovieGridItem item);

        ArrayList<MovieTrailer> getTrailers(int movieId);
        ArrayList<Reviews> getReviews(int movieId);
    }
}
