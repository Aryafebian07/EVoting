package com.griff.e_voting.model;

import com.google.gson.annotations.SerializedName;

public class SemuaPemilu {
    @SerializedName("id")
    private int id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("periode")
    private String periode;
    @SerializedName("status")
    private String status;
    @SerializedName("jurusan_id")
    private int jurusan_id;
    @SerializedName("path_image")
    private String path_image;

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getNama(){return nama;}
    public void setNama(String nama){this.nama = nama;}

    public String getPeriode(){return periode;}
    public void setPeriode(String periode){this.periode = periode;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}

    public int getJurusan_id(){return jurusan_id;}
    public void setJurusan_id(int jurusan_id){this.jurusan_id = jurusan_id;}

    public String getPath_image(){return path_image;}
    public void setPath_image(String path_image){this.path_image = path_image;}
}
