package com.example.administrator.on_off_button.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.on_off_button.R;

/**
 * Created by yangchun on 2016-12-7.
 */
public class TestCoordinatorActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout drawerRel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_view);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mainDrawer);
        drawerRel = (RelativeLayout) findViewById(R.id.drawerRel);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                setDrawerIndicatorEnabled(true);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                setDrawerIndicatorEnabled(true);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
//                setDrawerIndicatorEnabled(false);
//                setHomeAsUpIndicator(R.drawable.ic_launcher);
            }
        };
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //主界面左上角的icon点击反应
                mDrawerLayout.openDrawer(drawerRel);
//                mDrawerLayout.openDrawer(GravityCompat.END);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}