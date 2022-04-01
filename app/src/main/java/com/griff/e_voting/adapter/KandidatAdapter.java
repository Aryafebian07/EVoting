package com.griff.e_voting.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.griff.e_voting.KandidatActivity;
import com.griff.e_voting.MainActivity2;
import com.griff.e_voting.R;
import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.model.SemuaKandidat;
import com.griff.e_voting.model.SemuaPemilu;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class KandidatAdapter extends RecyclerView.Adapter<KandidatAdapter.KandidatHolder>{
    List<SemuaKandidat> semuaKandidatLists;
    Context mContext;

    public KandidatAdapter(Context context,List<SemuaKandidat> kandidatLists){
        this.mContext = context;
        semuaKandidatLists = kandidatLists;
    }

    @NonNull
    @Override
    public KandidatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_kandidat, parent,false);
        return new KandidatHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull KandidatHolder holder, int position) {
        final SemuaKandidat semuaKandidatItem = semuaKandidatLists.get(position);

        holder.tvk_nama_ketua.setText(semuaKandidatItem.getNama_ketua());
        holder.tvk_nama_wakil.setText(semuaKandidatItem.getNama_wakil());
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_kandidat/"+semuaKandidatItem.getPath_image_ketua()).into(holder.civ_kandidat_ketua);
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_kandidat/"+semuaKandidatItem.getPath_image_wakil()).into(holder.civ_kandidat_wakil);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, KandidatActivity.class);
                i.putExtra("kandidat_id", semuaKandidatItem.getId());
                i.putExtra("kandidat_nama_ketua", semuaKandidatItem.getNama_ketua());
                i.putExtra("kandidat_nama_wakil", semuaKandidatItem.getNama_wakil());
                i.putExtra("kandidat_image_ketua", semuaKandidatItem.getPath_image_ketua());
                i.putExtra("kandidat_image_wakil", semuaKandidatItem.getPath_image_wakil());
                i.putExtra("kandidat_nomor", semuaKandidatItem.getNomor());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return semuaKandidatLists.size();
    }

    public class KandidatHolder extends RecyclerView.ViewHolder {

        public TextView tvk_nama_ketua,tvk_nama_wakil;
        CircleImageView civ_kandidat_ketua,civ_kandidat_wakil;;

        public KandidatHolder(@NonNull View itemView) {
            super(itemView);

            tvk_nama_ketua = itemView.findViewById(R.id.kandidat_ketua_nama);
            tvk_nama_wakil = itemView.findViewById(R.id.kandidat_wakil_nama);
            civ_kandidat_ketua = itemView.findViewById(R.id.kandidat_ketua_image);
            civ_kandidat_wakil = itemView.findViewById(R.id.kandidat_wakil_image);

        }
    }
}
