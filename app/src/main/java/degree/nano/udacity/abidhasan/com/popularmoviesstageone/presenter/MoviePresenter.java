package degree.nano.udacity.abidhasan.com.popularmoviesstageone.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common.ToastMaker;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.MVP_INTERFACES.PopularMoviesMVP;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.adapters.PopularMovieAdapter;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.adapters.TopRatedMovieAdapter;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.interfaces.OnItemClickListener;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.model.PopularTopRatedMovieModels.MovieGridItem;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.API_URLS;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.GridSpacingItemDecoration;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MovieViewHolder;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.view.MainActivity;

/**
 * Created by VutkaBilai on 2/27/17.
 * mail : la4508@gmail.com
 */

public class MoviePresenter implements PopularMoviesMVP.RequiredPresenterOps
        , PopularMoviesMVP.ProvidedPresenterOps {

    private WeakReference<PopularMoviesMVP.RequiredViewOps> mView;

    //model reference
    private PopularMoviesMVP.ProvidedModelOps mModel;

    private PopularMovieAdapter popularMovieadpter;
    private TopRatedMovieAdapter topRatedMovieAdapter;
    private RecyclerView movieRV;

    private List<MovieGridItem> mPopularMovies;
    private List<MovieGridItem> mTopRatedMovies;

    private boolean isPopularSelected = true;

    public MoviePresenter(PopularMoviesMVP.RequiredViewOps mView) {
        this.mView = new WeakReference<PopularMoviesMVP.RequiredViewOps>(mView);

        mPopularMovies = new ArrayList<>();
        mTopRatedMovies = new ArrayList<>();
    }


    /**
     * called by activity every time during
     * setting up MVP , only called once
     *
     * @param model
     */
    public void setModel(PopularMoviesMVP.ProvidedModelOps model) {
        this.mModel = model;
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

    /**
     * called by {@link MainActivity}
     * during the reconstruction event
     *
     * @param view
     */
    @Override
    public void setView(PopularMoviesMVP.RequiredViewOps view) {
        mView = new WeakReference<PopularMoviesMVP.RequiredViewOps>(view);
    }

    @Override
    public boolean onCreateOptionMenu(Menu menu) {

        MenuInflater inflater = new MenuInflater(getActivityContext());
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public void popularMoviesSelected() {
        if (isPopularSelected())
            mModel.loadPopularMovies();
    }


    @Override
    public void topRatedMoviesSelected() {
        if (!isPopularSelected())
            mModel.loadTopRatedMovies();
    }


    /**
     * show progressDialog
     * for showing network
     * calling progress
     */
    @Override
    public ProgressDialog createProgressDialog() {

        ProgressDialog pDialog = new ProgressDialog(getView().getActivityContext()
                , R.style.AppTheme_Dark_Dialog);

        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);

        return pDialog;
    }

    @Override
    public void setProgressDialogMsg(String msg, ProgressDialog progressDialog) {
        getView().getProgressDialog().setMessage(msg);
    }

    @Override
    public void loadPopularMovies() {

        if (mPopularMovies.size() == 0)
            mModel.loadPopularMovies();
        else
            showPopularMovies();
    }

    @Override
    public void loadTopRatedMovies() {

        if (mTopRatedMovies.size() == 0)
            mModel.loadTopRatedMovies();
        else
            showTopRatedMovies();
    }


    public void showPopularMovies() {

        if (movieRV == null) {
            movieRV = getView().getRecyclrView();

            GridLayoutManager mLayoutManager = new GridLayoutManager(getActivityContext(), 2);
            movieRV.setLayoutManager(mLayoutManager);

            int spacingInPixels = getAppContext().getResources().getDimensionPixelSize(R.dimen.grid_item_space);
            movieRV.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true, 0));
        }


        if (mPopularMovies.size() == 0)
            addPopularMovieGriditem();

        popularMovieadpter = new PopularMovieAdapter(this);
        movieRV.setAdapter(popularMovieadpter);

        popularMovieadpter.notifyDataSetChanged();
    }


    public void showTopRatedMovies() {


        if (mTopRatedMovies.size() == 0)
            addTopRatedMoviesGridItem();

        topRatedMovieAdapter = new TopRatedMovieAdapter(this);
        movieRV.swapAdapter(topRatedMovieAdapter, true);
        topRatedMovieAdapter.notifyDataSetChanged();
    }

    /**
     * add top rated Movies grid
     * to the list
     */
    private void addTopRatedMoviesGridItem() {

        for (MovieGridItem item : getTopratedMovies()) {
            mTopRatedMovies.add(item);
        }
    }


    /**
     * add popular Movies grid
     * to the list
     */
    private void addPopularMovieGriditem() {

        for (MovieGridItem item : getPopularMovies()) {
            mPopularMovies.add(item);
        }

        Log.d(getClass().getSimpleName(), "popularMovies list " + mPopularMovies.size()
                + " isSelectedPopular " + isPopularSelected());
    }

    private List<MovieGridItem> getPopularMovies() {
        return mModel.generatePopularMovieGridItems();
    }

    private List<MovieGridItem> getTopratedMovies() {
        return mModel.generateTopRatedMovieGridItems();
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

    @Override
    public MovieViewHolder createPopularViewHolder(ViewGroup parent, int viewType) {

        MovieViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View movieItem = inflater.inflate(R.layout.movie_item, parent, false);
        viewHolder = new MovieViewHolder(movieItem);

        return viewHolder;
    }

    @Override
    public void bindPopularViewHolder(MovieViewHolder holder, int position) {

        MovieGridItem movieItem = mPopularMovies.get(position);
        bindMovieData(holder, movieItem);

    }

    @Override
    public int getPopularItemCount() {
        return mPopularMovies.size();
    }

    @Override
    public MovieViewHolder createTopRatedViewHolder(ViewGroup parent, int viewType) {

        MovieViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View movieItem = inflater.inflate(R.layout.movie_item, parent, false);
        viewHolder = new MovieViewHolder(movieItem);

        return viewHolder;
    }

    @Override
    public void bindTopratedViewHolder(MovieViewHolder holder, int position) {
        MovieGridItem movieItem = mTopRatedMovies.get(position);
        bindMovieData(holder, movieItem);

    }

    @Override
    public int getTopRatedItemCount() {
        return mTopRatedMovies.size();
    }


    /**
     * return the view reference.
     * could throw nullpinter exception
     * if view is null
     *
     * @return {@link PopularMoviesMVP.RequiredViewOps}
     * @throws NullPointerException
     */

    public PopularMoviesMVP.RequiredViewOps getView() throws NullPointerException {

        if (mView != null)
            return mView.get();
        else
            throw new NullPointerException("view is unavailable");
    }

    private void bindMovieData(MovieViewHolder holder, final MovieGridItem item) {

        holder.movietile.setText(item.getMovieName());
        holder.movieReleaseDate.setText(item.getMovieReleaseDate());
        holder.movieRating.setText(item.getMovieAvg_vote());

        ViewTarget target = new ViewTarget<ImageView, GlideDrawable>(holder.moviePosterIv) {

            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImageDrawable(resource.getCurrent());
            }
        };

        Glide.with(getView().getAppContext())
                .load(API_URLS.IMAGE_PATH + item.getMoviePosterPath()).crossFade().fitCenter()
                .into(target);

        holder.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                getView().goToDetailActivity(item);
            }
        });

    }

    @Override
    public boolean isPopularSelected() {
        return isPopularSelected;
    }

    @Override
    public void setPopularSelected(boolean popularSelected) {
        isPopularSelected = popularSelected;
    }
}
