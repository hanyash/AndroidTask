package com.task.newsfeedapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("articles")
    Call<FeedResponse> getNewsFeed(@Query("source") String source, @Query("apiKey") String apiKey);
}
