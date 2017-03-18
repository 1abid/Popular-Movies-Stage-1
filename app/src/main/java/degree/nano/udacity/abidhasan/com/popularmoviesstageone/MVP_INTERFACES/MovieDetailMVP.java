package degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels.MovieDetailResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel.MovieTrailer;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel.MovieTrailerResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieTrailerViewHolder;

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
        String getmovieId();

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

        void onTrailerDownload(ArrayList<MovieTrailer> trailerArrayList);


        MovieTrailerViewHolder createTrailerViewHolder(ViewGroup parent , int viewType);
        void bindTrailerViewHolder(MovieTrailerViewHolder holder , int position);
        int getTrailerCount();

    }


    /**
     * required Presenter operation available
     * to model (model -> presenter)
     */
    public interface RequiredPresenterOps{
        Context getAppContext();
        Context getActivityContext();
    }


    /**
     * Operations offered to model to communicate with presenter
     * Handles all data business logic
     * (presenter -> model)
     */
    public interface ProvidedModelOps{
        void onDestroy(boolean isConfigurationChanging);

        MovieDetailResponse loadMovieDetail(int movieId);

        List<MovieTrailer> getMovieTrailers(String movieId);

        int getTrailerItemCount();
    }
}
