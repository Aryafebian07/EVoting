package com.griff.e_voting.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.griff.e_voting.KandidatActivity;
import com.griff.e_voting.LoginActivity;
import com.griff.e_voting.R;
import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.model.ResponseKandidat;
import com.griff.e_voting.model.SemuaKandidat;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.KandidatHolder>{
    List<SemuaKandidat> semuaKandidatLists;
    Context mContext;

    public VoteAdapter(Context context, List<SemuaKandidat> kandidatLists){
        this.mContext = context;
        semuaKandidatLists = kandidatLists;
    }

    @NonNull
    @Override
    public KandidatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_vote, parent,false);
        return new KandidatHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull KandidatHolder holder, int position) {
        final SemuaKandidat semuaKandidatItem = semuaKandidatLists.get(position);

        holder.tvk_nama_ketua.setText(semuaKandidatItem.getNama_ketua());
        holder.tvk_nama_wakil.setText(semuaKandidatItem.getNama_wakil());
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_kandidat/"+semuaKandidatItem.getPath_image_ketua()).into(holder.civ_kandidat_ketua);
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_kandidat/"+semuaKandidatItem.getPath_image_wakil()).into(holder.civ_kandidat_wakil);
        holder.btn_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                AlertDialog.Builder alertResponse = new AlertDialog.Builder(mContext);
                alertResponse.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertResponse.create().dismiss();
                    }
                });
                alert.setMessage("Anda ingin memilih kandidat ini?");
                alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = mContext.getSharedPreferences("login", Context.MODE_PRIVATE);

                        int user_id = sp.getInt("id",0);
                        int pemilu_id = semuaKandidatItem.getPemilu_id();
                        int kandidat_id = semuaKandidatItem.getId();
                        Koneksi.getAPIService().votekandidat(user_id,pemilu_id,kandidat_id).enqueue(new Callback<ResponseKandidat>() {
                            @Override
                            public void onResponse(Call<ResponseKandidat> call, Response<ResponseKandidat> response) {
                                if(response.body().getSuccess() >0){
                                    alertResponse.setMessage(response.body().getMessage());
                                    alertResponse.create().show();
                                }else {
                                    alertResponse.setMessage(response.body().getMessage());
                                    alertResponse.create().show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseKandidat> call, Throwable t) {
                                Toast.makeText(mContext,"ErrorFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

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
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mContext, KandidatActivity.class);
//                i.putExtra("kandidat_id", semuaKandidatItem.getId());
//                i.putExtra("kandidat_nama", semuaKandidatItem.getNama());
//                i.putExtra("kandidat_visi", semuaKandidatItem.getVisi());
//                i.putExtra("kandidat_misi", semuaKandidatItem.getMisi());
//                i.putExtra("kandidat_image", semuaKandidatItem.getPath_image());
//                i.putExtra("kandidat_nomor", semuaKandidatItem.getNomor());
//                mContext.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return semuaKandidatLists.size();
    }

    public class KandidatHolder extends RecyclerView.ViewHolder {

        public TextView tvk_nama_ketua,tvk_nama_wakil;
        public Button btn_vote;
        CircleImageView civ_kandidat_ketua,civ_kandidat_wakil;

        public KandidatHolder(@NonNull View itemView) {
            super(itemView);

            tvk_nama_ketua = itemView.findViewById(R.id.vkandidat_ketua_nama);
            tvk_nama_wakil = itemView.findViewById(R.id.vkandidat_wakil_nama);
            civ_kandidat_ketua = itemView.findViewById(R.id.vkandidat_ketua_image);
            civ_kandidat_wakil = itemView.findViewById(R.id.vkandidat_wakil_image);
            btn_vote = itemView.findViewById(R.id.btn_vote);
        }
    }
}
