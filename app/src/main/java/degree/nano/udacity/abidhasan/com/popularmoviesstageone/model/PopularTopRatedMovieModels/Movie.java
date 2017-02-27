package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by VutkaBilai on 2/27/17.
 * mail : la4508@gmail.com
 */

public class Movie {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("adult")
    private boolean isAdult;

    @SerializedName("overview")
    private String overView;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("genre_ids")
    private ArrayList<Integer> genreIds;

    @SerializedName("id")
    private int id;


    @SerializedName("original_title")
    private String movieTitleOriginal;


    @SerializedName("original_language")
    private String movieLang;

    @SerializedName("title")
    private String movieTitle;

    @SerializedName("backdrop_path")
    private String backdropPath;


    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_count")
    private int vote;


    @SerializedName("video")
    private boolean isVideo;


    @SerializedName("vote_average")
    private float voteAvg;


    public Movie(float voteAvg, String posterPath, boolean isAdult, String overView,
                         ArrayList<Integer> genreIds, int id, String movieTitleOriginal, String movieLang,
                         String movieTitle, String backdropPath, double popularity, int vote, boolean isVideo ,String releaseDate) {
        this.voteAvg = voteAvg;
        this.posterPath = posterPath;
        this.isAdult = isAdult;
        this.overView = overView;
        this.genreIds = genreIds;
        this.id = id;
        this.movieTitleOriginal = movieTitleOriginal;
        this.movieLang = movieLang;
        this.movieTitle = movieTitle;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.vote = vote;
        this.isVideo = isVideo;
        this.releaseDate = releaseDate;
    }


    public String getPosterPath() {
        return posterPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getOverView() {
        return overView;
    }

    public ArrayList<Integer> getGenreIds() {
        return genreIds;
    }

    public int getId() {
        return id;
    }

    public String getMovieTitleOriginal() {
        return movieTitleOriginal;
    }

    public String getMovieLang() {
        return movieLang;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVote() {
        return vote;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public float getVoteAvg() {
        return voteAvg;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
