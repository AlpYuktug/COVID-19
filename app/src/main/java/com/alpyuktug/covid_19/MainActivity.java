package com.alpyuktug.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);

        Fragment fragment = new FragmentDashboard();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FragmentContent, fragment)
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.ItemDashboard:
                    Fragment fragment = new FragmentDashboard();
                    GetFragment(fragment);
                    return true;

                case R.id.ItemMeasures:
                    Fragment fragmentAddToDoList = new FragmentMeasures();
                    GetFragment(fragmentAddToDoList);
                    return true;

                case R.id.ItemNews:
                    Fragment fragmentProfile = new FragmentNews();
                    GetFragment(fragmentProfile);
                    return true;
            }
            return false;
        }
    };

    private void GetFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
