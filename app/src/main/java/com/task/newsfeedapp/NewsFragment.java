package com.task.newsfeedapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {
    private final String TAG = this.getTag();
    private final static String API_KEY = "533af958594143758318137469b41ba9";
    private final static String SOURCE = "the-next-web";

    RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private List<Article> mArticlesList;

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.news_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter == null)
            getData();
        else if (mRecyclerView.getAdapter() == null)
            mRecyclerView.setAdapter(mAdapter);
    }

    private void getData() {

        ApiInterface apiService =
                ApiClient.getClient(getActivity()).create(ApiInterface.class);

        Call<FeedResponse> call = apiService.getNewsFeed(SOURCE, API_KEY);
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                //List<Movie> movies = response.body().getResults();
                //Log.d(TAG, "Number of movies received: " + movies.size());
                if (response != null && response.body() != null && response.body().mArticles != null) {
                    mArticlesList = response.body().mArticles;
                    mAdapter = new ArticleAdapter(mArticlesList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
