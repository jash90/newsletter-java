package com.example.zimny.newsletter.Api;


import java.io.IOException;
import java.util.jar.Attributes;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken);
        Request request = builder.build();
        return chain.proceed(request);
    }

}