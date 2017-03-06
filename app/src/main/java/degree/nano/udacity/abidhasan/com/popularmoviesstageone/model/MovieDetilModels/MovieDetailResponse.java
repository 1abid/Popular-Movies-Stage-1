package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abidhasan on 3/6/17.
 */

public class MovieDetailResponse {

    @SerializedName("adult")
    private boolean isAdult;

    @SerializedName("backdrop_path")
    private String backDropPath;

    @SerializedName("belongs_to_collection")
    private BelongToCollection collection;

    @SerializedName("budget")
    private int movieBudget;

    @SerializedName("genres")
    private Genres movieGenres;


    @SerializedName("homepage")
    private String homePage;

    @SerializedName("id")
    private int movieId;

    @SerializedName("imdb_id")
    private String imdbId;


    @SerializedName("original_language")
    private String movielanguage;


    @SerializedName("original_title")
    private String movieOriginalTitle;

    @SerializedName("overview")
    private String overview;


    @SerializedName("popularity")
    private String popularity;


    @SerializedName("poster_path")
    private String moviePoster;


    @SerializedName("production_companies")
    private ProductionCompany producer;


    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("revenue")
    private int revenue;

    @SerializedName("runtime")
    private String runtime;


    @SerializedName("status")
    private String status;

    @SerializedName("tagline")
    private String tagLine;


    @SerializedName("title")
    private String movirTitle;


    @SerializedName("video")
    private boolean isVideo;


    @SerializedName("vote_average")
    private float voteAvg;


    @SerializedName("vote_count")
    private int voteCount;


    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public BelongToCollection getCollection() {
        return collection;
    }

    public void setCollection(BelongToCollection collection) {
        this.collection = collection;
    }

    public int getMovieBudget() {
        return movieBudget;
    }

    public void setMovieBudget(int movieBudget) {
        this.movieBudget = movieBudget;
    }

    public Genres getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(Genres movieGenres) {
        this.movieGenres = movieGenres;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovielanguage() {
        return movielanguage;
    }

    public void setMovielanguage(String movielanguage) {
        this.movielanguage = movielanguage;
    }

    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }

    public void setMovieOriginalTitle(String movieOriginalTitle) {
        this.movieOriginalTitle = movieOriginalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public ProductionCompany getProducer() {
        return producer;
    }

    public void setProducer(ProductionCompany producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getMovirTitle() {
        return movirTitle;
    }

    public void setMovirTitle(String movirTitle) {
        this.movirTitle = movirTitle;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public float getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(float voteAvg) {
        this.voteAvg = voteAvg;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
