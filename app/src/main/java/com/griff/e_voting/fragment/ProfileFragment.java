package com.griff.e_voting.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.griff.e_voting.LoginActivity;
import com.griff.e_voting.MainActivity;
import com.griff.e_voting.R;
import com.griff.e_voting.api.Koneksi;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
        String jurusan;
        TextView tp_name,tp_nim,tp_alamat,tp_jenis_kelamin,tp_jurusan;
        CircleImageView circleImageView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Profile");
        // Inflate the layout for this fragment
        SharedPreferences sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        Boolean isloged = sp.getBoolean("isLoged",false);
        String name = sp.getString("name","");
        String nim = sp.getString("nim","");
        String alamat = sp.getString("alamat","");
        String jenis_kelamin = sp.getString("jenis_kelamin","");
        String path_image = sp.getString("path_image","");
        int jurusan_id = sp.getInt("jurusan_id",0);

        if(jurusan_id == 1){
            jurusan = "Teknik Komputer";
        }else if(jurusan_id == 2){
            jurusan = "Teknik Mesin";
        }else if(jurusan_id == 3){
            jurusan = "Teknik Sipil";
        }else if(jurusan_id == 4){
            jurusan = "Administrasi Bisnis";
        }

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tp_name = view.findViewById(R.id.profile_name);
        tp_nim = view.findViewById(R.id.profile_nim);
        tp_alamat = view.findViewById(R.id.profile_alamat);
        tp_jenis_kelamin = view.findViewById(R.id.profile_jenis_kelamin);
        tp_jurusan = view.findViewById(R.id.profile_jurusan);
        circleImageView = view.findViewById(R.id.profile_image);

        tp_name.setText(name);
        tp_nim.setText(nim);
        tp_alamat.setText(alamat);
        tp_jenis_kelamin.setText(jenis_kelamin);
        tp_jurusan.setText(jurusan);
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_user/"+path_image).into(circleImageView);

        Button btnLogout = view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("Anda yakin untuk logout?");
                alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor sped = sp.edit();
                        sped.clear();
                        sped.apply();
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }
                }).setNeutralButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.create().dismiss();
                    }
                });
                alert.create().show();
            }
        });

        return view;
    }
}