package com.example.zimny.newsletter;

/**
 * Created by ideo7 on 01.06.2017.
 */
import android.os.AsyncTask;

import com.loopj.android.http.*;
public class BeinsuredRestClient{
    private static final String BASE_URL = "http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/RestAuth/";

    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setBasicAuth("beinsured","beinsu12");
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setBasicAuth("beinsured","beinsu12");
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
