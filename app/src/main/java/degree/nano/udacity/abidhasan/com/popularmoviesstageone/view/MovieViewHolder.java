package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.interfaces.OnItemClickListener;

/**
 * Created by abidhasan on 2/26/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder{

    public RelativeLayout container;
    public TextView movietile , movieReleaseDate , movieRating ;
    public ImageView moviePosterIv;

    public OnItemClickListener listener;

    public MovieViewHolder(final View itemView) {
        super(itemView);


        container = (RelativeLayout) itemView.findViewById(R.id.movie_item_container);
        movietile = (TextView) itemView.findViewById(R.id.movie_title_tv);
        movieReleaseDate = (TextView) itemView.findViewById(R.id.release_tv);
        movieRating = (TextView) itemView.findViewById(R.id.vote_avg_tv);
        moviePosterIv = (ImageView) itemView.findViewById(R.id.movie_poster);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                        listener.onItemClick(itemView , position);
                }
            }
        });
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener ;
    }
}
