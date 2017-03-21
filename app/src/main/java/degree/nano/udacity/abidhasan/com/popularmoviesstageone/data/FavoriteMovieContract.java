package degree.nano.udacity.abidhasan.com.popularmoviesstageone.data;

import android.provider.BaseColumns;

/**
 * Created by abidhasan on 3/21/17.
 */

public final class FavoriteMovieContract {


    private FavoriteMovieContract(){

    }

    /**
     * inner class which defines table favorite Movie table
     */
    public static class FavoriteMovieEntry implements BaseColumns{

        /**table entry for table favorite**/
        public static final String TABLE_NAME = "favMovies";
        public static final String COLUMN_NAME_MOVIE_ID = "movie_id";
        public static final String COLUMN_NAME_MOVIE_VOTE_AVG = "vote_avg";
        public static final String COLUMN_NAME_MOVIE_NAME = "movie_name";
        public static final String COLUMN_NAME_MOVIE_TITLE = "movie_title";
        public static final String COLUMN_NAME_RELEASE_DATE = "release_date";
        public static final String COLUMN_NAME_BACKDROP = "movie_backdrop";
        public static final String COLUMN_NAME_POSTER = "movie_poster";
        public static final String COLUMN_NAME_OVERVIEW = "movie_overView";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";

    }

    /**
     * inner class which defines movie trailer table
     */
    public static class MovieTrailerEntry implements BaseColumns{

        /**table entry for table trailers**/
        public static final String TABLE_NAME = "trailers";
        public static final String COLUMN_NAME_MOVIE_ID = "movie_id";
        public static final String COLUMN_NAME_TRAILER_ID = "trailer_id";
        public static final String COLUMN_NAME_TRAILER_VIDEOKEY = "video_key";
        public static final String COLUMN_NAME_TRAILER_TITLE = "trailer_title";
        public static final String COLUMN_NAME_TRAILER_SITE = "trailer_site";
        public static final String COLUMN_NAME_TRAILER_SIZE = "trailer_size";
        public static final String COLUMN_NAME_TRAILER_TYPE = "trailer_type";
    }

    /**
     * inner class which defines movie review table
     */
    public static class MovieReviewEntry implements BaseColumns{

        /**table entry for table review**/
        public static final String TABLE_NAME = "reviews";
        public static final String COLUMN_NAME_MOVIE_ID = "movie_id";
        public static final String COLUMN_NAME_REVIEW_ID = "review_id";
        public static final String COLUMN_NAME_REVIEW_AUTHOR = "review_auth";
        public static final String COLUMN_NAME_REVIEW_CONTENT = "review_content";
        public static final String COLUMN_NAME_REVIEW_URL = "review_url";
    }
}
