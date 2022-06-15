package com.example.orderfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.orderfood.Fragments.AccountFragment;
import com.example.orderfood.Fragments.HomeFragment;
import com.example.orderfood.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameHomeContainer,new HomeFragment(),HomeFragment.class.getSimpleName()).commit();
        init();
    }
    private void init(){
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_home: {
                        Fragment account = fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName());
                        Fragment search = fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName());
                        if(account!=null){
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName())).commit();
                        }
                        if (search!=null){
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName())).commit();
                        }
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                        break;
                    }

                    case R.id.item_account:{
                        Fragment account = fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName());
                        Fragment search = fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName());
                        if(account!=null){
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                            if (search!=null){
                                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName())).commit();
                            }
                        }
                        else {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().add(R.id.frameHomeContainer,new AccountFragment(),AccountFragment.class.getSimpleName()).commit();
                            if (search!=null){
                                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName())).commit();
                            }
                        }

                        break;
                    }
                    case R.id.item_store: {
                        startActivity(new Intent(HomeActivity.this, CartActivity.class));
                        break;
                        
                    }
                    case R.id.item_search: {
                        Fragment account = fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName());
                        Fragment search = fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName());
                        if(search!=null){
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                            if (account!=null){
                                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName())).commit();
                            }
                        }
                        else {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().add(R.id.frameHomeContainer,new SearchFragment(),SearchFragment.class.getSimpleName()).commit();
                            if (account!=null){
                                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName())).commit();
                            }
                        }

                        break;

                    }

                }
                return true;
            }
        });
    }

}