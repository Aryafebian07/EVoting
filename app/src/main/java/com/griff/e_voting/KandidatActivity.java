package com.griff.e_voting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.griff.e_voting.api.Koneksi;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class KandidatActivity extends AppCompatActivity {
    TextView tvpk_nama,tvpk_visi,tvpk_misi;
    CircleImageView circleImageView;
    TextView toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kandidat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle(null);

        CircleImageView civ_profilrtop = findViewById(R.id.profile_imageTop);
        SharedPreferences sp = KandidatActivity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String path_image2 = sp.getString("path_image","");
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_user/"+path_image2).into(civ_profilrtop);

        String nama = getIntent().getStringExtra("kandidat_nama");
        String visi = getIntent().getStringExtra("kandidat_visi");
        String misi = getIntent().getStringExtra("kandidat_misi");
        int nomor = getIntent().getIntExtra("kandidat_nomor",0);
        String path_image = getIntent().getStringExtra("kandidat_image");

        tvpk_nama = findViewById(R.id.pkandidat_name);
        tvpk_visi = findViewById(R.id.pkandidat_visi);
        tvpk_misi = findViewById(R.id.pkandidat_misi);
        circleImageView = findViewById(R.id.pkandidat_image);

        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_kandidat/"+path_image).into(circleImageView);
        tvpk_nama.setText(nama);
        tvpk_visi.setText(visi);
        tvpk_misi.setText(misi);
        toolbarTitle.setText("Kandidat "+nomor);

    }
}