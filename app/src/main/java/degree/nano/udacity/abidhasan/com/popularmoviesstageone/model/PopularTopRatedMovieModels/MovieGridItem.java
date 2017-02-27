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
    private String movieLength;
    private String MoviewGenere;
    private String movieBackDroppath;
    private String moviePosterPath;

    public MovieGridItem(String movieAvg_vote, String movieBackDroppath,
                         int movieId, String movieLength, String movieName,
                         String moviePosterPath, String movieReleaseDate, String moviewGenere) {
        this.movieAvg_vote = movieAvg_vote;
        this.movieBackDroppath = movieBackDroppath;
        this.movieId = movieId;
        this.movieLength = movieLength;
        this.movieName = movieName;
        this.moviePosterPath = moviePosterPath;
        this.movieReleaseDate = movieReleaseDate;
        MoviewGenere = moviewGenere;
    }


    public String getMovieAvg_vote() {
        return movieAvg_vote;
    }

    public String getMovieBackDroppath() {
        return movieBackDroppath;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieLength() {
        return movieLength;
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

    public String getMoviewGenere() {
        return MoviewGenere;
    }
}
