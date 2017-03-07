package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abidhasan on 3/6/17.
 */

public class Genres {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;


    public Genres(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
