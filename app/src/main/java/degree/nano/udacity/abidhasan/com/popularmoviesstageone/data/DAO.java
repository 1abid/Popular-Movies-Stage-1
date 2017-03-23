package degree.nano.udacity.abidhasan.com.popularmoviesstageone.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieReviewsModel.Reviews;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel.MovieTrailer;

/**
 * Created by abidhasan on 3/21/17.
 */

public class DAO {

    private FavoriteMovieDataSchema dbOpenhelper;
    private Context context ;

    //SELECTION
    private static final String SELECT_ID_BASED = FavoriteMovieContract.FavoriteMovieEntry._ID + " =? ";
    private static final String SELECT_MOVIE_NAME_BASED = FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_NAME + " =? ";
    private static final String SELECT_TRAILER_ID_BASED = FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_ID + " =? ";
    private static final String SELECT_REVIEW_ID_BASED = FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_ID + " =? ";
    private static final String SELECT_MOVIE_ID_BASED = FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_ID + " =? ";
    private static final String PROJECTION_ALL = " * ";
    private static final String SORT_ORDER_DEFAULT = FavoriteMovieContract.FavoriteMovieEntry._ID + " DESC";

    public DAO(Context context) {
        this.context = context;

        dbOpenhelper = new FavoriteMovieDataSchema(context);
    }


    private SQLiteDatabase getReadDb(){
        return dbOpenhelper.getReadableDatabase();
    }

    private SQLiteDatabase getWritDb(){
        return dbOpenhelper.getWritableDatabase();
    }


    /**
     * add movie to favorite list
     *
     * @param @return
     */
    public MovieGridItem insertMovie(MovieGridItem item){

        SQLiteDatabase db = getWritDb();
        long id = db.insert(FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME , null , getMovieItemCV(item));

        Log.d(getClass().getSimpleName() , " inserted ... "+id);

        MovieGridItem insertedItem = getMovie((int) id);
        db.close();

        return insertedItem;
    }

    public void insertMovieTriler(MovieTrailer trailer , int movieID){

        SQLiteDatabase db = getWritDb();
        long id = db.insert(FavoriteMovieContract.MovieTrailerEntry.TABLE_NAME , null , getTrailerItemCV(trailer,movieID));

        Log.d(getClass().getSimpleName() , " inserted trailer... "+id);
        db.close();


    }

    public void insertMovieReview(Reviews review , int movieId){

        SQLiteDatabase db = getWritDb();
        long id = db.insert(FavoriteMovieContract.MovieReviewEntry.TABLE_NAME, null , getReviewItemCv(review , movieId));

        Log.d(getClass().getSimpleName() , " inserted review... "+id);
        db.close();

    }


    public MovieGridItem getMovie(int id){

        SQLiteDatabase db = getReadDb();
        Cursor c = db.query(FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME,
                null,
                SELECT_ID_BASED,
                new String[]{Integer.toString(id)},
                null,
                null,
                null);

        if(c != null){

            c.moveToFirst();

            MovieGridItem foundItem = getCursorValues(c);

            c.close();
            db.close();

            return foundItem;
        }else {
            return null ;
        }

    }

    public ArrayList<MovieGridItem> getAllMovies(){

        SQLiteDatabase db = getReadDb();

        ArrayList<MovieGridItem> allFavoritedMovies = new ArrayList<>();

        Cursor c = db.query(FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME,
                null ,
                null ,
                null ,
                null ,
                null ,
                null );

        if(c != null && c.moveToFirst()){

            while (!c.isAfterLast()){
                MovieGridItem foundMovie = getCursorValues(c) ;

                allFavoritedMovies.add(foundMovie);
                c.moveToNext();
            }

            c.close();
            db.close();

            return allFavoritedMovies;
        }else {
            return null ;
        }
    }

    public ArrayList<MovieTrailer> getTrilers(int movieId){

        SQLiteDatabase db = getReadDb();

        ArrayList<MovieTrailer> allTrilers = new ArrayList<>();

        Cursor c = db.query(FavoriteMovieContract.MovieTrailerEntry.TABLE_NAME,
                null ,
                SELECT_MOVIE_ID_BASED ,
                new String[]{String.valueOf(movieId)} ,
                null ,
                null ,
                null );

        if(c != null && c.moveToFirst()){

            while (!c.isAfterLast()){
                MovieTrailer trailer = getTrailerCursorValues(c);

                allTrilers.add(trailer);
                c.moveToNext();
            }

            c.close();
            db.close();

            return allTrilers;
        }else {
            return allTrilers ;
        }
    }

    public ArrayList<Reviews> getReviews(int movieId){

        SQLiteDatabase db = getReadDb();

        ArrayList<Reviews> allReviews = new ArrayList<>();

        Cursor c = db.query(FavoriteMovieContract.MovieReviewEntry.TABLE_NAME,
                null ,
                SELECT_MOVIE_ID_BASED ,
                new String[]{String.valueOf(movieId)} ,
                null ,
                null ,
                null );

        if(c != null && c.moveToFirst()){

            while (!c.isAfterLast()){
                Reviews reviews = getReviewsCursorValues(c);

                allReviews.add(reviews);
                c.moveToNext();
            }

            c.close();
            db.close();

            return allReviews;
        }else {
            return allReviews ;
        }
    }

