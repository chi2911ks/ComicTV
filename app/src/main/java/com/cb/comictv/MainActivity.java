package com.cb.comictv;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.cb.comictv.adapter.comic.ComicModel;
import com.cb.comictv.databinding.ActivityMainBinding;
import com.cb.comictv.fragment.FollowFragment;
import com.cb.comictv.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        bottomNavigationView = findViewById(R.id.bottom_nav);
        frameLayout = findViewById(R.id.frameLayout);
        replaceFragment(new HomeFragment());
        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();

            if ( itemId == R.id.home){
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.follow) {
                replaceFragment(new FollowFragment());
            }
            return true;
        });

    }
    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}