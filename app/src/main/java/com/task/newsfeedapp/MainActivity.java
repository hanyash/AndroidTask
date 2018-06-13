package com.task.newsfeedapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawer;
    NavigationView mNavigationView;
    int mSelectedNavigationItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        setupDrawerItems();

        ShowNewsFragment();
    }

    private void setupDrawerItems() {
        setupDrawerItem(R.id.nav_explore);
        setupDrawerItem(R.id.nav_live_chat);
        setupDrawerItem(R.id.nav_gallery);
        setupDrawerItem(R.id.nav_wish_list);
        setupDrawerItem(R.id.nav_e_magazine);
    }

    private void setupDrawerItem(int itemId) {
        View v = mNavigationView.getMenu().findItem(itemId).getActionView();
        if (v != null) {
            ImageView selectedImage = v.findViewById(R.id.nav_item_selected);
            if (selectedImage != null)
                selectedImage.setVisibility(itemId == mSelectedNavigationItemId ? View.VISIBLE : View.GONE);
            ImageView itemImage = v.findViewById(R.id.nav_item_icon);
            if (itemImage != null)
                itemImage.setImageResource(getNavigationItemIconId(itemId));
            TextView itemText = v.findViewById(R.id.nav_item_text);
            if (itemText != null)
                itemText.setText(getNavigationItemTextId(itemId));
        }
    }

    private void setSelectedNavigationItem(int id, int visibility) {
        View v = mNavigationView.getMenu().findItem(id).getActionView();
        if (v != null) {
            ImageView selectedImage = v.findViewById(R.id.nav_item_selected);
            if (selectedImage != null)
                selectedImage.setVisibility(visibility);
        }
    }

    private int getNavigationItemIconId(int id) {
        switch (id) {
            case R.id.nav_explore:
                return R.drawable.explore;
            case R.id.nav_live_chat:
                return R.drawable.live;
            case R.id.nav_gallery:
                return R.drawable.gallery;
            case R.id.nav_wish_list:
                return R.drawable.wishlist;
            case R.id.nav_e_magazine:
                return R.drawable.e_magazine;
            default:
                return 0;
        }
    }

    private int getNavigationItemTextId(int id) {
        switch (id) {
            case R.id.nav_explore:
                return R.string.nav_item_explore;
            case R.id.nav_live_chat:
                return R.string.nav_item_live_chat;
            case R.id.nav_gallery:
                return R.string.nav_item_gallery;
            case R.id.nav_wish_list:
                return R.string.nav_item_wish_list;
            case R.id.nav_e_magazine:
                return R.string.nav_item_e_magazine;
            default:
                return 0;
        }
    }

    private void ShowNewsFragment() {
        Fragment newFragment = new NewsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        //unselect previous item if selected
        if (mSelectedNavigationItemId != 0)
            setSelectedNavigationItem(mSelectedNavigationItemId, View.GONE);

        mSelectedNavigationItemId = item.getItemId();

        switch (mSelectedNavigationItemId) {
            case R.id.nav_explore:
                Toast.makeText(this, R.string.nav_item_explore, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_live_chat:
                Toast.makeText(this, R.string.nav_item_live_chat, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_gallery:
                Toast.makeText(this, R.string.nav_item_gallery, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_wish_list:
                Toast.makeText(this, R.string.nav_item_wish_list, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_e_magazine:
                Toast.makeText(this, R.string.nav_item_e_magazine, Toast.LENGTH_SHORT).show();
                break;

        }

        setSelectedNavigationItem(mSelectedNavigationItemId, View.VISIBLE);
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