    public long removeMovie(MovieGridItem item){

        SQLiteDatabase db = getWritDb();
        long res = db.delete(FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME
                ,SELECT_MOVIE_NAME_BASED
                ,new String[]{item.getMovieName()});
        db.close();

        return res;

    }

    public long removeTrailer(String trilerId){

        SQLiteDatabase db = getWritDb() ;

        long res = db.delete(FavoriteMovieContract.MovieTrailerEntry.TABLE_NAME
                 ,SELECT_TRAILER_ID_BASED
                 ,new String[]{trilerId});

        db.close();

        return res;
    }


    public long removeReview(String reviewId){

        SQLiteDatabase db = getWritDb() ;

        long res = db.delete(FavoriteMovieContract.MovieReviewEntry.TABLE_NAME
                ,SELECT_REVIEW_ID_BASED
                ,new String[]{reviewId});

        db.close();

        return res;
    }



    private MovieGridItem getCursorValues(Cursor c) {

        MovieGridItem foundItem = new MovieGridItem();
        foundItem.setMovieId(c.getInt(c.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_ID)));
        foundItem.setMovieAvg_vote(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_VOTE_AVG)));
        foundItem.setMovieName(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_NAME)));
        foundItem.setMovieReleaseDate(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_RELEASE_DATE)));
        foundItem.setMovieTitle(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_TITLE)));
        foundItem.setMovieBackDropPath(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_BACKDROP)));
        foundItem.setMoviePosterPath(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_POSTER)));
        foundItem.setMovieOverView(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_OVERVIEW)));

        return foundItem;
    }

    private MovieTrailer getTrailerCursorValues(Cursor c){

        MovieTrailer trailer = new MovieTrailer();
        trailer.setId(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_ID)));
        trailer.setVieoKey(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_VIDEOKEY)));
        trailer.setTrailerTitle(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_TITLE)));
        trailer.setSite(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_SITE)));
        trailer.setSize(c.getInt(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_SIZE)));
        trailer.setType(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_TYPE)));

        return trailer;
    }

    private Reviews getReviewsCursorValues(Cursor c){

        Reviews review = new Reviews();
        review.setId(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_ID)));
        review.setAuthor(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_AUTHOR)));
        review.setContent(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_CONTENT)));
        review.setUrl(c.getString(c.getColumnIndexOrThrow(FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_URL)));

        return review;
    }

    public boolean isAlreadyInserted(String movieName){

        boolean alreadyAdded = false ;

        SQLiteDatabase db = getReadDb();
        Cursor c = db.query(FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME,
                null,
                SELECT_MOVIE_NAME_BASED,
                new String[]{movieName},
                null,
                null,
                null);

        if(c != null && c.moveToFirst()){

            MovieGridItem item = getCursorValues(c);
            alreadyAdded = true ;
            Log.d(getClass().getSimpleName() , "already inserted.."+alreadyAdded);
            c.close();
            db.close();

        }

        return alreadyAdded;
    }


    private ContentValues getMovieItemCV(MovieGridItem item){
        ContentValues cv = new ContentValues();
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_ID , item.getMovieId());
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_VOTE_AVG , item.getMovieAvg_vote());
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_NAME , item.getMovieName());
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_MOVIE_TITLE , item.getMovieTitle());
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_RELEASE_DATE , item.getMovieReleaseDate());
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_BACKDROP , item.getMovieBackDropPath());
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_POSTER , item.getMoviePosterPath());
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_NAME_OVERVIEW , item.getMovieOverView());

        return cv;
    }

    private ContentValues getTrailerItemCV(MovieTrailer trailer ,int movieId){

        ContentValues cv = new ContentValues();
        cv.put(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_MOVIE_ID , movieId);
        cv.put(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_ID, trailer.getId());
        cv.put(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_VIDEOKEY, trailer.getVieoKey());
        cv.put(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_TITLE , trailer.getTrailerTitle());
        cv.put(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_SITE , trailer.getSite());
        cv.put(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_SIZE , trailer.getSize());
        cv.put(FavoriteMovieContract.MovieTrailerEntry.COLUMN_NAME_TRAILER_TYPE , trailer.getType());

        return cv;
    }

    private ContentValues getReviewItemCv(Reviews review , int movieId){

        ContentValues cv = new ContentValues();
        cv.put(FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_MOVIE_ID , movieId);
        cv.put(FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_ID , review.getId());
        cv.put(FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_AUTHOR , review.getAuthor());
        cv.put(FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_CONTENT , review.getContent());
        cv.put(FavoriteMovieContract.MovieReviewEntry.COLUMN_NAME_REVIEW_URL , review.getContent());

        return cv;
    }
}
