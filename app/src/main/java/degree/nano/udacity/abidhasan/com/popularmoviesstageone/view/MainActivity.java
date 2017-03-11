package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        setContentView(R.layout.activity_main);

        setUpMvp();
        setUpViews();

        showPopularMovies();

    }


    /**
     * tells presenter
     * to load popularmoies
     */
    private void showPopularMovies() {

        if (new GetNetworkStatus(getActivityContext()).isOnline())
            mPresenter.loadPopularMovies();
        else
            showToast(ToastMaker.makeToast(getActivityContext(), " No internetConnection !"));
    }


    private void setUpViews() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        movieRecyclerView = (RecyclerView) findViewById(R.id.rvMovies);

        if (pDailog == null)
            pDailog = createProgressDialog();

        if (mToolbar != null)
            setSupportActionBar(mToolbar);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.onDestroy(isChangingConfigurations());
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(pDailog!=null)
            pDailog.dismiss();
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

        try {
            if (mStateMaintainer.isFirstTimeIn()) {

                initilize(this);

            } else {
                reInitialize(this);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            Log.d(getClass().getSimpleName(), "onCreate() " + e);
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

        if (mPresenter == null) {
            Log.d(getClass().getSimpleName(), "Ininitalizing view");
            initilize(view);
        } else {
            Log.d(getClass().getSimpleName(), "reinitalizing view");
            mPresenter.setView(view);
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


    private ProgressDialog createProgressDialog(){
        pDailog = new ProgressDialog(getActivityContext()
                , R.style.AppTheme_Dark_Dialog);

        pDailog.setIndeterminate(true);
        pDailog.setCancelable(false);

        return pDailog;
    }

    @Override
    public RecyclerView getRecyclrView() {
        return movieRecyclerView;
    }
}
