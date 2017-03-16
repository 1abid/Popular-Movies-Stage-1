package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Application.RestServiceGenerator;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ToastMaker;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels.MovieDetailResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel.MovieTrailer;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel.MovieTrailerResponse;
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

    private ArrayList<MovieTrailer> trailerList;

    public MovieDetailModel(MovieDetailMVP.ProviedPresenterOps mPresenter) {

        this.mPresenter = mPresenter;
        movieDetailPresenter = (MovieDetailPresenter) mPresenter;

        trailerList = new ArrayList<>();
    }

    @Override
    public void onDestroy(boolean isConfigurationChanging) {
        if(!isConfigurationChanging)
            mPresenter = null ;
    }

    @Override
    public MovieDetailResponse loadMovieDetail(int movieId) {


        return null;
    }

    @Override
    public List<MovieTrailer> getMovieTrailers(String movieId) {


        TmdbApiInterface apiInterface = RestServiceGenerator.createService(TmdbApiInterface.class);

        final Call<MovieTrailerResponse> trailers = apiInterface.getTrailers(movieId,API_URLS.TMDB_API_KEY);

        trailers.enqueue(new Callback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {

                    if(response.isSuccessful()){
                    addMovieTrailers(response.body().getTrailers());
                    movieDetailPresenter.getView().hideProgressDialog();

                    movieDetailPresenter.onTrailerDownload(trailerList);
                    }else {
                        movieDetailPresenter.getView().showToast(ToastMaker.makeToast(movieDetailPresenter.getActivityContext()
                                ,"something went wrong !"));
                    }
            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                movieDetailPresenter.getView().hideProgressDialog();
            }
        });
        return trailerList;
    }

    @Override
    public int getTrailerItemCount() {
        return trailerList.size() == 0 ? 0 : trailerList.size() ;
    }

    private void addMovieTrailers(ArrayList<MovieTrailer> trailers) {
        this.trailerList = trailers;
    }


}
