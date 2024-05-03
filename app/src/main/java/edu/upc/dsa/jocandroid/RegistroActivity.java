package edu.upc.dsa.jocandroid;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONObject;
import java.util.regex.Pattern;
import edu.upc.dsa.jocandroid.databinding.ActivityRegistroBinding;
import edu.upc.dsa.jocandroid.modelo.RegistroReq;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegistroActivity extends AppCompatActivity
{
    public static final String BASE_URL = "http://147.83.7.204:8080/dsaApp/";
    EditText etnombre,etusuario, etpassword, etemail, etpassword2;
    Button btn_registrar;
    TextView usuario;
    TextView Name;
    TextView email;
    TextView password;
    TextView password2;

    ApiInterface apiInterface;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);

        //Asociar variables

        etnombre = findViewById(R.id.NomRegister_Text);
        etusuario = findViewById(R.id.UsuarioRegister_Text);
        etpassword = findViewById(R.id.PassRegister_Text);
        etpassword2 = findViewById(R.id.Pass2_Register_Text);
        etemail = findViewById(R.id.Emailregister_Text);
        btn_registrar = findViewById(R.id.GuardaRegist_bnt);

    }

    public void doneClick(View view) throws IOException
    {
        findViewById(R.id.textViewErrorPass).setVisibility(View.GONE);
        findViewById(R.id.textViewErrorCampos).setVisibility(View.GONE);
        findViewById(R.id.textViewErrorMail).setVisibility(View.GONE);

        String nombre = etnombre.getText().toString();
        String usuario = etusuario.getText().toString();
        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();
        String password2 = etpassword2.getText().toString();


        if (nombre.length()>0 && full.length()>0 && email.length()>0 && password.length()>0)

        {
            if (!validarEmail(email))
            {
                findViewById(R.id.textViewErrorMail).setVisibility(View.VISIBLE);
                return;
            }
            if (!password.equals(password2))
            {
                findViewById(R.id.Pass2_Register_Text).setVisibility(View.VISIBLE);
                password.setText("");
                password2.setText("");
                return;
            }
        }
        else
        {
            findViewById(R.id.textViewErrorCampos).setVisibility(View.VISIBLE);
            return;
        }

        RegisterUserTO usuarioRegister = new RegisterUserTO(usuario,password,nombre,email);
        Call<UserTO> call = apiInterface.addUser(usuarioRegister);
        call.enqueue(new Callback<UserTO>()
        {
            @Override
            public void onResponse(Call<UserTO> call, Response<UserTO> response)
            {
                if (!response.isSuccessful())
                {
                    Log.d("Agregar usuario", "Error agregar Usuario" + response.code());
                    Toast.makeText(RegistroActivity.this, "Usuario ya registrado", Toast.LENGTH_LONG).show();
                    return;
                }
                UserTO userTO = response.body();
                Toast.makeText(Register.this, "Bienvenido " + userTO.getUserName(), Toast.LENGTH_LONG).show();
                Log.d("Add Usuario", "Successful add Usuario " + userTO.getUserName());

                SharedPreferences sharedPref = getSharedPreferences("credencial", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("usuario", usuarioRegister.getUserName());
                editor.putString("password", usuarioRegister.getPassword());
                Log.d("Add Usuario", "Save Usuarios--> " + usuarioRegister.getUserName());
                Log.d("Add Usuario", "Save Password --> " + usuarioRegister.getPassword());
                editor.commit();

                Intent intentMainActivity = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }

            public void onFailure(Call<UserTO> call, Throwable t) {
                Toast.makeText(RegistroActivity.this, "Error  respuesta servidor", Toast.LENGTH_LONG).show();
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                Log.d("AddUser", "Error de retrofit: "+t.getMessage());
            }
        });
    }

    private boolean validarEmail(String email)

    {
        Pattern pattern = Pattern.EMAL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void onBackPressed()
    {
        Intent intentLoginActivity = new Intent(RegistroActivity.this, LoginActivity.class);
        startActivity(intentLoginActivity);
        finish();
    }


        //Eventos



//    public void onClick(View view)
//    {
//        if (view == btn_registrar)
//        {
//            final String nombre = etnombre.getText().toString();
//            final String usuario = etusuario.getText().toString();
//            final String password = etpassword.getText().toString();
//            final String email = etemail.getText().toString();
//
//        }
//    }

}