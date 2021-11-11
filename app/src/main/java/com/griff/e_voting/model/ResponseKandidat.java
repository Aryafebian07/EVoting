package com.griff.e_voting.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKandidat {
    @SerializedName("semuakandidat")
    private List<SemuaKandidat> semuaKandidat;

    @SerializedName("success")
    private int success;

    @SerializedName("massage")
    private  String massage;

    public  List<SemuaKandidat> getSemuaKandidat(){
        return  semuaKandidat;
    }

    public void setSemuaKandidat(List<SemuaKandidat> semuaKandidat)
    {
        this.semuaKandidat = semuaKandidat;
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
