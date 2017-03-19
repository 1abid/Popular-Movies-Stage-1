package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieReviewsModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VutkaBilai on 3/20/17.
 * mail : la4508@gmail.com
 */

public class MovieReviewResponse {

    @SerializedName("id")
    private int movieID;

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Reviews> reviewsList;


    @SerializedName("total_pages")
    private int totalPage;


    @SerializedName("total_results")
    private int totalResults;


    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
