package degree.nano.udacity.abidhasan.com.popularmoviesstageone.Application;

import android.text.TextUtils;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.rest.AuthenticationInterceptor;
import degree.nano.udacity.abidhasan.com.popularmoviesstageone.util.API_URLS;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VutkaBilai on 2/27/17.
 * mail : la4508@gmail.com
 */

public class RestServiceGenerator  {

    private static final String baseUrl = API_URLS.BASE_URL ;

    private static Retrofit retrofit ;

    public static Retrofit retrofit(){
        return retrofit;
    }

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass){
        return createService(serviceClass , null , null);
    }

    private static <S> S createService(Class<S> serviceClass, String  userName, String password) {
        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)){

            //now authenticate user with OkhhtpClient authentication interceptor
            String credentials = Credentials.basic(userName , password);

            // add auth interceptor using the created credentials
            httpClient.addInterceptor(new AuthenticationInterceptor(credentials));
        }


        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }


    /**method to change base url for a api service**/
    public void changeBaseURl(String url){
        builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());
    }
}
