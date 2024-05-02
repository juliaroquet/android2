package edu.upc.dsa.jocandroid;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONObject;

import edu.upc.dsa.jocandroid.databinding.ActivityRegistroBinding;
import edu.upc.dsa.jocandroid.modelo.RegistroReq;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener
{

    EditText etnombre,etusuario, etpassword, etemail;
    Button btn_registrar;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Asociar variables

        etnombre = findViewById(R.id.NomRegister_Text);
        etusuario = findViewById(R.id.UsuarioRegister_Text);
        etpassword = findViewById(R.id.PassRegister_Text);
        etemail = findViewById(R.id.Emailregister_Text);
        btn_registrar = findViewById(R.id.GuardaRegist_bnt);

        //Acciones

        btn_registrar.setOnClickListener(this);
    }

        //Eventos



    public void onClick(View view) {
        if (view == btn_registrar) {
            final String nombre = etnombre.getText().toString();
            final String usuario = etusuario.getText().toString();
            final String password = etpassword.getText().toString();
            final String email = etemail.getText().toString();

        }
    }

}