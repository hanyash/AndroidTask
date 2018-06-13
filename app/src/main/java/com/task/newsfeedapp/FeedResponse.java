package com.task.newsfeedapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedResponse {
    @SerializedName("status")
    String mStatus;

    @SerializedName("source")
    String mSource;

    @SerializedName("sortBy")
    String mSortBy;

    @SerializedName("articles")
    List<Article> mArticles;
}
