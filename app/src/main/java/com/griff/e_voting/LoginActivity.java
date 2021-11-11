package com.griff.e_voting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.griff.e_voting.api.Koneksi;
import com.griff.e_voting.model.ResponseLogin;
import com.griff.e_voting.model.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    ProgressDialog loading;
    EditText textEmail,textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmail = findViewById(R.id.et_login_email);
        textPassword = findViewById(R.id.et_login_password);

        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loading = ProgressDialog.show(LoginActivity.this, null, "Harap Tunggu ...",true,false);
                String Email = textEmail.getText().toString();
                String Password = textPassword.getText().toString();
                
                if(TextUtils.isEmpty(Email)){
                    textEmail.setError("Kolom Email Tidak Boleh Kosong");
                    textEmail.requestFocus();
                    loading.dismiss();
                    return;
                }else if(TextUtils.isEmpty(Password)){
                    textPassword.setError("Kolom Password Tidak Boleh Kosong");
                    textPassword.requestFocus();
                    loading.dismiss();
                    return;
                }else{
                    Koneksi.getAPIService().login(Email,Password).enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if(response.body().getSuccess() >0){
                                List<User> dataUsers = response.body().getUser();
                                User datauser = dataUsers.get(0);

                                SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                                SharedPreferences.Editor sped = sp.edit();
                                sped.putInt("id", datauser.getId());
                                sped.putString("nim",datauser.getNim());
                                sped.putString("name",datauser.getName());
                                sped.putString("alamat",datauser.getAlamat());
                                sped.putString("jenis_kelamin",datauser.getJenis_kelamin());
                                sped.putInt("jurusan_id",datauser.getJurusan_id());
                                sped.putString("path_image",datauser.getPath_image());
                                sped.putBoolean("isLoged",true);
                                sped.apply();
                                loading.dismiss();
                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }else {
                                loading.dismiss();
                                Toast.makeText(LoginActivity.this,"Gagal Login : "+response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            Toast.makeText(LoginActivity.this,"ErrorFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                            System.out.println("onFailure");
                            System.out.println(t.fillInStackTrace());
                        }
                    });
                }


            }
        });
    }

}