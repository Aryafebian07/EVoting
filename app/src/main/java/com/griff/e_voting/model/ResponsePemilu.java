package com.griff.e_voting.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePemilu {

    @SerializedName("semuapemilu")
    private List<SemuaPemilu> semuaPemilu;

    @SerializedName("success")
    private int success;

    @SerializedName("massage")
    private  String massage;

    public  List<SemuaPemilu> getSemuaPemilu(){
        return  semuaPemilu;
    }

    public void setSemuaPemilu(List<SemuaPemilu> semuaPemilu)
    {
        this.semuaPemilu = semuaPemilu;
    }

    public int getSuccess() {
        return success;
    }

    protected void setSuccess(int success){
        this.success = success;
    }

    public String getMessage() {
        return massage;
    }

    public void setMessage(String message){
        this.massage = message;
    }

}
