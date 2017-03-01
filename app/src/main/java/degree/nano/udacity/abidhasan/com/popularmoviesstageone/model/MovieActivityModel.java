package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Application.RestServiceGenerator;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.PopularMoviesMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.Movie;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.PopularMovieResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.TopRatedMovieResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter.MoviePresenter;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.rest.TmdbApiInterface;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.API_URLS;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by VutkaBilai on 2/27/17.
 * mail : la4508@gmail.com
 */

public class MovieActivityModel implements PopularMoviesMVP.ProvidedModelOps {


    private PopularMoviesMVP.RequiredPresenterOps mPresenter ;
    private MoviePresenter moviePresenter ;

    private List<MovieGridItem> popularMovieGridItems;
    private List<MovieGridItem> topRatedMoviesGridItems;
    private List<Movie> popularMovies ;
    private List<Movie> topRatedMovies ;


    public MovieActivityModel(PopularMoviesMVP.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;

        moviePresenter = (MoviePresenter) mPresenter;

        popularMovieGridItems = new ArrayList<>() ;
        topRatedMoviesGridItems = new ArrayList<>();

        popularMovies = new ArrayList<>() ;
        topRatedMovies = new ArrayList<>() ;
    }

    @Override
    public void onDestroy(boolean isConfigurationChanging) {
        if(!isConfigurationChanging)
            mPresenter = null ;
    }

    @Override
    public void loadPopularMovies() {

        moviePresenter.setProgressDialogMsg("Loading Popular movies... " , moviePresenter.getView().getProgressDialog());
        moviePresenter.getView().showProgressDialog();

        TmdbApiInterface apiInterface = RestServiceGenerator.createService(TmdbApiInterface.class);

        Call<PopularMovieResponse> call = apiInterface.getPopularMovies(API_URLS.TMDB_API_KEY);

        call.enqueue(new Callback<PopularMovieResponse>() {

            @Override
            public void onResponse(Call<PopularMovieResponse> call, Response<PopularMovieResponse> response) {

                if(response.isSuccessful()){

                    moviePresenter.getView().hideProgressDialog();

                    popularMovies = response.body().getResults();

                    moviePresenter.showPopularMovies();
                }
            }

            @Override
            public void onFailure(Call<PopularMovieResponse> call, Throwable t) {

                moviePresenter.getView().hideProgressDialog();
                Log.d("ERROR : ","something went wrong .... "+t.getMessage());
            }
        });
    }

    @Override
    public void loadTopRatedMovies() {


        moviePresenter.setProgressDialogMsg("Loading Top Rated movies... " , moviePresenter.getView().getProgressDialog());
        moviePresenter.getView().showProgressDialog();

        TmdbApiInterface apiInterface = RestServiceGenerator.createService(TmdbApiInterface.class);

        Call<TopRatedMovieResponse> call = apiInterface.getTopratedMovies(API_URLS.TMDB_API_KEY);

        call.enqueue(new Callback<TopRatedMovieResponse>() {

            @Override
            public void onResponse(Call<TopRatedMovieResponse> call, Response<TopRatedMovieResponse> response) {

                if(response.isSuccessful()){

                    moviePresenter.getView().hideProgressDialog();

                    topRatedMovies = response.body().getResults();

                    moviePresenter.showTopRatedMovies();
                }
            }

            @Override
            public void onFailure(Call<TopRatedMovieResponse> call, Throwable t) {

                moviePresenter.getView().hideProgressDialog();
                Log.d("ERROR : ","something went wrong .... "+t.getMessage());
            }
        });
    }

    @Override
    public int getPopularMoviesListSize() {
        return (popularMovies == null) ? 0 : popularMovies.size();
    }

    @Override
    public int getTopRatedMoviesListSize() {
        return (topRatedMovies == null ) ? 0 : topRatedMovies.size();
    }


    /**
     * generate popular movie grid item
     * from popular movieList
     * @return
     */
    @Override
    public List<MovieGridItem> generatePopularMovieGridItems() {

        for (Movie movie : popularMovies) {

            MovieGridItem item = new MovieGridItem() ;
            int movieId = movie.getId();
            String movieName = movie.getMovieTitle();
            String movieTitle = movie.getMovieTitleOriginal();
            String movieVote = String.valueOf(movie.getVoteAvg());
            String releaseDate = movie.getReleaseDate();
            String backDropPath = movie.getBackdropPath();
            String posterPath = movie.getPosterPath();

            item.setMovieId(movieId);
            item.setMovieName(movieName);
            item.setMovieAvg_vote(movieVote);
            item.setMovieReleaseDate(releaseDate);
            item.setMovieBackDropPath(backDropPath);
            item.setMoviePosterPath(posterPath);
            item.setMovieTitle(movieTitle);

            popularMovieGridItems.add(item);

        }

        return popularMovieGridItems;
    }

    /**
     * generate toprated movie grid item
     * from toprated movieList
     * @return
     */
    @Override
    public List<MovieGridItem> generateTopRatedMovieGridItems() {

        for (Movie movie : topRatedMovies) {

            MovieGridItem item = new MovieGridItem() ;
            int movieId = movie.getId();
            String movieName = movie.getMovieTitle();
            String movieTitle = movie.getMovieTitleOriginal();
            String movieVote = String.valueOf(movie.getVoteAvg());
            String releaseDate = movie.getReleaseDate();
            String backDropPath = movie.getBackdropPath();
            String posterPath = movie.getPosterPath();

            item.setMovieId(movieId);
            item.setMovieName(movieName);
            item.setMovieAvg_vote(movieVote);
            item.setMovieReleaseDate(releaseDate);
            item.setMovieBackDropPath(backDropPath);
            item.setMoviePosterPath(posterPath);
            item.setMovieTitle(movieTitle);

            topRatedMoviesGridItems.add(item);

        }

        return topRatedMoviesGridItems;
    }
}
