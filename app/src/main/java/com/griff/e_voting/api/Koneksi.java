package com.griff.e_voting.api;

public class Koneksi {
    public static final String BASE_URL_API = "http://192.168.137.1/e-voting/public/";

    //mobile_example/public/api
    public static BaseApiServices getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).
                create(BaseApiServices.class);
    }
}
