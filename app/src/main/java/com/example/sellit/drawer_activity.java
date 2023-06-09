package com.example.sellit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class drawer_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private androidx.drawerlayout.widget.DrawerLayout drawer;
//    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);

        drawer = (androidx.drawerlayout.widget.DrawerLayout) findViewById(R.id.drawer_layout);

        com.google.android.material.navigation.NavigationView navigationView = (com.google.android.material.navigation.NavigationView)
                findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new home_fragement()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int menu_id = menuItem.getItemId();
        if (menu_id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new home_fragement()).commit();
        }

        else if (menu_id == R.id.nav_my_profile) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new profile_fragment()).commit();
        }
        else if (menu_id == R.id.nav_sell) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new sell_fragment()).commit();
        }
        else if (menu_id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, login_activity.class));
        }
        else if (menu_id == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new AboutFragment()).commit();
        }
        else if (menu_id == R.id.nav_feedback) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new FeedbackFragment()).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
