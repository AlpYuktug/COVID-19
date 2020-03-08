package com.alpyuktug.covid_19.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alpyuktug.covid_19.Fragments.FragmentDashboard;
import com.alpyuktug.covid_19.Fragments.FragmentDashboard;
import com.alpyuktug.covid_19.Fragments.FragmentDashboard;
import com.alpyuktug.covid_19.Fragments.FragmentMeasures;
import com.alpyuktug.covid_19.Fragments.FragmentNews;
import com.alpyuktug.covid_19.R;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);
        navigation.setSelectedItemId(R.id.ItemDashboard);


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
