package com.griff.e_voting.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLogin {
    @SerializedName("user")
    private List<User> user;

    @SerializedName("success")
    private int success;

    @SerializedName("massage")
    private  String massage;

    public  List<User> getUser(){
        return  user;
    }

    public void setUser(List<User> user)
    {
        this.user = user;
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

//    @NonNull
//    @Override
//    public String toString(){
//        return
//                "ResponseLogin{"+
//                        "user ='"+user+'\''+
//                        "success ='"+success+'\''+
//                        "message ='"+message+'\''+
//                        "}";
//    }
}
