package degree.nano.udacity.abidhasan.com.popularmoviesstageone.Application;

import android.app.Application;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.FontsOverride;

/**
 * Created by abidhasan on 2/26/17.
 */

public class PopularMovieApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/DroidSans.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/DroidSans.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/DroidSans.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/DroidSans.ttf");
    }
}
