package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abidhasan on 3/6/17.
 */

public class BelongToCollection {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private String posterPath;


    @SerializedName("backdrop_path")
    private String backDropPath;

    public BelongToCollection(int id, String name, String posterPath, String backDropPath) {
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.backDropPath = backDropPath;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackDropPath() {
        return backDropPath;
    }
}
