package edu.upc.dsa.jocandroid;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.jocandroid.modelo.Usuario;

public class InicioActivity extends AppCompatActivity
{
    private String nombre;
    private TextView etnominiperfil, etuseriniperfil, etemailiniperfil;
    private Usuario objUsuario;
    ApiInterface apiInterface;
    public static final String API_URL = "http://10.0.0.2:8080/dsaApp/";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        inicio();
        cargarDatos();
    }

    public void inicio()
    {
        etnominiperfil= findViewById(R.id.nom_iniperfil_TextView);
        etuseriniperfil= findViewById(R.id.useriniperfil_TextView);
        etemailiniperfil= findViewById(R.id.email_iniperfil_TextView);

    }

    public void cargarDatos()
    {
        objUsuario = (Usuario) getIntent().getSerializableExtra("Usuario");
        etnominiperfil.setText(objUsuario.getNombre());
        etemailiniperfil.setText(objUsuario.getEmail());
    }
}
