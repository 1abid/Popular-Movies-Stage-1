package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.MovieDetailMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.API_URLS;

/**
 * Created by abidhasan on 3/19/17.
 */

public  class YoutubeVideoFrgment extends YouTubePlayerFragment implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayer player ;
    private String videoKey;
    private static MovieDetailMVP.ProviedPresenterOps moviePresenter;

    public static YouTubePlayerFragment newInstance(MovieDetailMVP.ProviedPresenterOps presenter){

        moviePresenter = presenter ;
        return new YoutubeVideoFrgment();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        initialize(API_URLS.YOUTUBE_API_KEY , this);
    }

    public void setVideoKey(String vKey){
        if(videoKey != null && !videoKey.equals(vKey)){
            this.videoKey = vKey ;
            if(player !=null)
                player.cueVideo(vKey);
        }
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    @Override
    public void onDestroy() {
        if (player != null) {
            player.release();
        }
        super.onDestroy();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {

        this.player = youTubePlayer;

        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.setOnFullscreenListener((YouTubePlayer.OnFullscreenListener) moviePresenter);
        if (!restored && videoKey != null) {
            player.cueVideo(videoKey);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        this.player = null ;
    }
}
