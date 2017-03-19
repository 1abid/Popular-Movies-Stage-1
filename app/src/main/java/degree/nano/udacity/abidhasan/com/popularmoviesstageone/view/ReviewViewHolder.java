package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;

/**
 * Created by VutkaBilai on 3/20/17.
 * mail : la4508@gmail.com
 */

public class ReviewViewHolder extends RecyclerView.ViewHolder {


    public TextView authNameTv , contenctTv;

    public ReviewViewHolder(View itemView) {
        super(itemView);

        authNameTv = (TextView) itemView.findViewById(R.id.review_authTV);
        contenctTv = (TextView) itemView.findViewById(R.id.review_contentTV);
    }
}
