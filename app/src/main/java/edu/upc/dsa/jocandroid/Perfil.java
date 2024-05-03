package edu.upc.dsa.jocandroid;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.jocandroid.modelo.Usuario;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.os.Bundle;

public class Perfil extends AppCompatActivity
{
    TextView etusuario, etname, etemail;
    ApiInterface apiInterface;

    public static final String API_URL = "http://147.83.7.204:8080/dsaApp/";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        etusuario = findViewById(R.id.useriniperfil_TextView);
        etname = findViewById(R.id.nom_iniperfil_TextView);
        etemail = findViewById(R.id.email_iniperfil_TextView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);

        SharedPreferences sharedPref = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String userName = sharedPref.getString("Usuario", null).toString();

        Call<UserTO> call = apiInterface.getUser(userName); //no se solucionarlo UserTO
        call.enqueue(new Callback<UserTO>() {
            
            public void onResponse(Call<UserTO> call, Response<UserTO> response) {
                if (!response.isSuccessful()) {
                    Log.d("Profile", "Error usuario  no existe");
                    return;
                }
                Log.d("Perfil", "Successful  " + userName);
                UserTO data = response.body();
                etusuario.setText(data.getUsuario());
                etname.setText("Nombre" + data.getNombre);
                etemail.setText("Email     " + data.getEmail());
            }


            public void onFailure(Call call, Throwable t) {
                Toast.makeText(Perfil.this, "Error response from service", Toast.LENGTH_LONG).show();
                Log.d("Profile", "Error in getting response from service: " + t.getMessage());
            }
        });
    }

}
