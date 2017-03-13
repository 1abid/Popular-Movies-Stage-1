package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ActivityFragmentStatemaintainer;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ToastMaker;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.PopularMoviesMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieActivityModel;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter.MoviePresenter;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.GetNetworkStatus;

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

        setUpMvp();
        Log.d(getClass().getSimpleName(), "lifecycle_event :onCreate()");

        setContentView(R.layout.activity_main);
        setUpViews();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(), "lifecycle_event :onStart()");

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), "lifecycle_event :onResume()");

        showPopularMovies(getSortType());
    }

    private String getSortType(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivityContext());

        return sharedPreferences.getString(getString(R.string.sort_key) , "1");
    }

    /**
     * tells presenter
     * to load popularmoies
     */
    private void showPopularMovies(String sortID) {

        if (new GetNetworkStatus(getActivityContext()).isOnline()) {
            if ("1".equals(sortID)) {
                Log.d(getClass().getSimpleName(), "popular selected");
                mPresenter.loadPopularMovies();
            } else {
                Log.d(getClass().getSimpleName(), "Toprated selected");
                mPresenter.loadTopRatedMovies();
            }
        } else
            showToast(ToastMaker.makeToast(getActivityContext(), " No internetConnection !"));
    }


    private void setUpViews() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        movieRecyclerView = (RecyclerView) findViewById(R.id.rvMovies);

        mPresenter.initializeRecyclerView();

        if (pDailog == null)
            pDailog = createProgressDialog();

        if (mToolbar != null)
            setSupportActionBar(mToolbar);

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getClass().getSimpleName(), "lifecycle_event :onPause()");

        if (pDailog != null)
            pDailog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), "lifecycle_event :onStop()");

        mPresenter.onConfigurationChanged(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getClass().getSimpleName(), "lifecycle_event :onDestroy()");
        mPresenter.onDestroy(isChangingConfigurations());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mPresenter.onCreateOptionMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            /*case R.id.popular_movies:
                mPresenter.setPopularSelected(true);
                mPresenter.popularMoviesSelected();
                break;

            case R.id.toprated_movies:
                mPresenter.setPopularSelected(false);
                mPresenter.topRatedMoviesSelected();
                break;*/

            case R.id.setting_menu:
                startActivity(new Intent(this , SettingsActivity.class));
                return true;

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

        try {
            if (mStateMaintainer.isFirstTimeIn()) {

                initilize(this);

            } else {
                Log.d(getClass().getSimpleName(), " reinitializing...");
                reInitialize(this);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            Log.e(getClass().getSimpleName(), "onCreate() " + e);
            throw new RuntimeException(e);
        }

    }

    /**
     * Initialize relevant MVP Objects.
     * Creates a Presenter instance, saves the presenter in {@link ActivityFragmentStatemaintainer}
     *
     * @param view
     */
    private void initilize(PopularMoviesMVP.RequiredViewOps view)
            throws InstantiationException, IllegalAccessException {

        //create the presenter
        MoviePresenter presenter = new MoviePresenter(this);

        //create the model
        MovieActivityModel model = new MovieActivityModel(presenter);

        //set presenter to model
        presenter.setModel(model);

        //save presenter
        /**and model to {@link ActivityFragmentStatemaintainer}**/
        mStateMaintainer.put(PopularMoviesMVP.ProvidedPresenterOps.class.getSimpleName(), presenter);
        mStateMaintainer.put(PopularMoviesMVP.ProvidedModelOps.class.getSimpleName(), model);

        //set the presenter as a interface
        //to limit communication with it
        mPresenter = presenter;

    }


    /**
     * Recovers Presenter and informs Presenter that occurred a config change.
     * If Presenter has been lost, recreates a instance
     */
    private void reInitialize(PopularMoviesMVP.RequiredViewOps view)
            throws InstantiationException, IllegalAccessException {

        mPresenter = mStateMaintainer.get(PopularMoviesMVP.ProvidedPresenterOps.class.getSimpleName());

        if (mPresenter == null)
            initilize(view);
        else
            mPresenter.onConfigurationChanged(this);
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

        if (pDailog == null)
            pDailog = createProgressDialog();

        return pDailog;
    }

    @Override
    public void showProgressDialog() {
        if (pDailog == null)
            pDailog = createProgressDialog();

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
        Intent activity = new Intent(this, MovieDetailActivity.class);
        activity.putExtra("movie_item", new Gson().toJson(item));
        startActivity(activity);
    }


    private ProgressDialog createProgressDialog() {
        pDailog = new ProgressDialog(getActivityContext()
                , R.style.AppTheme_Dark_Dialog);

        pDailog.setIndeterminate(true);
        pDailog.setCancelable(false);

        return pDailog;
    }

    @Override
    public RecyclerView getRecyclrView() {

        if (movieRecyclerView == null) {

            return movieRecyclerView = (RecyclerView) findViewById(R.id.rvMovies);
        }

        return movieRecyclerView;
    }
}
