package com.griff.e_voting.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.griff.e_voting.MainActivity2;
import com.griff.e_voting.R;
import com.griff.e_voting.adapter.KandidatAdapter;
import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.model.ResponseKandidat;
import com.griff.e_voting.model.SemuaKandidat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaticFragment extends Fragment {
    List<SemuaKandidat> semuaKandidatLists = new ArrayList<>();
    ProgressDialog loading;
    BarChart barChart;
    SwipeRefreshLayout swpKandidat;
    List<String> namakandidat = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StaticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StaticFragment newInstance(String param1, String param2) {
        StaticFragment fragment = new StaticFragment();
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
        ((MainActivity2) getActivity()).setActionBarTitle("Statistika");
        View view = inflater.inflate(R.layout.fragment_static, container, false);

        barChart = view.findViewById(R.id.statikbar_kandidat);

        // Inflate the layout for this fragment
        return view;
    }

    private void getResultListPemilu(int pemilu_id) {
        loading = ProgressDialog.show(getActivity(),null,"Harap Tunggu..",true,false);
        Koneksi.getAPIService().semuakandidat(pemilu_id).enqueue(new Callback<ResponseKandidat>() {
            @Override
            public void onResponse(Call<ResponseKandidat> call, Response<ResponseKandidat> response) {
                if(response.body().getSuccess() >0){
                    loading.dismiss();

                }else {
                    loading.dismiss();
                    Toast.makeText(getActivity(),"Gagal Mengambil data Kandidat : "+response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKandidat> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(),"ErrorFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}