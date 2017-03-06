package degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter;

import android.app.ProgressDialog;
import android.content.Context;

import java.lang.ref.WeakReference;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;

/**
 * Created by abidhasan on 3/6/17.
 */

public class MovieDetailPresenter implements MovieDetailMVP.ProviedPresenterOps,MovieDetailMVP.RequiredPresenterOps {

    private WeakReference<MovieDetailMVP.RequiredViewOps> mView;

    private MovieDetailMVP.ProvidedModelOps mModel;


    public MovieDetailPresenter(MovieDetailMVP.RequiredViewOps mView) {
        this.mView = new WeakReference<MovieDetailMVP.RequiredViewOps>(mView);

    }

    /**
     * called by activity every time during
     * setting up MVP , only called once
     *
     * @param mModel
     *
     */
    public void setModel(MovieDetailMVP.ProvidedModelOps mModel){
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

    /**
     * called by {@link degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieDetailActivity}
     * @param view
     */
    @Override
    public void setView(MovieDetailMVP.RequiredViewOps view) {
        this.mView = new WeakReference<MovieDetailMVP.RequiredViewOps>(view);
    }


    /**
     * show progressDialog
     * for showing network
     * calling progress
     */
    @Override
    public ProgressDialog createProgressDialog() {

        ProgressDialog pDialog = new ProgressDialog(getView().getActivityContext()
                , R.style.AppTheme_Dark_Dialog);

        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);

        return pDialog;
    }

    @Override
    public void setProgressDialogMsg(String msg) {
        getView().getProgressDialog().setMessage(msg);
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
    public MovieDetailMVP.RequiredViewOps getView() throws NullPointerException{

        if (mView != null)
            return mView.get();
        else
            throw new NullPointerException("view is unavailable");

    }
}
