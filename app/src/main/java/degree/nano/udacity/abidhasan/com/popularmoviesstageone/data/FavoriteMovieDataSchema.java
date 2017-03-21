package degree.nano.udacity.abidhasan.com.popularmoviesstageone.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by abidhasan on 3/21/17.
 */

public class FavoriteMovieDataSchema extends SQLiteOpenHelper {

    private static final int    DB_VERSION  = 1;
    private static final String DB_NAME     = "favorite_movie.db";

    private static final String COMMA_SPACE     = ", ";
    private static final String CREATE_TABLE    = "CREATE TABLE ";
    private static final String PRIMARY_KEY     = "PRIMARY KEY ";
    private static final String UNIQUE          = "UNIQUE ";
    private static final String TYPE_TEXT       = " TEXT ";
    private static final String TYPE_DATE       = " DATETIME ";
    private static final String TYPE_INT        = " INTEGER ";
    private static final String TYPE_TIMESTAMP = " TIMESTAMP ";
    private static final String CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
    private static final String DEFAULT         = "DEFAULT ";
    private static final String AUTOINCREMENT   = "AUTOINCREMENT ";
    private static final String NOT_NULL        = "NOT NULL ";
    private static final String DROP_TABLE      = "DROP TABLE IF EXISTS ";


    /**
     * create table favorite movie table sql query
     */
    private static final String SQL_CREATE_TABLE_FAVORITE_MOVIE = CREATE_TABLE +
            FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME + " ("+
            FavoriteMovieContract.FavoriteMovieEntry._ID + TYPE_INT + PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE +
            FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_ID + TYPE_INT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_VOTE_AVG + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_NAME + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_RELEASE_DATE + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_TITLE + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_BACKDROP + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_POSTER + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_OVERVIEW + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_TIMESTAMP +TYPE_TIMESTAMP + DEFAULT + CURRENT_TIMESTAMP +");";


    private static final String SQL_CREATE_TABLE_MOVIE_TRAILER = CREATE_TABLE +
            FavoriteMovieContract.MovieTrailerEntry.TABLE_NAME + " ("+
            FavoriteMovieContract.MovieTrailerEntry._ID + TYPE_INT + PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE +
            FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_MOVIE_ID + TYPE_INT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_ID + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_VIDEOKEY + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_TITLE + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_SITE + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_SIZE + TYPE_INT + COMMA_SPACE +
            FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_TYPE + TYPE_TEXT + ");";


    private static final String SQL_CREATE_TABLE_MOVIE_REVIEW = CREATE_TABLE +
            FavoriteMovieContract.MovieReviewEntry.TABLE_NAME + " ("+
            FavoriteMovieContract.MovieReviewEntry._ID + TYPE_INT + PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE +
            FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_MOVIE_ID + TYPE_INT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_ID + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_AUTHOR + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_CONTENT + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
            FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_URL + TYPE_TEXT + NOT_NULL + ");";


    public FavoriteMovieDataSchema(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAVORITE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_MOVIE_TRAILER);
        db.execSQL(SQL_CREATE_TABLE_MOVIE_REVIEW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME);
        db.execSQL(DROP_TABLE + FavoriteMovieContract.MovieTrailerEntry.TABLE_NAME);
        db.execSQL(DROP_TABLE + FavoriteMovieContract.MovieReviewEntry.TABLE_NAME);
        onCreate(db);
    }
}
