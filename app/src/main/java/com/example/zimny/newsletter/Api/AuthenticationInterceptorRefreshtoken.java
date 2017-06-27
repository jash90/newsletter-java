package com.example.zimny.newsletter.Api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ideo7 on 27.06.2017.
 */

public class AuthenticationInterceptorRefreshtoken implements Interceptor {

    private String authToken;

    public AuthenticationInterceptorRefreshtoken(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken)
                .header("Authtoken", com.example.zimny.newsletter.Activity.Attributes.getRefresh_token());
        Request request = builder.build();
        return chain.proceed(request);
    }

}