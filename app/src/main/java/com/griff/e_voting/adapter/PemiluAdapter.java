package com.griff.e_voting.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.griff.e_voting.MainActivity2;
import com.griff.e_voting.R;
import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.model.SemuaPemilu;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PemiluAdapter extends RecyclerView.Adapter<PemiluAdapter.PemiluHolder> {

    List<SemuaPemilu> semuaPemiluLists;
    Context mContext;

    public PemiluAdapter(Context context,List<SemuaPemilu> pemiluLists){
        this.mContext = context;
        semuaPemiluLists = pemiluLists;
    }

    @NonNull
    @Override
    public PemiluAdapter.PemiluHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pemilu, parent,false);
        return new PemiluHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull PemiluAdapter.PemiluHolder holder, int position) {
        final SemuaPemilu semuaPemiluItem = semuaPemiluLists.get(position);

        holder.tvp_nama.setText(semuaPemiluItem.getNama());
        holder.tvp_periode.setText(semuaPemiluItem.getPeriode());
        holder.tvp_status.setText(semuaPemiluItem.getStatus());
        Picasso.get().load(Koneksi.BASE_URL_API+"storage/images/profile_pemilu/"+semuaPemiluItem.getPath_image()).into(holder.civ_pemilu);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MainActivity2.class);
                i.putExtra("pemilu_id", semuaPemiluItem.getId());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return semuaPemiluLists.size();
    }

    public class PemiluHolder extends RecyclerView.ViewHolder {

        public TextView tvp_nama, tvp_periode, tvp_status;
        CircleImageView civ_pemilu;

        public PemiluHolder(@NonNull  View itemView) {
            super(itemView);

            tvp_nama = itemView.findViewById(R.id.pemilu_nama);
            tvp_periode = itemView.findViewById(R.id.pemilu_periode);
            tvp_status = itemView.findViewById(R.id.pemilu_status);
            civ_pemilu = itemView.findViewById(R.id.pemilu_image);

        }
    }

}
