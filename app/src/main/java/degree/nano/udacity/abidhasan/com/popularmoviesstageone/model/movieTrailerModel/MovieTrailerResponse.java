package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VutkaBilai on 3/17/17.
 * mail : la4508@gmail.com
 */

public class MovieTrailerResponse {


    @SerializedName("id")
    private int movieId;

    @SerializedName("results")
    private ArrayList<MovieTrailer> trailers;

    public MovieTrailerResponse(int movieId, ArrayList<MovieTrailer> trailers) {
        this.movieId = movieId;
        this.trailers = trailers;
    }

    public int getMovieId() {
        return movieId;
    }

    public ArrayList<MovieTrailer> getTrailers() {
        return trailers;
    }
}
