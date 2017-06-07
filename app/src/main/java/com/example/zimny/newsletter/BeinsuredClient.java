package com.example.zimny.newsletter;

import retrofit2.Call;
import retrofit2.http.*;


/**
 * Created by ideo7 on 07.06.2017.
 */

public interface BeinsuredClient {
    @POST("/signIn")
    Call<User> login(@Body Guest guest);
}
