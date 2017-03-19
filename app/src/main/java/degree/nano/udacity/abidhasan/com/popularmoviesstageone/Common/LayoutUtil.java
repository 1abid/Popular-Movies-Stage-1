package degree.nano.udacity.abidhasan.com.popularmoviesstageone.Common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by abidhasan on 3/19/17.
 */

public class LayoutUtil {

    private Context context;

    public LayoutUtil(Context context){
        this.context = context ;
    }

    public int dpTopx(int dp){
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public void setLayoutSize(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }


    public static void setLayoutSizeAndGravity(View view, int width, int height, int gravity) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        params.gravity = gravity;
        view.setLayoutParams(params);
    }
}
