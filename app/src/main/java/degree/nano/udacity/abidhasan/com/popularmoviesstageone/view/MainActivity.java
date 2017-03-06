package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ActivityFragmentStatemaintainer;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.PopularMoviesMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieActivityModel;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter.MoviePresenter;

public class MainActivity extends AppCompatActivity implements PopularMoviesMVP.RequiredViewOps {

    private PopularMoviesMVP.ProvidedPresenterOps mPresenter;
    private RecyclerView movieRecyclerView;
    private ProgressDialog pDailog;
    private Toolbar mToolbar;

    // Responsible to maintain the object's integrity
    // during configurations change
    private ActivityFragmentStatemaintainer mStateMaintainer
            = new ActivityFragmentStatemaintainer(getFragmentManager(), getClass().getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        setUpViews();

        showPopularMovies();

    }


    /**
     * tells presenter
     * to load popularmoies
     */
    private void showPopularMovies() {
        mPresenter.loadPopularMovies();
    }


    private void setUpViews() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        movieRecyclerView = (RecyclerView) findViewById(R.id.rvMovies);

        setUpMvp();

        if (pDailog == null)
            pDailog = mPresenter.createProgressDialog();

        if (mToolbar != null)
            setSupportActionBar(mToolbar);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy(isChangingConfigurations());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mPresenter.onCreateOptionMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.popular_movies:
                mPresenter.setPopularSelected(true);
                mPresenter.popularMoviesSelected();

            case R.id.toprated_movies:
                mPresenter.setPopularSelected(false);
                mPresenter.topRatedMoviesSelected();

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Setup Model View Presenter pattern.
     * Use a {@link ActivityFragmentStatemaintainer} to maintain the
     * Presenter and Model instances between configuration changes.
     * Could be done differently,
     * using a dependency injection for example.
     */
    private void setUpMvp() {

        if (mStateMaintainer.isFirstTimeIn()) {

            //create the presenter
            MoviePresenter presenter = new MoviePresenter(this);

            //create the model
            MovieActivityModel model = new MovieActivityModel(presenter);

            //set presenter to model
            presenter.setModel(model);

            //save presenter
            /**and model to {@link ActivityFragmentStatemaintainer}**/
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
        return MainActivity.this;
    }

    @Override
    public ProgressDialog getProgressDialog() {

        if (pDailog != null)
            return pDailog;

        return null;
    }

    @Override
    public void showProgressDialog() {
        pDailog.show();
    }

    @Override
    public void hideProgressDialog() {
        pDailog.hide();
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
    public void goToDetailActivity(MovieGridItem item) {
        Intent activity = new Intent(this , MovieDetailActivity.class);
        activity.putExtra("movie_item" , new Gson().toJson(item));
        startActivity(activity);
    }

    @Override
    public RecyclerView getRecyclrView() {
        return movieRecyclerView;
    }
}
