package com.task.newsfeedapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;

public class DetailsActivity extends AppCompatActivity {
    public final static String ARTICLE_JSON_EXTRA_KEY = "articleJson";

    Article mCurrentArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (getIntent() != null) {
            String articleJson = getIntent().getStringExtra(ARTICLE_JSON_EXTRA_KEY);
            if (articleJson != "") {
                Gson gson = new Gson();
                mCurrentArticle = gson.fromJson(articleJson, Article.class);
                if (mCurrentArticle != null)
                    displayData();

            }
        }
    }

    private void displayData() {
        final TextView title = findViewById(R.id.title);
        final TextView description = findViewById(R.id.description);
        final TextView publishedAt = findViewById(R.id.date);
        final ImageView thumbnail = findViewById(R.id.image);
        final TextView author = findViewById(R.id.author);
        final Button openWebsite = findViewById(R.id.open_website);


        title.setText(mCurrentArticle.getTitle());
        author.setText(getString(R.string.card_by_author) + " " + mCurrentArticle.getAuthor());
        description.setText(mCurrentArticle.getDescription());
        publishedAt.setText(DateFormat.getDateInstance().format(mCurrentArticle.getPublishedAt()));
        Picasso.get()
                .load(mCurrentArticle.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(thumbnail);
        openWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebsite();
            }
        });

    }

    private void openWebsite() {
        if (mCurrentArticle.mArticleUrl != null && mCurrentArticle.mArticleUrl != "") {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mCurrentArticle.mArticleUrl));
            startActivity(browserIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
