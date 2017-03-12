package degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels.MovieDetailResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;

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


        void setProgressDialogMsg(String msg );

        void loadMovieDetail(MovieGridItem movie);
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
    }
}
