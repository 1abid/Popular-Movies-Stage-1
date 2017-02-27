package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by VutkaBilai on 2/27/17.
 * mail : la4508@gmail.com
 */

public class PopularMovieResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalpages;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("results")
    private ArrayList<Movie> results;

    public PopularMovieResponse(int page, ArrayList<Movie> results, int totalpages, int totalResults) {
        this.page = page;
        this.results = results;
        this.totalpages = totalpages;
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
