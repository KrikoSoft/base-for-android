package com.example.receptar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.receptar.R;
import com.example.receptar.java.LoginData;
import com.google.android.material.navigation.NavigationView;

/**
 * activity which contains navigation view and fragments for user recipes or browsing recipes
 */
public class InsideActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView view = navigationView.getHeaderView(0).findViewById(R.id.nav_bar_user_name);
        view.setText(LoginData.getLoggedUserName());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserRecipesFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_user_recipes);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_user_recipes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserRecipesFragment()).commit();
                setTitle(R.string.user_recipes);
                break;
            case R.id.nav_browse_recipes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BrowseRecipesFragment()).commit();
                setTitle(R.string.browse_recipes);
                break;
            case R.id.nav_logout:
                LoginData.setLoggedUser(null);
                Toast.makeText(getApplicationContext(), R.string.logout_success, Toast.LENGTH_LONG).show();
                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
