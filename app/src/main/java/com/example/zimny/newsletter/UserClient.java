package com.example.zimny.newsletter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ideo7 on 01.06.2017.
 */

public interface SignInterface {
    @POST("user")
    Call<User> signIn(@Body User user);
}
