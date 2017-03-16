package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VutkaBilai on 3/17/17.
 * mail : la4508@gmail.com
 */

public class MovieTrailer {

    @SerializedName("id")
    private String id;


    @SerializedName("key")
    private String vieoKey ;


    @SerializedName("name")
    private String trailerTitle;


    @SerializedName("site")
    private String site;


    @SerializedName("size")
    private int size;


    @SerializedName("type")
    private String type;


    public MovieTrailer(String id, String vieoKey, String trailerTitle, String site, int size, String type) {
        this.id = id;
        this.vieoKey = vieoKey;
        this.trailerTitle = trailerTitle;
        this.site = site;
        this.size = size;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getVieoKey() {
        return vieoKey;
    }

    public String getTrailerTitle() {
        return trailerTitle;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}
