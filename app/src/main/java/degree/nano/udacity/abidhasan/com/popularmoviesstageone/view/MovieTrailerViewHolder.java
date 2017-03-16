package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.interfaces.OnItemClickListener;

/**
 * Created by VutkaBilai on 3/17/17.
 * mail : la4508@gmail.com
 */

public class MovieTrailerViewHolder extends RecyclerView.ViewHolder {

    public ImageView videoThumb;

    public OnItemClickListener clickListener;

    public MovieTrailerViewHolder(View itemView) {
        super(itemView);

        videoThumb = (ImageView) itemView.findViewById(R.id.video_thmb);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void setListener(OnItemClickListener listener){
        this.clickListener = listener ;
    }
}
