package com.example.zimny.newsletter.Api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZimnY on 26.06.2017.
 */

public class AuthenticationInterceptorAuthtoken implements Interceptor {

    private String authToken;

    public AuthenticationInterceptorAuthtoken(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken)
                .header("Authtoken", com.example.zimny.newsletter.Activity.Attributes.getLogin_token());
        Request request = builder.build();
        return chain.proceed(request);
    }

}