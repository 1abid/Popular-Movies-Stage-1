package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeThumbnailView;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.interfaces.OnItemClickListener;

/**
 * Created by VutkaBilai on 3/17/17.
 * mail : la4508@gmail.com
 */

public class MovieTrailerViewHolder extends RecyclerView.ViewHolder {

    public YouTubeThumbnailView videoThumb;

    public OnItemClickListener clickListener;

    public MovieTrailerViewHolder(final View itemView) {
        super(itemView);

        videoThumb = (YouTubeThumbnailView) itemView.findViewById(R.id.video_thmb);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener  != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                        clickListener.onItemClick(itemView , position);
                }
            }
        });
    }

    public void setListener(OnItemClickListener listener){
        this.clickListener = listener ;
    }
}
