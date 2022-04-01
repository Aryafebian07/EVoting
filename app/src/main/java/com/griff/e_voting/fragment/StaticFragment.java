package com.griff.e_voting.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.griff.e_voting.LoginActivity;
import com.griff.e_voting.MainActivity2;
import com.griff.e_voting.R;
import com.griff.e_voting.adapter.KandidatAdapter;
import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.model.ResponseKandidat;
import com.griff.e_voting.model.SemuaKandidat;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaticFragment extends Fragment {
    ProgressDialog loading;
    SwipeRefreshLayout swpKandidat;
    PieChart pieChart;

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
        ((MainActivity2) getActivity()).setActionBarTitle("Statistik");
        int pemilu_id = ((MainActivity2) getActivity()).getPemilu_id();

        View view = inflater.inflate(R.layout.fragment_static, container, false);
        pieChart = view.findViewById(R.id.statikbar_kandidat);
        swpKandidat = view.findViewById(R.id.skandidat_swp);

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

    private void showPieChart(){

    }

    private void initPieChart(){

    }

    private void getResultListKandidat(int pemilu_id) {
        loading = ProgressDialog.show(getActivity(),null,"Harap Tunggu..",true,false);
        Koneksi.getAPIService().semuakandidat(pemilu_id).enqueue(new Callback<ResponseKandidat>() {
            @Override
            public void onResponse(Call<ResponseKandidat> call, Response<ResponseKandidat> response) {
                if(response.body().getSuccess() >0){
                    loading.dismiss();
                    try {
                        pieChart.setDrawHoleEnabled(true);
                        pieChart.setUsePercentValues(true);
                        pieChart.setEntryLabelColor(Color.BLACK);
                        pieChart.setEntryLabelTextSize(12);
                        pieChart.setDrawEntryLabels(false);
                        pieChart.setCenterText("Persentasi Hasil Vote");
                        pieChart.setCenterTextSize(24);
                        pieChart.getDescription().setEnabled(false);

                        Legend l = pieChart.getLegend();
                        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                        l.setOrientation(Legend.LegendOrientation.VERTICAL);
                        l.setTextSize(12);
                        l.setDrawInside(false);
                        l.setEnabled(true);

                        List<PieEntry> entries = new ArrayList<>();
                        for(int i=0; i < response.body().getSemuaKandidat().size(); i++)
                        {
                            entries.add(new PieEntry(response.body().getSemuaKandidat().get(i).getJumlah_suara(),
                                    response.body().getSemuaKandidat().get(i).getNama_ketua()+" & "+response.body().getSemuaKandidat().get(i).getNama_wakil()));
                        }
                        ArrayList<Integer> colors = new ArrayList<>();
                        for (int color: ColorTemplate.MATERIAL_COLORS) {
                            colors.add(color);
                        }

                        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
                            colors.add(color);
                        }

                        PieDataSet dataSet = new PieDataSet(entries,"");

                        dataSet.setColors(colors);
                        PieData data = new PieData(dataSet);
                        data.setDrawValues(true);
                        data.setValueFormatter(new PercentFormatter(pieChart));
                        data.setValueTextSize(12f);
                        data.setValueTextColor(Color.BLACK);
                        pieChart.setData(data);
                        pieChart.invalidate();
                        pieChart.animateY(1400, Easing.EaseInOutQuad);

                        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                            @Override
                            public void onValueSelected(Entry e, Highlight h) {
                                AlertDialog.Builder alertResponse = new AlertDialog.Builder(getContext());
                                alertResponse.setMessage(String.valueOf((int)e.getY()));
                                alertResponse.setTitle("Jumlah Suara");
                                alertResponse.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertResponse.create().dismiss();
                                    }
                                });
                                alertResponse.create().show();
                            }

                            @Override
                            public void onNothingSelected() {

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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