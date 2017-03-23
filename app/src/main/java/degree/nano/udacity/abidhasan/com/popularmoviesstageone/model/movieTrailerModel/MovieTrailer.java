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

    public void setId(String id) {
        this.id = id;
    }

    public void setVieoKey(String vieoKey) {
        this.vieoKey = vieoKey;
    }

    public void setTrailerTitle(String trailerTitle) {
        this.trailerTitle = trailerTitle;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(String type) {
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
