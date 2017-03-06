package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter.MovieDetailPresenter;

/**
 * Created by abidhasan on 3/6/17.
 */

public class MovieDetailModel implements MovieDetailMVP.ProvidedModelOps {

    private MovieDetailMVP.ProviedPresenterOps mPresenter;
    private MovieDetailPresenter movieDetailPresenter;


    public MovieDetailModel(MovieDetailMVP.ProviedPresenterOps mPresenter) {

        this.mPresenter = mPresenter;
        movieDetailPresenter = (MovieDetailPresenter) mPresenter;



    }

    @Override
    public void onDestroy(boolean isConfigurationChanging) {
        if(!isConfigurationChanging)
            mPresenter = null ;
    }
}
