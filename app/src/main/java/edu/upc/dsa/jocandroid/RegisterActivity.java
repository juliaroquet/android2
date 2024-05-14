package edu.upc.dsa.jocandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.regex.Pattern;

import edu.upc.dsa.jocandroid.ApiInterface;
import edu.upc.dsa.jocandroid.MainActivity;
import edu.upc.dsa.jocandroid.R;
import edu.upc.dsa.jocandroid.modelo.RegisterUsuario;
import edu.upc.dsa.jocandroid.modelo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity
{
    TextView etusername;
    TextView etfullname;
    TextView etemail;
    TextView etpassword;
    TextView etpassword2;

    ApiInterface apiInterface;

    ProgressBar spinner;

    public static final String BASE_URL = "http://147.83.7.204:8080/dsaApp/";


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        etusername = findViewById(R.id.username_regis_editTextText);
        etfullname = findViewById(R.id.fullname_regis_editText);
        etemail = findViewById(R.id.email_registeditTextText3);
        etpassword = findViewById(R.id.passw_regist_editTextText4);
        etpassword2 = findViewById(R.id.passw2_register_editTextText5);
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

    }
    public void doneClick(View view) throws IOException
    {
        String usuario =etusername.getText().toString();
        String name = etfullname.getText().toString();
        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();
        String password2 = etpassword2.getText().toString();

        findViewById(R.id.error_email_regis_textView4).setVisibility(View.GONE);
        findViewById(R.id.error_pass_log_textView7).setVisibility(View.GONE);
        findViewById(R.id.errores_regist_textView8).setVisibility(View.GONE);

        if(usuario.length()>0 && etfullname.length()>0 && email.length()>0 && password.length()>0)
        {
            if (!validarEmail(email))
            {
                findViewById(R.id.error_email_regis_textView4).setVisibility(View.VISIBLE);
                return;
            }
            if(!password.equals(password2))
            {
                findViewById(R.id.error_pass_log_textView7).setVisibility(View.VISIBLE);
                etpassword.setText("");
                etpassword2.setText("");
                return;
            }
        }else {
            findViewById(R.id.errores_regist_textView8).setVisibility(View.VISIBLE);
            return;
        }

        RegisterUsuario userRegister =new RegisterUsuario(usuario, name, email, password,password2);

        Call<Usuario> call = apiInterface.addUser(userRegister);
        call.enqueue(new Callback<Usuario>()
        {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response)
            {
                if(response.isSuccessful())
                {
                    Log.d("AddUser", "Error addUser"+response.code());
                    Toast.makeText(RegisterActivity.this, "User name already registered" ,Toast.LENGTH_LONG).show();
                    return;
                }


                Usuario user = response.body();
                Toast.makeText(RegisterActivity.this, "Bienvenido" + user.getUsuario(), Toast.LENGTH_LONG).show();
                Log.d("Add Usuario", "Successful add ususario" + user.getUsuario());


                SharedPreferences sharedPrefence = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefence.edit();
                editor.putString("Usuario", userRegister.getUsuario());
                editor.putString("Password", userRegister.getPassword());
                Log.d("Add usuario", "Guardar usuario" + userRegister.getUsername());
                Log.d("Add Usuario", "Guardar password" + userRegister.getPassword());
                editor.commit();

                Intent intentMainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }

            public void onFailure(Call<Usuario> call, Throwable t)
            {
                Toast.makeText(RegisterActivity.this,"Error en la respuesta del servidor", Toast.LENGTH_LONG).show();
                Log.d("Add usuario", "Error Add usuario retrofit:"+ t.getMessage());
            }
        });
    }
    //validación de correo
    private boolean validarEmail(String email)
    {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void OnBackPress()
    {
        Intent intentLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intentLoginActivity);
        finish();
    }

}
