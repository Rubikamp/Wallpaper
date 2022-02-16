package org.rubikamp.wallpaper;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_aboutus, R.id.nav_setting)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            int id = navDestination.getId();
            switch (id) {
                case R.id.nav_home:
                    Toast.makeText(MainActivity.this, "HoME", Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_profile:
                    Toast.makeText(MainActivity.this, "PROFILE", Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_aboutus:
                    Toast.makeText(MainActivity.this, "ABOUT US", Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_setting:
                    Toast.makeText(MainActivity.this, "SETTING", Toast.LENGTH_LONG).show();
                    break;
            }
        });
    }
}