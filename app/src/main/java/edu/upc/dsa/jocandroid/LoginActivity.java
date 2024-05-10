package edu.upc.dsa.jocandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.jocandroid.modelo.LoginUsuario;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity
{
    TextView usernamelog;
    TextView passwordlog;

    edu.upc.dsa.jocandroid.ApiInterface apiInterface;

    public static final String API_URL = "http://147.83.7.204:8080/dsaApp/";


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(edu.upc.dsa.jocandroid.ApiInterface.class);

    }

    public void Login_BtnonClick(View view)
    {
        usernamelog =(TextView) findViewById(R.id.EmailLog_Text);
        passwordlog =(TextView) findViewById(R.id.PassLog_Text);

        LoginUsuario usuario = new LoginUsuario(usernamelog.getText().toString(), passwordlog.getText().toString());
        Log.d("Usernamelog","Login user ---> "+ usuario.getUsuario());
        Call<LoginUsuario> call =apiInterface.loginUser(usuario);
        call.enqueue(new Callback<LoginUsuario>()
        {
            @Override
            public void onResponse(Call<LoginUsuario> call, Response<LoginUsuario> response)
            {
                if (!response.isSuccessful())
                {
                    Log.d("Usernamelog", "Error login usuario"+ response.code());
                    Toast.makeText(LoginActivity.this, "Usuario no registrado", Toast.LENGTH_LONG).show();
                    return;
                }
                //llamada hhtp correctadevuelve el mensaje del del usuario
                LoginUsuario loginUsuario =response.body();
                Toast.makeText(LoginActivity.this, " Welcome"+ loginUsuario.getUsuario(), Toast.LENGTH_LONG).show();
                Log.d("LoguinUser","Successful loginUsuario"+ loginUsuario.getUsuario());
                saveSharedPreferences(loginUsuario);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();//final del log con el btn detrás
            }

            @Override
            public void onFailure(Call<LoginUsuario> call, Throwable t)
            {
                Toast.makeText(LoginActivity.this, "Error en la respuesta servidor", Toast.LENGTH_LONG).show();
                Log.d("Login Usuario", "Error username no funciona retrofit" + t.getMessage());

            }
        });


    }
    //navegar entre pantallas para registro
    public void loginRegister(View view)
    {
        Intent intent = new Intent(this, edu.upc.dsa.jocandroid.RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void saveSharedPreferences(LoginUsuario loginUsuario)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("usuario",loginUsuario.getUsuario());
        editor.putString("password", loginUsuario.getPassword());
        Log.d("Login Usuario", "Save usuario"+loginUsuario.getUsuario());
        Log.d("Login usuario", "Save password" + loginUsuario.getPassword());
        editor.commit();
    }





}
