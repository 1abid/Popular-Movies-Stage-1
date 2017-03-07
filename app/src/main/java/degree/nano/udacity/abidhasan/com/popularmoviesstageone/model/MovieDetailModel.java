package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Application.RestServiceGenerator;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels.MovieDetailResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter.MovieDetailPresenter;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.rest.TmdbApiInterface;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.API_URLS;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Override
    public MovieDetailResponse loadMovieDetail(int movieId) {

        /*movieDetailPresenter.setProgressDialogMsg("loading...");
        movieDetailPresenter.getView().showProgressDialog();


        TmdbApiInterface apiInterface = RestServiceGenerator.createService(TmdbApiInterface.class);

        Call<MovieDetailResponse> movieDetailCall = apiInterface.getMovieDetail(movieId , API_URLS.TMDB_API_KEY);


        movieDetailCall.enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {

                if(response.isSuccessful()){

                    movieDetailPresenter.getView().hideProgressDialog();


                }
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {

            }
        });*/
        return null;
    }
}
