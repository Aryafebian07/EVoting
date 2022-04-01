package com.griff.e_voting.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.griff.e_voting.MainActivity2;
import com.griff.e_voting.R;
import com.griff.e_voting.adapter.KandidatAdapter;
import com.griff.e_voting.adapter.PemiluAdapter;
import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.model.ResponseKandidat;
import com.griff.e_voting.model.SemuaKandidat;
import com.griff.e_voting.model.SemuaPemilu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KandidatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KandidatFragment extends Fragment {

    RecyclerView rvKandidat;
    List<SemuaKandidat> semuaKandidatLists = new ArrayList<>();
    ProgressDialog loading;
    KandidatAdapter kandidatAdapter;
    SwipeRefreshLayout swpKandidat;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KandidatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KandidatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KandidatFragment newInstance(String param1, String param2) {
        KandidatFragment fragment = new KandidatFragment();
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
        ((MainActivity2) getActivity()).setActionBarTitle("Kandidat");

        int pemilu_id = ((MainActivity2) getActivity()).getPemilu_id();;

        View view = inflater.inflate(R.layout.fragment_kandidat, container, false);
        rvKandidat = view.findViewById(R.id.kandidat_rv);
        swpKandidat = view.findViewById(R.id.kandidat_swp);

        kandidatAdapter = new KandidatAdapter(getContext(),semuaKandidatLists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvKandidat.setLayoutManager(mLayoutManager);
        getResultListKandidat(pemilu_id);
        swpKandidat.setColorSchemeResources(R.color.fourth,R.color.primary);
        swpKandidat.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swpKandidat.setRefreshing(false);
                        getResultListKandidat(pemilu_id);
                    }
                },2000);
            }
        });
        return view;
    }

    private void getResultListKandidat(int pemilu_id) {
        loading = ProgressDialog.show(getActivity(),null,"Harap Tunggu..",true,false);
        Koneksi.getAPIService().semuakandidat(pemilu_id).enqueue(new Callback<ResponseKandidat>() {
            @Override
            public void onResponse(Call<ResponseKandidat> call, Response<ResponseKandidat> response) {
                if(response.body().getSuccess() >0){
                    loading.dismiss();
                    final List<SemuaKandidat> semuaKandidatLists = response.body().getSemuaKandidat();
                    rvKandidat.setAdapter(new KandidatAdapter(getContext(), semuaKandidatLists));
                    kandidatAdapter.notifyDataSetChanged();
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