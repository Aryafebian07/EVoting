package com.griff.e_voting.model;

import com.google.gson.annotations.SerializedName;

public class SemuaKandidat {
    @SerializedName("id")
    private int id;
    @SerializedName("nama_ketua")
    private String nama_ketua;
    @SerializedName("nama_wakil")
    private String nama_wakil;
    @SerializedName("nomor")
    private int nomor;
    @SerializedName("jumlah_suara")
    private int jumlah_suara;
    @SerializedName("pemilu_id")
    private int pemilu_id;
    @SerializedName("path_image_ketua")
    private String path_image_ketua;
    @SerializedName("path_image_wakil")
    private String path_image_wakil;

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getNama_ketua(){return nama_ketua;}
    public void setNama_ketua(String nama_ketua){this.nama_ketua = nama_ketua;}
    public String getNama_wakil(){return nama_wakil;}
    public void setNama_wakil(String nama_wakil){this.nama_wakil = nama_wakil;}


    public int getNomor(){return nomor;}
    public void setNomor(int nomor){this.nomor = nomor;}

    public int getJumlah_suara(){return jumlah_suara;}
    public void setJumlah_suara(int jumlah_suara){this.jumlah_suara = jumlah_suara;}

    public int getPemilu_id(){return pemilu_id;}
    public void setPemilu_id(int pemilu_id){this.pemilu_id = pemilu_id;}

    public String getPath_image_ketua(){return path_image_ketua;}
    public void setPath_image_ketua(String path_image_ketua){this.path_image_ketua = path_image_ketua;}
    public String getPath_image_wakil(){return path_image_wakil;}
    public void setPath_image_wakil(String path_image_wakil){this.path_image_wakil = path_image_wakil;}
}
