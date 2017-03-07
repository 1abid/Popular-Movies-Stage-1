package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels;

/**
 * Created by VutkaBilai on 2/27/17.
 * mail : la4508@gmail.com
 */

public class MovieGridItem {

    private int movieId;
    private String movieAvg_vote ;
    private String movieName ;
    private String movieReleaseDate ;
    private String movieTitle;
    private String movieBackDropPath;
    private String moviePosterPath;
    private String MovieOverView;

    public MovieGridItem() {
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMovieAvg_vote(String movieAvg_vote) {
        this.movieAvg_vote = movieAvg_vote;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMovieBackDropPath(String movieBackDropPath) {
        this.movieBackDropPath = movieBackDropPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }

    public String getMovieAvg_vote() {
        return movieAvg_vote;
    }

    public String getMovieBackDropPath() {
        return movieBackDropPath;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public String getMovieOverView() {
        return MovieOverView;
    }

    public void setMovieOverView(String movieOverView) {
        MovieOverView = movieOverView;
    }
}
