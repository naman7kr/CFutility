package com.hackathon.cfutility.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.hackathon.cfutility.Fragments.Blogs;
import com.hackathon.cfutility.Fragments.CompareHandle;
import com.hackathon.cfutility.Fragments.Contests;
import com.hackathon.cfutility.Fragments.Discussions;
import com.hackathon.cfutility.Fragments.HandleInfo;
import com.hackathon.cfutility.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentTransaction ft;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        navigationView =  findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.home_container,new HandleInfo()).commit();
        navigationView.setCheckedItem(R.id.nav_handle_info);
        getSupportActionBar().setTitle("Handle Information");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.isChecked()) item.setChecked(false);
        else item.setChecked(true);


        int id = item.getItemId();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.home_container);
        if (id == R.id.nav_handle_info) {
            if(!(f instanceof HandleInfo)){
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.home_container, new HandleInfo()).commit();
                getSupportActionBar().setTitle("Handle Information");
            }
        } else if (id == R.id.nav_compare_handle) {
            if(!(f instanceof CompareHandle)){
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.home_container, new CompareHandle()).commit();
                getSupportActionBar().setTitle("Compare Handle");
            }
        } else if (id == R.id.nav_contests) {
            if(!(f instanceof Contests)){
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.home_container, new Contests()).commit();
                getSupportActionBar().setTitle("Contests");
            }
        } else if (id == R.id.nav_discussions) {
            if(!(f instanceof Discussions)){
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.home_container, new Discussions()).commit();
                getSupportActionBar().setTitle("Discussions");
            }
        } else if (id == R.id.nav_blogs) {
            if(!(f instanceof Blogs)){
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.home_container, new Blogs()).commit();
                getSupportActionBar().setTitle("Blogs");
            }
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
