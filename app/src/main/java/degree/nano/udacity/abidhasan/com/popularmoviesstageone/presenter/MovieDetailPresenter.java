package degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.LayoutUtil;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ToastMaker;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.adapters.ReviewAdapter;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.adapters.TrailerAdpter;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.interfaces.OnItemClickListener;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieReviewsModel.Reviews;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.movieTrailerModel.MovieTrailer;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.API_URLS;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieTrailerViewHolder;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.ReviewViewHolder;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by abidhasan on 3/6/17.
 */

public class MovieDetailPresenter implements MovieDetailMVP.ProviedPresenterOps
        , MovieDetailMVP.RequiredPresenterOps, YouTubePlayer.OnFullscreenListener {


    private WeakReference<MovieDetailMVP.RequiredViewOps> mView;

    private MovieDetailMVP.ProvidedModelOps mModel;

    private ArrayList<MovieTrailer> trailers;
    private ArrayList<Reviews> reviewes;

    private TrailerAdpter trailerAdpter;
    private ReviewAdapter reviewAdapter;



    /**youtube video loading**/
    /**
     * The duration of the animation sliding up the video in portrait.
     */
    public static final int ANIMATION_DURATION_MILLIS = 300;
    /**
     * The padding between the video list and the video in landscape orientation.
     */
    public static final int LANDSCAPE_VIDEO_PADDING_DP = 5;

    /**
     * The request code when calling startActivityForResult to recover from an API service error.
     */
    public static final int RECOVERY_DIALOG_REQUEST = 1;

    private boolean isFullscreen;

    private LayoutUtil layoutUtil;

    public MovieDetailPresenter(MovieDetailMVP.RequiredViewOps mView) {
        this.mView = new WeakReference<MovieDetailMVP.RequiredViewOps>(mView);

        trailers = new ArrayList<>();
        reviewes = new ArrayList<>();

        layoutUtil = new LayoutUtil(getAppContext());
    }

    /**
     * called by activity every time during
     * setting up MVP , only called once
     *
     * @param mModel
     */
    public void setModel(MovieDetailMVP.ProvidedModelOps mModel) {
        this.mModel = mModel;
    }

    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        //view should be null every time onDestroy is called
        mView = null;

        //inform model about the event
        mModel.onDestroy(isChangingConfigurations);

        //activity destroyed
        if (!isChangingConfigurations) {
            mModel = null;
        }


    }

    @Override
    public void onConfigurationChanged(MovieDetailMVP.RequiredViewOps view) {
        setView(view);

        //configLayout();
    }


    /**
     * called by {@link degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieDetailActivity}
     *
     * @param view
     */
    @Override
    public void setView(MovieDetailMVP.RequiredViewOps view) {
        this.mView = new WeakReference<MovieDetailMVP.RequiredViewOps>(view);
    }

    @Override
    public void checkYourYoutubeApi() {
        YouTubeInitializationResult errorReason =
                YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivityContext());
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog((Activity) getView().getActivityContext(), RECOVERY_DIALOG_REQUEST).show();
        } else if (errorReason != YouTubeInitializationResult.SUCCESS) {
            String errorMessage =
                    String.format(getActivityContext().getString(R.string.error_player), errorReason.toString());
            getView().showToast(ToastMaker.makeToast(getActivityContext(), errorMessage));
        }
    }


    @Override
    public void setProgressDialogMsg(String msg) {
        getView().getProgressDialog().setMessage(msg);
    }

    @Override
    public void loadMovieDetail(MovieGridItem movie) {

        setProgressDialogMsg("Loading...");
        getView().showProgressDialog();

        getView().getTitleTv().setText(movie.getMovieTitle());
        getView().getRatingTv().setText(movie.getMovieAvg_vote());
        getView().getRelaseDateTv().setText("Release date : " + movie.getMovieReleaseDate());
        getView().getOverViewTv().setText(movie.getMovieOverView());
        loadImage(getView().getBackdropImageView(), movie.getMovieBackDropPath());
        loadImage(getView().getPosterImageView(), movie.getMoviePosterPath());

        mModel.getMovieTrailers(getView().getmovieId());
    }

    @Override
    public void onTrailerDownload(ArrayList<MovieTrailer> trailerArrayList ,ArrayList<Reviews> reviewsArrayList) {

        trailers = trailerArrayList;
        reviewes = reviewsArrayList;

        trailerAdpter = new TrailerAdpter(this);
        RecyclerView trailerRv = InitializeRv(getView().getTrailerRV() , LinearLayoutManager.HORIZONTAL);
        trailerRv.setAdapter(trailerAdpter);

        reviewAdapter = new ReviewAdapter(this);
        RecyclerView reviewRv = InitializeRv(getView().getReviewRv() ,LinearLayoutManager.VERTICAL);
        reviewRv.setAdapter(reviewAdapter);
    }

    private RecyclerView InitializeRv(RecyclerView rv , int orientation) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivityContext(), orientation, false);
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                layoutManager.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);


        return rv;
    }



    @Override
    public MovieTrailerViewHolder createTrailerViewHolder(ViewGroup parent, int viewType) {

        MovieTrailerViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.movie_trailer_card, parent, false);


        viewHolder = new MovieTrailerViewHolder(v);


        return viewHolder;
    }

    @Override
    public void bindTrailerViewHolder(MovieTrailerViewHolder holder, int position) {
        MovieTrailer trailer = trailers.get(position);
        bindTrailerData(holder, trailer);
    }

    private void bindTrailerData(MovieTrailerViewHolder holder, final MovieTrailer trailer) {

        holder.videoThumb.initialize(API_URLS.YOUTUBE_API_KEY, getView().getThumbListener());

        YouTubeThumbnailLoader loader = getView().getThumbMap().get(holder.videoThumb);
        if (loader == null) {
            // 2) The view is already created, and is currently being initialized. We store the
            //    current videoId in the tag.
            holder.videoThumb.setTag(trailer.getVieoKey());
        } else {
            // 3) The view is already created and already initialized. Simply set the right videoId
            //    on the loader.
            holder.videoThumb.setImageResource(R.drawable.loading_thumbnail);
            loader.setVideo(trailer.getVieoKey());
        }
        holder.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                String videoId = trailers.get(position).getVieoKey();

                getView().playVideo(videoId);

                /*YoutubeVideoFrgment videoFragment = getView().getVideoContainer();
                View videoBox = getView().getVideoBox() ;
                videoFragment.setVideoKey(videoId);

                // The videoBox is INVISIBLE if no video was previously selected, so we need to show it now.
                if (getView().getVideoBox().getVisibility() != View.VISIBLE) {
                    if (getActivityContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        // Initially translate off the screen so that it can be animated in from below.

                        videoBox.setTranslationY(videoBox.getHeight());
                    }
                    videoBox.setVisibility(View.VISIBLE);
                }

                // If the fragment is off the screen, we animate it in.
                if (videoBox.getTranslationY() > 0) {
                    videoBox.animate().translationY(0).setDuration(ANIMATION_DURATION_MILLIS);
                }*/
            }
        });
    }

    @Override
    public int getTrailerCount() {
        return trailers.size();
    }

    @Override
    public ReviewViewHolder createReviewViewHolder(ViewGroup parent, int viewType) {

        ReviewViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.review_item, parent, false);


        viewHolder = new ReviewViewHolder(v);


        return viewHolder;
    }

    @Override
    public void bindReviewViewHolder(ReviewViewHolder holder, int position) {
        Reviews review = reviewes.get(position);

        holder.authNameTv.setText(review.getAuthor());
        holder.contenctTv.setText(review.getContent());
    }

    @Override
    public int getReviewCount() {
        return reviewes.size();
    }

    @Override
    public void onClickClose() {
        getView().getVideoContainer().pause();
        View videoBox = getView().getVideoBox();
        ViewPropertyAnimator animator = videoBox.animate()
                .translationYBy(videoBox.getHeight())
                .setDuration(ANIMATION_DURATION_MILLIS);
        runOnAnimationEnd(animator, new Runnable() {
            @Override
            public void run() {
                getView().getVideoBox().setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onMovieFbClick() {
        getView().showToast(ToastMaker.makeToast(getActivityContext()
                ,"Selected movie : "+ getView().getSelectedMovie().getMovieName()));
    }

    /**
     * Sets up the layout programatically for the three different states. Portrait, landscape or
     * fullscreen+landscape. This has to be done programmatically because we handle the orientation
     * changes ourselves in order to get fluent fullscreen transitions, so the xml layout resources
     * do not get reloaded.
     */
    @Override
    public void configLayout() {

        boolean isPortrait =
                getActivityContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        getView().getTrailerRV().setVisibility(isFullscreen ? View.GONE : View.VISIBLE);
        getView().getCloseButton().setVisibility(isFullscreen ? View.GONE : View.VISIBLE);

        if (isFullscreen) {
            getView().getVideoBox().setTranslationY(0); // Reset any translation that was applied in portrait.
            layoutUtil.setLayoutSize(getView().getVideoContainer().getView(), MATCH_PARENT, MATCH_PARENT);
            layoutUtil.setLayoutSizeAndGravity(getView().getVideoBox(), MATCH_PARENT, MATCH_PARENT, Gravity.TOP | Gravity.LEFT);
        } else if (isPortrait) {
            layoutUtil.setLayoutSize(getView().getTrailerRV(), MATCH_PARENT, MATCH_PARENT);
            layoutUtil.setLayoutSize(getView().getVideoContainer().getView(), MATCH_PARENT, WRAP_CONTENT);
            layoutUtil.setLayoutSizeAndGravity(getView().getVideoBox(), MATCH_PARENT, WRAP_CONTENT, Gravity.BOTTOM);
        } else {
            getView().getVideoBox().setTranslationY(0); // Reset any translation that was applied in portrait.
            int screenWidth = layoutUtil.dpTopx(getActivityContext().getResources().getConfiguration().screenWidthDp);
            layoutUtil.setLayoutSize(getView().getTrailerRV(), screenWidth / 4, MATCH_PARENT);
            int videoWidth = screenWidth - screenWidth / 4 - layoutUtil.dpTopx(LANDSCAPE_VIDEO_PADDING_DP);
            layoutUtil.setLayoutSize(getView().getVideoContainer().getView(), videoWidth, WRAP_CONTENT);
            layoutUtil.setLayoutSizeAndGravity(getView().getVideoBox(), videoWidth, WRAP_CONTENT,
                    Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        }
    }

    @TargetApi(16)
    private void runOnAnimationEnd(ViewPropertyAnimator animator, final Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            animator.withEndAction(runnable);
        } else {
            animator.setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    runnable.run();
                }
            });
        }
    }

    /**
     * load image into a
     * imageView with glide
     *
     * @param imageView
     * @param imagePath
     */
    private void loadImage(ImageView imageView, String imagePath) {


        ViewTarget target = new ViewTarget<ImageView, GlideDrawable>(imageView) {

            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImageDrawable(resource.getCurrent());
            }
        };


        Glide.with(getView().getAppContext())
                .load(API_URLS.IMAGE_PATH + imagePath).crossFade().fitCenter()
                .into(target);
    }


    @Override
    public Context getAppContext() {
        try {

            return getView().getAppContext();

        } catch (NullPointerException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public Context getActivityContext() {
        try {

            return getView().getActivityContext();

        } catch (NullPointerException e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * return the view reference.
     * could throw nullpinter exception
     * if view is null
     *
     * @return {@link MovieDetailMVP.RequiredViewOps}
     * @throws NullPointerException
     */
    public MovieDetailMVP.RequiredViewOps getView() throws NullPointerException {

        if (mView != null)
            return mView.get();
        else
            throw new NullPointerException("view is unavailable");

    }

    @Override
    public void onFullscreen(boolean b) {
        this.isFullscreen = b;

        //configLayout();
    }
}
