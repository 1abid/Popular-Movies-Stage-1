package degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common;

import android.util.Log;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.Map;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;

/**
 * Created by VutkaBilai on 3/17/17.
 * mail : la4508@gmail.com
 */

public class ThumbnailListener implements YouTubeThumbnailView.OnInitializedListener ,
        YouTubeThumbnailLoader.OnThumbnailLoadedListener{

    private Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;

    public ThumbnailListener(Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap) {
        this.thumbnailViewToLoaderMap = thumbnailViewToLoaderMap;
    }

    @Override
    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {

    }

    @Override
    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
        youTubeThumbnailView.setImageResource(R.drawable.no_thumbnail);
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {


        youTubeThumbnailLoader.setOnThumbnailLoadedListener(this);
        thumbnailViewToLoaderMap.put(youTubeThumbnailView , youTubeThumbnailLoader);
        youTubeThumbnailView.setImageResource(R.drawable.loading_thumbnail);
        String videId = (String) youTubeThumbnailView.getTag();
        youTubeThumbnailLoader.setVideo(videId);


    }


    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
        youTubeThumbnailView.setImageResource(R.drawable.no_thumbnail);
    }


}
