package com.lcc.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lcc.base.BaseActivity;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // checkNetWork();[此处在5.0+you bug]
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        intNavigationView();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    private void intNavigationView() {
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        MainNavigationHeader mMainNavigationHeader = new MainNavigationHeader(this, mNavigationView);
        mMainNavigationHeader.bindData();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            Toast.makeText(MainActivity.this,"nav_camera",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(MainActivity.this,"nav_gallery",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(MainActivity.this,"nav_slideshow",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(MainActivity.this,"nav_manage",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(MainActivity.this,"nav_share",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(MainActivity.this,"nav_send",Toast.LENGTH_LONG).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
