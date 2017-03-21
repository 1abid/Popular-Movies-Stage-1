package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Application.RestServiceGenerator;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ToastMaker;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.data.DAO;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels.MovieDetailResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieReviewsModel.MovieReviewResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieReviewsModel.Reviews;
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

    private MovieDetailMVP.RequiredPresenterOps mPresenter;
    private MovieDetailPresenter movieDetailPresenter;

    private ArrayList<MovieTrailer> trailerList;
    private ArrayList<Reviews> reviewList;

    private DAO mDao;

    public MovieDetailModel(MovieDetailMVP.RequiredPresenterOps mPresenter) {

        this.mPresenter = mPresenter;
        movieDetailPresenter = (MovieDetailPresenter) mPresenter;

        mDao = new DAO(mPresenter.getActivityContext());

        trailerList = new ArrayList<>();
        reviewList = new ArrayList<>();
    }

    @Override
    public void onDestroy(boolean isConfigurationChanging) {
        if (!isConfigurationChanging) {
            mPresenter = null;
            mDao = null;
        }
    }

    @Override
    public MovieDetailResponse loadMovieDetail(int movieId) {


        return null;
    }

    @Override
    public void getMovieTrailers(final String movieId) {


        TmdbApiInterface apiInterface = RestServiceGenerator.createService(TmdbApiInterface.class);

        final Call<MovieTrailerResponse> trailers = apiInterface.getTrailers(movieId, API_URLS.TMDB_API_KEY);

        trailers.enqueue(new Callback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {

                if (response.isSuccessful()) {

                    addMovieTrailers(response.body().getTrailers());

                    getMovieReviews(movieId);
                } else {
                    movieDetailPresenter.getView().showToast(ToastMaker.makeToast(movieDetailPresenter.getActivityContext()
                            , "something went wrong !"));
                }
            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                movieDetailPresenter.getView().hideProgressDialog();
            }
        });
    }

    @Override
    public void getMovieReviews(String movieId) {

        TmdbApiInterface apiInterface = RestServiceGenerator.createService(TmdbApiInterface.class);

        final Call<MovieReviewResponse> reviews = apiInterface.getReviews(Integer.valueOf(movieId), API_URLS.TMDB_API_KEY);

        reviews.enqueue(new Callback<MovieReviewResponse>() {
            @Override
            public void onResponse(Call<MovieReviewResponse> call, Response<MovieReviewResponse> response) {

                if (response.isSuccessful()) {

                    addReviews(response.body().getReviewsList());
                    movieDetailPresenter.getView().hideProgressDialog();

                    movieDetailPresenter.onTrailerDownload(trailerList, reviewList);

                } else {
                    movieDetailPresenter.getView().showToast(ToastMaker.makeToast(movieDetailPresenter.getActivityContext()
                            , "something went wrong !"));
                }
            }

            @Override
            public void onFailure(Call<MovieReviewResponse> call, Throwable t) {
                movieDetailPresenter.getView().hideProgressDialog();
            }
        });
    }

    @Override
    public int getTrailerItemCount() {
        return trailerList.size() == 0 ? 0 : trailerList.size();
    }

    @Override
    public void insertMovieToFavoriteList(MovieGridItem item) {
        if(!mDao.isAlreadyInserted(item.getMovieName())){
            mDao.insertMovie(item);
            mPresenter.changeFABState(true);
        }else {

            Log.d(getClass().getSimpleName() , "deleted "+mDao.removeMovie(item));
            mPresenter.changeFABState(false);
        }
    }

    @Override
    public boolean isAlreadyFaved(MovieGridItem item) {
        return mDao.isAlreadyInserted(item.getMovieName()) ? true : false ;
    }


    private void addMovieTrailers(ArrayList<MovieTrailer> trailers) {
        this.trailerList = trailers;
    }

    private void addReviews(List<Reviews> reviewsList) {
        this.reviewList = (ArrayList<Reviews>) reviewsList;
    }
}
