package com.task.newsfeedapp;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Article {

    @SerializedName("author")
    String mAuthor;

    @SerializedName("title")
    String mTitle;

    @SerializedName("description")
    String mDescription;

    @SerializedName("url")
    String mArticleUrl;

    @SerializedName("urlToImage")
    String mImageUrl;

    @SerializedName("publishedAt")
    Date mPublishedAt;

    public Article(String author, String title, String description, String url, String imageUrl, Date publishedAt) {
        mAuthor = author;
        mTitle = title;
        mDescription = description;
        mArticleUrl = url;
        mImageUrl = imageUrl;
        mPublishedAt = publishedAt;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getArticleUrl() {
        return mArticleUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public Date getPublishedAt() {
        return mPublishedAt;
    }


}
