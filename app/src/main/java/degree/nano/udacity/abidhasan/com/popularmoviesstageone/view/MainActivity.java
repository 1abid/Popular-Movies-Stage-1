package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ActivityFragmentStatemaintainer;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.PopularMoviesMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieActivityModel;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter.MoviePresenter;

public class MainActivity extends AppCompatActivity implements PopularMoviesMVP.RequiredViewOps {

    private PopularMoviesMVP.ProvidedPresenterOps mPresenter;
    private RecyclerView movieRecyclerView;
    private ProgressDialog pDailog;


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
        movieRecyclerView = (RecyclerView) findViewById(R.id.rvMovies);

        setUpMvp();

        if(pDailog == null)
            pDailog = mPresenter.createProgressDialog();
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
    private void setUpMvp(){

        if(mStateMaintainer.isFirstTimeIn()){

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
            mPresenter = presenter ;
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
    public RecyclerView getRecyclrView() {
        return movieRecyclerView;
    }
}
