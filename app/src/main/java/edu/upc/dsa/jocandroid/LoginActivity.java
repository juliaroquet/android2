package edu.upc.dsa.jocandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import edu.upc.dsa.jocandroid.modelo.LoginUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;


public class LoginActivity extends AppCompatActivity {
    TextView loginName;
    TextView PassLog_Text;
    ApiInterface apiInterface;


    public static final String API_URL = "http://147.83.7.204:8080/dsaApp/";
    //public static final String API_URL = "http://10.0.2.2:8080/dsaApp/";

    protected void onCreate(Bundle savedInstanceState)
    {

        getSupportActionBar().hide();

        Retrofit retrofit =new  Retrofit.Builder()

                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface =retrofit.create(ApiInterface.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void Login_BtnonClick (View view) {
        loginName = (TextView) findViewById(R.id.EmailLog_Text);
        PassLog_Text = (TextView) findViewById(R.id.PassLog_Text);
        //Inicio Sesión Usuario

        LoginUsuario usuario = new LoginUsuario(loginName.getText().toString(), PassLog_Text.getText().toString());

        Log.d("LoginUsuario", "Login user --> " + usuario.getUsuario());
        Call<LoginUsuario> call = apiInterface.loginUser(usuario);
        call.enqueue(new Callback<LoginUsuario>() {
            public void onResponse(Call<LoginUsuario> call, Response<LoginUsuario> response) {

                if (!response.isSuccessful()) {
                    Log.d("LoginUser", "Error loginUser" + response.code());
                    Toast.makeText(LoginActivity.this, "Usuario no Registrado", Toast.LENGTH_LONG).show();
                    return;
                }

                LoginUsuario LoginUsuario = response.body();
                Toast.makeText(LoginUsuario.this, "Bienvenido " + LoginUsuario.getUsuario(), Toast.LENGTH_LONG).show();
                Log.d("LoginUser", "Successful loginUser " + LoginUsuario.getUsuario());
                saveSharedPreferences(LoginUsuario);
                Intent intent = new Intent(LoginActivity.this, MainActivity.this);
                startActivity(intent);
                finish();
            }

            public void onFailure(Call<LoginUsuario> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Error del servicio service", Toast.LENGTH_LONG).show();
                Log.d("LoginUser", "Error de inicio de sesión no hay usuario using retrofit: " + t.getMessage());
            }
        });
    }


    public void loginToRegister(View view) {
        Intent intent = new Intent(this, RegistroActivity.this);
        startActivity(intent);
        finish();
    }

    public void saveSharedPreferences(LoginUsuario loginUsuario) {
        SharedPreferences sharedPref = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("usuario", loginUserTO.getUserName());
        editor.putString("password", loginUserTO.getPassword());
        Log.d("LoginUser", "Save usuario--> " + loginUsuario.getUsuario());
        Log.d("LoginUser", "Save password --> " + loginUsuario.getPassword());
        editor.commit();
    }
}

