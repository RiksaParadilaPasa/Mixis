package com.example.mixis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mixis.api.BaseApiService;
import com.example.mixis.api.Token;
import com.example.mixis.api.UtilsApi;
import com.xwray.passwordview.PasswordView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextView txtSignup;
    EditText inputusername;
    PasswordView inputpassword;
    Button btnlogins;

    CardView pb;


    Context mContext;
    BaseApiService mApiService;

    public static String token = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        txtSignup = (TextView) findViewById(R.id.txtSignup);
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });

        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper

        pb = (CardView) findViewById(R.id.cvtunggulogin);

        inputusername = (EditText) findViewById(R.id.inputteksusername);
        inputpassword = (PasswordView) findViewById(R.id.inputtekspassword);
        btnlogins = (Button) findViewById(R.id.btnlogin);

        btnlogins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String users = inputusername.getText().toString();
                final String pass = inputpassword.getText().toString();

                if (TextUtils.isEmpty(users)) {
                    Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestLogin();
                pb.setVisibility(view.VISIBLE);

            }
        });


    }


    public void requestLogin(){
        String username = inputusername.getText().toString();
        String password = inputpassword.getText().toString();
        mApiService.loginRequest(username, password)
                .enqueue(new Callback<ResponseBody>() {
                    private String Success;
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                Success = jsonRESULTS.getJSONObject("success").getString("token");
                                Token token = new Token();
                                token.setToken(Success);
                                Log.d("Session ", ""+Success);
                                Toast.makeText(mContext, "berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, MainActivity.class);
                                Login.token = "Bearer " + this.Success;
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "Email Or Password doesn't exist", Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                });
    }


}
