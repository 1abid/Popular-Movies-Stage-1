package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ActivityFragmentStatemaintainer;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetailModel;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter.MovieDetailPresenter;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailMVP.RequiredViewOps {

    private MovieDetailMVP.ProviedPresenterOps mPresenter;
    private ProgressDialog pDialog;
    private Toolbar mToolbar;

    private ImageView backDropIv, posterIv;
    public TextView titleTv , ratingTv , releaseDateTv , overViewTv;

    // Responsible to maintain the object's integrity
    // during configurations change
    private ActivityFragmentStatemaintainer mStateMaintainer
            = new ActivityFragmentStatemaintainer(getFragmentManager(), getClass().getName());

    private MovieGridItem selectedMovieItem;
    private String toolbarTitle , moviegridItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);

        Bundle extras = getIntent().getExtras();

        selectedMovieItem = getSelectedMovieItem(extras);

        Log.d(getClass().getSimpleName() , "selected movie "+ selectedMovieItem.getMovieId());

        setUpViews();

        showMovieDetails();
    }

    /**
     * tell presenter to fetch
     * movie detail for the movie
     */
    private void showMovieDetails() {
        mPresenter.loadMovieDetail(selectedMovieItem);
    }

    private void setUpViews() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        backDropIv = (ImageView) findViewById(R.id.movie_backdrop);
        posterIv = (ImageView) findViewById(R.id.movie_detail_poster_iv);
        titleTv = (TextView) findViewById(R.id.movie_title_tv_detail);
        ratingTv = (TextView) findViewById(R.id.vote_avg_tv_detail);
        releaseDateTv = (TextView) findViewById(R.id.releaseDate_tv);
        overViewTv = (TextView) findViewById(R.id.overView_tv);

        setUpMVP();

        if(pDialog == null)
            pDialog = mPresenter.createProgressDialog();

        /*if(mToolbar !=null)
            setSupportActionBar(mToolbar);

        if(selectedMovieItem != null)
            getSupportActionBar().setTitle(selectedMovieItem.getMovieTitle());*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy(isChangingConfigurations());
    }


    /**
     * Setup Model View Presenter pattern.
     * Use a {@link ActivityFragmentStatemaintainer} to maintain the
     * Presenter and Model instances between configuration changes.
     * Could be done differently,
     * using a dependency injection for example.
     */
    private void setUpMVP(){

        if(mStateMaintainer.isFirstTimeIn()){

            //create the presenter
            MovieDetailPresenter presenter = new MovieDetailPresenter(this);

            //create the model
            MovieDetailModel model = new MovieDetailModel(presenter);

            //set model for presenter
            presenter.setModel(model);

            //save presenter
            /**
             * save object {@link ActivityFragmentStatemaintainer}
             */
            mStateMaintainer.put(presenter);
            mStateMaintainer.put(model);

            //set the presenter as a interface
            //to limit communication with it
            mPresenter = presenter;
        }
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return MovieDetailActivity.this;
    }

    @Override
    public ProgressDialog getProgressDialog() {

        if (pDialog == null)
            return mPresenter.createProgressDialog() ;

        return pDialog;
    }

    @Override
    public void showProgressDialog() {
        pDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        pDialog.hide();
    }

    @Override
    public void showToast(Toast toast) {
        toast.show();
    }

    @Override
    public void showAlert(AlertDialog alertDialog) {
        alertDialog.show();
    }

    @Override
    public ImageView getBackdropImageView() {

        return backDropIv;
    }

    @Override
    public ImageView getPosterImageView() {
        return posterIv;
    }

    @Override
    public TextView getTitleTv() {
        return titleTv;
    }

    @Override
    public TextView getRatingTv() {
        return ratingTv;
    }

    @Override
    public TextView getRelaseDateTv() {
        return releaseDateTv;
    }

    @Override
    public TextView getOverViewTv() {
        return overViewTv;
    }


    private MovieGridItem getSelectedMovieItem(Bundle b) {

        if(b != null)
            moviegridItem = b.getString("movie_item");

        return new Gson().fromJson(moviegridItem , MovieGridItem.class);
    }



}
