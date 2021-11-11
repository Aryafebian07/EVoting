package com.griff.e_voting.model;

import com.google.gson.annotations.SerializedName;

public class SemuaKandidat {
    @SerializedName("id")
    private int id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("visi")
    private String visi;
    @SerializedName("misi")
    private String misi;
    @SerializedName("nomor")
    private int nomor;
    @SerializedName("jumlah_suara")
    private int jumlah_suara;
    @SerializedName("pemilu_id")
    private int pemilu_id;
    @SerializedName("path_image")
    private String path_image;

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getNama(){return nama;}
    public void setNama(String nama){this.nama = nama;}

    public String getVisi(){return visi;}
    public void setVisi(String visi){this.visi = visi;}

    public String getMisi(){return misi;}
    public void setMisi(String misi){this.misi = misi;}

    public int getNomor(){return nomor;}
    public void setNomor(int nomor){this.nomor = nomor;}

    public int getJumlah_suara(){return jumlah_suara;}
    public void setJumlah_suara(int jumlah_suara){this.jumlah_suara = jumlah_suara;}

    public int getPemilu_id(){return pemilu_id;}
    public void setPemilu_id(int pemilu_id){this.pemilu_id = pemilu_id;}

    public String getPath_image(){return path_image;}
    public void setPath_image(String path_image){this.path_image = path_image;}
}
