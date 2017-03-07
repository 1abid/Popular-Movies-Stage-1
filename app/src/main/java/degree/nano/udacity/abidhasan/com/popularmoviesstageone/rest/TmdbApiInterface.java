package degree.nano.udacity.abidhasan.com.popularmoviesstageone.rest;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels.MovieDetailResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.PopularMovieResponse;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.TopRatedMovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by VutkaBilai on 2/27/17.
 * mail : la4508@gmail.com
 */

public interface TmdbApiInterface {

    //get popularMovies
    @GET("movie/popular")
    Call<PopularMovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    //get toprate movies
    @GET("movie/top_rated")
    Call<TopRatedMovieResponse> getTopratedMovies(@Query("api_key") String apiKey);


    //get movie detail
    @GET("/movie/{movie_id}")
    Call<MovieDetailResponse> getMovieDetail(@Path("movie_id") int movieId, @Query("api_key") String apiKey);
}
