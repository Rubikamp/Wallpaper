package org.rubikamp.wallpaper;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.rubikamp.wallpaper.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavController navController;
    private ActionBarDrawerToggle drawerToggle;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navController = Navigation.findNavController(this, R.id.nav_fragment);
        setupBottomNav();
        setupDrawerNav();

    }


    private void setupDrawerNav() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerToggle = setupDrawerToggle();
        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        // Tie DrawerLayout events to the ActionBarToggle
        binding.drawerLayout.addDrawerListener(drawerToggle);

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout);
        NavigationUI.setupWithNavController(binding.navigationView, navController);
        binding.navigationView.setNavigationItemSelectedListener(this);


    }

    private void setupBottomNav() {
        NavigationUI.setupWithNavController(binding.bottomNavigatinView, navController);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_profile, R.id.nav_aboutus, R.id.nav_setting)
//                .build();

        //        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
//            int id = navDestination.getId();
//            switch (id) {
//                case R.id.nav_home:
//                    Toast.makeText(MainActivity.this, "HoME", Toast.LENGTH_LONG).show();
//                    break;
//                case R.id.nav_profile:
//                    Toast.makeText(MainActivity.this, "PROFILE", Toast.LENGTH_LONG).show();
//                    break;
//                case R.id.nav_aboutus:
//                    Toast.makeText(MainActivity.this, "ABOUT US", Toast.LENGTH_LONG).show();
//                    break;
//                case R.id.nav_setting:
//                    Toast.makeText(MainActivity.this, "SETTING", Toast.LENGTH_LONG).show();
//                    break;
//            }
//        });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public void onBackPressed() {
        if ( binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        binding.drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nav_home:
                navController.navigate(R.id.nav_home);
                break;

            case R.id.nav_profile:
                navController.navigate(R.id.nav_profile);
                break;

            case R.id.nav_aboutus:
                navController.navigate(R.id.nav_aboutus);
                break;

            case R.id.nav_setting:
                navController.navigate(R.id.nav_setting);
                break;

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}