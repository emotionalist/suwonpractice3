package com.cookandroid.suwonpractice3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StartActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        bottomNavigationView = findViewById(R.id.bottom_navigationview);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new RecyclerViewFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.allList:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new RecyclerViewFragment()).commit();
                        return true;
                    case R.id.map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new MapFragment()).commit();
                        return true;
                    case R.id.random:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new RandomFragment()).commit();
                        return true;
                    case R.id.mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new MyPageFragment()).commit();
                        return true;
                }
                return false;
            }
        });

    }
}
