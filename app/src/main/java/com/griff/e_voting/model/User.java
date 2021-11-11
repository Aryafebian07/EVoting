package com.griff.e_voting.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;
    @SerializedName("nim")
    private String nim;
    @SerializedName("name")
    private String name;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("jurusan_id")
    private int jurusan_id;
    @SerializedName("path_image")
    private String path_image;

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getNim(){return nim;}
    public void setNim(String nim){this.nim = nim;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getAlamat(){return alamat;}
    public void setAlamat(String alamat){this.alamat = alamat;}

    public String getJenis_kelamin(){return jenis_kelamin;}
    public void setJenis_kelamin(String Jenis_kelamin){this.jenis_kelamin = jenis_kelamin;}

    public int getJurusan_id(){return jurusan_id;}
    public void setJurusan_id(int jurusan_id){this.jurusan_id = jurusan_id;}

    public String getPath_image(){return path_image;}
    public void setPath_image(String path_image){this.path_image = path_image;}

//    @NonNull
//    @Override
//    public String toString(){
//        return
//                "ResponseLogin{"+
//                        "id ='"+id+'\''+
//                        "nim ='"+nim+'\''+
//                        "name ='"+name+'\''+
//                        "alamat ='"+alamat+'\''+
//                        "jenis_kelamin ='"+jenis_kelamin+'\''+
//                        "jurusan_id ='"+jurusan_id+'\''+
//                        "}";
//    }
}
