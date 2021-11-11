package com.griff.e_voting.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.griff.e_voting.LoginActivity;
import com.griff.e_voting.MainActivity;
import com.griff.e_voting.R;
import com.griff.e_voting.adapter.PemiluAdapter;
import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.model.ResponsePemilu;
import com.griff.e_voting.model.SemuaPemilu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PemiluFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PemiluFragment extends Fragment {

    RecyclerView rvPemilu;
    List<SemuaPemilu> semuaPemiluLists = new ArrayList<>();
    ProgressDialog loading;
    PemiluAdapter pemiluAdapter;
    SwipeRefreshLayout swpPemilu;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PemiluFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PemiluFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PemiluFragment newInstance(String param1, String param2) {
        PemiluFragment fragment = new PemiluFragment();
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
        ((MainActivity) getActivity()).setActionBarTitle("list Pemilu");
        SharedPreferences sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int jurusan_id = sp.getInt("jurusan_id",0);
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pemilu, container, false);
        rvPemilu = view.findViewById(R.id.pemilu_rv);
        swpPemilu = view.findViewById(R.id.pemilu_swp);

        pemiluAdapter = new PemiluAdapter(getContext(),semuaPemiluLists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvPemilu.setLayoutManager(mLayoutManager);
        getResultListPemilu(jurusan_id);
        swpPemilu.setColorSchemeResources(R.color.fourth,R.color.primary);
        swpPemilu.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swpPemilu.setRefreshing(false);
                        getResultListPemilu(jurusan_id);
                    }
                },2000);
            }
        });
        return view;
    }

    private void getResultListPemilu(int jurusan_id){
        loading = ProgressDialog.show(getActivity(),null,"Harap Tunggu..",true,false);
        Koneksi.getAPIService().semuapemilu(jurusan_id).enqueue(new Callback<ResponsePemilu>() {
            @Override
            public void onResponse(Call<ResponsePemilu> call, Response<ResponsePemilu> response) {
                if(response.body().getSuccess() >0){
                    loading.dismiss();
                    final List<SemuaPemilu> semuaPemiluLists = response.body().getSemuaPemilu();
                    rvPemilu.setAdapter(new PemiluAdapter(getContext(), semuaPemiluLists));
                    pemiluAdapter.notifyDataSetChanged();
                }else {
                    loading.dismiss();
                    Toast.makeText(getActivity(),"Gagal Mengambil data pemilu : "+response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePemilu> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(),"ErrorFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}