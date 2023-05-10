package com.example.virtualclinic;

import androidx.annotation.FractionRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.virtualclinic.databinding.ActivityMainBottomTabsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainBottomTabsActivity extends AppCompatActivity {
    ActivityMainBottomTabsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBottomTabsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // bottomNavigationView = findViewById(R.id.bottom_navigaton);
        loadFragment(new HomeFragment());
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        //by default ye wali screen show kry ga ab

        //bottomNavigationView.setSelectedItemId(R.id.nav_home);

        binding.bottomNavigaton.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home: {
                        loadFragment(new HomeFragment());
                        break;
                    }
                    case R.id.nav_ongoing: {
                        loadFragment(new OngoingCasesFragment());
                        break;
                    }
                    case R.id.nav_reports: {
                        loadFragment ( new ReportsFragment());
                        break;
                    }
                }
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;

            }
        });


    }

    private void loadFragment(Fragment f) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction ft =
                fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.commit();

    }
}