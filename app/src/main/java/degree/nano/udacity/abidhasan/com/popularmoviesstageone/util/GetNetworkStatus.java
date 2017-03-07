package degree.nano.udacity.abidhasan.com.popularmoviesstageone.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by VutkaBilai on 3/8/17.
 * mail : la4508@gmail.com
 */

public class GetNetworkStatus {

    private Context context;

    public GetNetworkStatus(Context context) {
        this.context = context;
    }

    public  boolean isOnline(){
        ConnectivityManager cm =
                (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
