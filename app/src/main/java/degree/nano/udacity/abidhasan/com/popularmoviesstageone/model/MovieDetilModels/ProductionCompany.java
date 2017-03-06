package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.MovieDetilModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abidhasan on 3/6/17.
 */

public class ProductionCompany {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id ;


    public ProductionCompany(String name, int id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
