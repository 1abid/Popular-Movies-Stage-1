package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailMVP.RequiredViewOps {

    private MovieDetailMVP.ProviedPresenterOps mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }

    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }

    @Override
    public ProgressDialog getProgressDialog() {
        return null;
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showToast(Toast toast) {

    }

    @Override
    public void showAlert(AlertDialog alertDialog) {

    }
}
