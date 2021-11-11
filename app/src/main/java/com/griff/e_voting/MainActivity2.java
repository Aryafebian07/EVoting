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
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.fragment.HomeFragment;
import com.griff.e_voting.fragment.KandidatFragment;
import com.griff.e_voting.fragment.PemiluFragment;
import com.griff.e_voting.fragment.ProfileFragment;
import com.griff.e_voting.fragment.StaticFragment;
import com.griff.e_voting.fragment.VoteFragment;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity {

    Fragment fragment;
    TextView toolbarTitle;
    FragmentManager fm = getSupportFragmentManager();
    private int pemilu_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Home");
        getSupportActionBar().setTitle(null);

        pemilu_id = getIntent().getIntExtra("pemilu_id",0);

        CircleImageView civ_profilrtop = findViewById(R.id.profile_imageTop);
        SharedPreferences sp = MainActivity2.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String path_image = sp.getString("path_image","");
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_user/"+path_image).into(civ_profilrtop);

        fm.beginTransaction().replace(R.id.container, new KandidatFragment()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.navigation_kandidat:
                        fragment = new KandidatFragment();
                        break;
                    case R.id.navigation_vote:
                        fragment = new VoteFragment();
                        break;
                    case R.id.navigation_statik:
                        fragment = new StaticFragment();
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

    public int getPemilu_id(){
        return  pemilu_id;
    }
}