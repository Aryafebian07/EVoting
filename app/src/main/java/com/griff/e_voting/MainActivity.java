package com.griff.e_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.fragment.HomeFragment;
import com.griff.e_voting.fragment.PemiluFragment;
import com.griff.e_voting.fragment.ProfileFragment;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    Fragment fragmentHome = new HomeFragment();
    Fragment fragmentProfile = new ProfileFragment();
    Fragment fragmentPemilu = new PemiluFragment();

    Fragment fragment;
    TextView toolbarTitle;
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Home");
        getSupportActionBar().setTitle(null);

        CircleImageView civ_profilrtop = findViewById(R.id.profile_imageTop);
        SharedPreferences sp = MainActivity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String path_image = sp.getString("path_image","");
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_user/"+path_image).into(civ_profilrtop);


        fm.beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.navigation_pemilu:
                        fragment = new PemiluFragment();
                        break;
                    case R.id.navigation_profile:
                        fragment = new ProfileFragment();
                        break;
                }
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment).commit();
                return true;
            }
        });

    }

    public void setActionBarTitle(String title){
        toolbarTitle.setText(title);
    }
}