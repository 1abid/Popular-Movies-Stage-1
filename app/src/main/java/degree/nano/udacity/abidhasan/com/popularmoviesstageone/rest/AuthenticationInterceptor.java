package degree.nano.udacity.abidhasan.com.popularmoviesstageone.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AuthenticationInterceptor implements Interceptor {

    private static final String TAG_AUTHORIGATION = "Authorization";

    private String authToken;

    public AuthenticationInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();


        // set or override the `Authorization` header
        // keep the request body
        Request.Builder builder = original.newBuilder()
                .header(TAG_AUTHORIGATION , authToken)
                .method(original.method() , original.body());

        Request request = builder.build();
        return chain.proceed(request);
    }
}
