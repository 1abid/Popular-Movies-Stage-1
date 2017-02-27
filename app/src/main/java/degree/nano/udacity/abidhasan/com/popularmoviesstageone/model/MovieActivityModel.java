package degree.nano.udacity.abidhasan.com.popularmoviesstageone.model;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.PopularMoviesMVP;

/**
 * Created by VutkaBilai on 2/27/17.
 * mail : la4508@gmail.com
 */

public class MovieActivityModel implements PopularMoviesMVP.ProvidedModelOps {
    @Override
    public void onDestroy(boolean isConfigurationChanging) {

    }

    @Override
    public void loadPopularMovies() {

    }

    @Override
    public void loadTopRatedMovies() {

    }

    @Override
    public int getPopularMoviesListSize() {
        return 0;
    }

    @Override
    public int getTopRatedMoviesListSize() {
        return 0;
    }
}
