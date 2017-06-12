package com.example.zimny.newsletter.Api;

import com.example.zimny.newsletter.Model.NewsletterContent;
import com.example.zimny.newsletter.Model.Newsletters;
import com.example.zimny.newsletter.Model.User;

import retrofit2.Call;
import retrofit2.http.*;




/**
 * Created by ideo7 on 07.06.2017.
 */

public interface BeinsuredClient {
    @FormUrlEncoded
    @POST("RestAuth/signIn/")
    Call<User> login(
            @Field("login") String login,
            @Field("password") String password,
            @Field("apiKey") String apiKey);

    @GET("DefaultProfil/getListaNewsleter?apiKey=2esde2%23derdsr%23RD")
    Call<Newsletters> getListNewsletter();

    @GET("DefaultProfil/getNewsleter?apiKey=2esde2%23derdsr%23RD?newsletter=22")
    Call<NewsletterContent> getNewsletter();


}
