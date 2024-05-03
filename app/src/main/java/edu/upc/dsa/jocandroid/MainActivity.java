package edu.upc.dsa.jocandroid;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;


import edu.upc.dsa.jocandroid.modelo.Usuario;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.ArrayList;
import androidx.annotation.RequiresApi;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    ApiInterface apiInterface;
    public static final String API_URL = "http://147.83.7.204:8080/dsaApp/";

    private EditText etemaillog;
    private Button btn_login;
    TextView pantalla_resgistrar;
    private ArrayList<Usuario> listaUsuarios;
    private RetroApiUsuer loginService;
    String Jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        SharedPreferences sharedPref = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        jugador = sharedPref.getString("usuario","Hola");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        sharedPref = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        userName = sharedPref.getString("usuario", null);

        if (userName == null) {
            finish();
        }


    }



}