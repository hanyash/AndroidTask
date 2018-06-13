package com.task.newsfeedapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    List<Article> mArticlesList;

    public ArticleAdapter(List<Article> articles) {
        mArticlesList = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_row, parent, false);

        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, final int position) {
        Article article = mArticlesList.get(position);
        holder.mTitle.setText(article.getTitle());
        holder.mAuthor.setText(holder.mAuthor.getContext().getString(R.string.card_by_author) + " " + article.getAuthor());
        holder.mPublishedAt.setText(DateFormat.getDateInstance().format(article.getPublishedAt()));
        Picasso.get()
                .load(article.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.mArticleImage);
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                Article article = mArticlesList.get(position);
                String articleJson = gson.toJson(article);
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.ARTICLE_JSON_EXTRA_KEY, articleJson);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticlesList != null ? mArticlesList.size() : 0;
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        public View mRootView;
        public TextView mAuthor;
        public TextView mTitle;
        public ImageView mArticleImage;
        public TextView mPublishedAt;

        public ArticleViewHolder(View view) {
            super(view);
            mAuthor = (TextView) view.findViewById(R.id.author);
            mTitle = (TextView) view.findViewById(R.id.title);
            mArticleImage = (ImageView) view.findViewById(R.id.image);
            mPublishedAt = (TextView) view.findViewById(R.id.date);
            mRootView = view;
        }
    }


}
