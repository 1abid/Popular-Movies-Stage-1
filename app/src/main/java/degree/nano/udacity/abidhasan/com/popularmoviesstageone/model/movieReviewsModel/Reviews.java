package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieReviewsModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VutkaBilai on 3/20/17.
 * mail : la4508@gmail.com
 */

public class Reviews {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;


    @SerializedName("content")
    private String content;


    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
