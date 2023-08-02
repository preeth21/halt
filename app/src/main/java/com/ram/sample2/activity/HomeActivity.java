package com.ram.sample2.activity;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ram.sample2.fragment.HomeFragment;
import com.ram.sample2.fragment.LogsFragment;
import com.ram.sample2.R;
import com.ram.sample2.fragment.SupportFragment;
import com.ram.sample2.fragment.TrackFragment;
import com.ram.sample2.fragment.AlertsFragment;
import com.ram.sample2.fragment.FindFragment;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawerLayout;
    public CoordinatorLayout coordinatorLayout;
    public FrameLayout frameLayout;
    public Toolbar toolbar;
    public NavigationView navigationView;

    public  FirebaseAuth auth;
    public  FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        drawerLayout = findViewById(R.id.drawerLayout);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        frameLayout = findViewById(R.id.frameLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment())
                .commit();

        setupToolbar();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawers();
            return;
        }

        Fragment fragment= getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        if(fragment.getClass()!= HomeFragment.class){

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment())
                    .commit();
            getSupportActionBar().setTitle("Home...");
        }else super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.END);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home...");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().home
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int i = menuItem.getItemId();
        if (i == R.id.itBus) {
            Toast.makeText(this, "More info", Toast.LENGTH_SHORT).show();
           getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment())
                   .commit();
            getSupportActionBar().setTitle("Home...");
           drawerLayout.closeDrawers();
        } else if (i == R.id.itLogs) {
            Toast.makeText(this, "Logs", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new LogsFragment())
                    .commit();
            getSupportActionBar().setTitle("Logs...");
            drawerLayout.closeDrawers();
        } else if (i == R.id.itStops) {
            Toast.makeText(this, "Find alternate stops", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new FindFragment())
                    .commit();
            getSupportActionBar().setTitle("Find stops...");
            drawerLayout.closeDrawers();
        } else if (i == R.id.itAlerts) {
            Toast.makeText(this, "Alerts", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new AlertsFragment())
                    .commit();
            getSupportActionBar().setTitle("Alerts...");
            drawerLayout.closeDrawers();

        } else if (i == R.id.itTrack) {
            Toast.makeText(this, "Track your bus", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new TrackFragment())
                    .commit();
            getSupportActionBar().setTitle("Track bus...");
            drawerLayout.closeDrawers();

        } else if (i == R.id.itSupport) {
            Toast.makeText(this, "Support", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new SupportFragment())
                    .commit();
            getSupportActionBar().setTitle("Help...");
            drawerLayout.closeDrawers();

        } else if (i==R.id.itLogout) {
            Toast.makeText(this, "Bye!!", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }

        return true;
    }
}