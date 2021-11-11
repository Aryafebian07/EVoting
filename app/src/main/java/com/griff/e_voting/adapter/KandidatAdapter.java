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

        holder.tvk_nama.setText(semuaKandidatItem.getNama());
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_kandidat/"+semuaKandidatItem.getPath_image()).into(holder.civ_kandidat);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, KandidatActivity.class);
                i.putExtra("kandidat_id", semuaKandidatItem.getId());
                i.putExtra("kandidat_nama", semuaKandidatItem.getNama());
                i.putExtra("kandidat_visi", semuaKandidatItem.getVisi());
                i.putExtra("kandidat_misi", semuaKandidatItem.getMisi());
                i.putExtra("kandidat_image", semuaKandidatItem.getPath_image());
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

        public TextView tvk_nama;
        CircleImageView civ_kandidat;

        public KandidatHolder(@NonNull View itemView) {
            super(itemView);

            tvk_nama = itemView.findViewById(R.id.kandidat_nama);
            civ_kandidat = itemView.findViewById(R.id.kandidat_image);
        }
    }
}
