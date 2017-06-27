package com.example.zimny.newsletter.Api;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import cz.msebera.android.httpclient.util.TextUtils;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ideo7 on 07.06.2017.
 */

public class ServiceGenerator {

    public static final String API_BASE_URL = "http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/")
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {

        httpClient.addNetworkInterceptor(new StethoInterceptor());
        builder.client(httpClient.build());
        retrofit = builder.build();
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, String username, String password) {

        httpClient.addNetworkInterceptor(new StethoInterceptor());
        builder.client(httpClient.build());
        retrofit = builder.build();
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null, null);
    }
    public static <S> S createServiceAuthtoken(
            Class<S> serviceClass, String username, String password) {

        httpClient.addNetworkInterceptor(new StethoInterceptor());
        builder.client(httpClient.build());
        retrofit = builder.build();
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createServiceAuthtoken(serviceClass, authToken);
        }

        return createService(serviceClass, null, null);
    }
    public static <S> S createServiceRefreshtoken(
            Class<S> serviceClass, String username, String password) {

        httpClient.addNetworkInterceptor(new StethoInterceptor());
        builder.client(httpClient.build());
        retrofit = builder.build();
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createServiceRefreshtoken(serviceClass, authToken);
        }

        return createService(serviceClass, null, null);
    }
    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        httpClient.addNetworkInterceptor(new StethoInterceptor());
        builder.client(httpClient.build());
        retrofit = builder.build();
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder newrequest = request.newBuilder();
                    return chain.proceed(newrequest.build());
                }
            });
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
    public static <S> S createServiceAuthtoken(
            Class<S> serviceClass, final String authToken) {
        httpClient.addNetworkInterceptor(new StethoInterceptor());
        builder.client(httpClient.build());
        retrofit = builder.build();
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptorAuthtoken interceptor =
                    new AuthenticationInterceptorAuthtoken(authToken);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder newrequest = request.newBuilder();
                    return chain.proceed(newrequest.build());
                }
            });
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
    public static <S> S createServiceRefreshtoken(
            Class<S> serviceClass, final String authToken) {
        httpClient.addNetworkInterceptor(new StethoInterceptor());
        builder.client(httpClient.build());
        retrofit = builder.build();
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptorRefreshtoken interceptor =
                    new AuthenticationInterceptorRefreshtoken(authToken);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder newrequest = request.newBuilder();
                    return chain.proceed(newrequest.build());
                }
            });
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}