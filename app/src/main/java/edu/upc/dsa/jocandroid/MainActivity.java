package edu.upc.dsa.jocandroid;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.upc.dsa.jocandroid.databinding.ActivityMainBinding;
import edu.upc.dsa.jocandroid.modelo.Usuario;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText etemaillog, etpasswlg;
    private Button btn_iniciar_sesion;
    private Button btn_login;
    TextView pantalla_resgistrar;
    private ArrayList<Usuario> listaUsuarios;
    private Usuario objUsuio;
    private RetroApiUsuer loginService;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.Login_Btn);
        etemaillog = findViewById(R.id.EmailLog_Text);
        etpasswlg = findViewById(R.id.PassLog_Text);
        pantalla_resgistrar = findViewById(R.id.RegistrarText_Btn);
        pantalla_resgistrar.setOnClickListener(this);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();//peticion a la API
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
               // .baseUrl("http://147.:8080//") host de la mv
                .baseUrl("http://10.0.2.2:8080//")//host local
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        pruebitas
        //RetroApiUsuerService loginretrofit = retrofit.create(RetroApiUsuer.class);
        loginService = retrofit.create(RetroApiUsuer.class);
        btn_login.setOnClickListener(this);
        getSupportActionBar().hide();
        Iniciar();
    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.Login_Btn) {
            IniciarSession();
        } else if (v.getId() == R.id.RegistrarText_Btn) {
            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(intent);
        }
    }

    private void Iniciar()
    {
        etemaillog = findViewById(R.id.EmailLog_Text);
        etpasswlg = findViewById(R.id.PassLog_Text);
        btn_login = findViewById(R.id.Login_Btn);
        btn_login.setOnClickListener(this);
    }

    private void IniciarSession()
    {
        Iniciar();
        String email = etemaillog.getText().toString().trim();
        String password = etpasswlg.getText().toString().trim();
        if (!email.isEmpty() && !password.isEmpty())
        {
            //LoginActivity loginBody = LoginActivity(email, password);
            Call<Usuario> call = LoginActivity.class(email, password);
           // Call<Usuario> call = loginService.login(email, password);
            call.enqueue(new Callback<Usuario>(){

            public void onResponse(Call<Usuario> call, Response<Usuario> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    etemaillog.getText().clear();
                    etpasswlg.getText().clear();
                    String tokenInter = response.body().getToken();

                    Intent intent = new Intent(MainActivity.this, InicioActivity.class );
                    intent.putExtra("token", tokenInter);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    finish();
                    } else
                    {
                        Toast.makeText(MainActivity.this, "Error de contraseña", Toast.LENGTH_SHORT).show();
                    }
                }
                public void onFailure(Call<Usuario> call, Throwable t)
                {
                    Toast.makeText(MainActivity.this, "Sin conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(MainActivity.this, "Por favor, complete los campos", Toast.LENGTH_SHORT).show();
        }

        if(v.getId() == R.id.RegistrarText_Btn) {
            Intent intentReg = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(intentReg);
        }
    }

    private void llUsuarios()
    {
        listaUsuarios =new ArrayList<Usuario>();
        //test
        listaUsuarios.add(new Usuario("Maria", "mariadsa", "maria@hotmail.com", "12345"));
        listaUsuarios.add(new Usuario("Juan", "juandsa", "juan@hotmail.com", "678910"));
        listaUsuarios.add(new Usuario("Pedro", "pedroadsa", "pedro@hotmail.com", "111213"));


    }

    //IniciarSesiion

    public void iniciar()
    {
        etemaillog = findViewById(R.id.EmailLog_Text);
        etpasswlg = findViewById(R.id.PassLog_Text);
        btn_iniciar_sesion =findViewById(R.id.Login_Btn);
        btn_iniciar_sesion.setOnClickListener(this);
    }

//    private void IniciarSesion()
//    {
//        llUsuarios();
//        String email= etemaillog.getText().toString();
//        String password= etpasswlg.getText().toString();
//        boolean usuarioEncontrado =false;
//        for (int i = 0; i< listaUsuarios.size();i++)
//        {
//            if (email.equals(listaUsuarios.get(i).getEmail()) && password.equals(listaUsuarios.get(i).getPassword()))
//            {
//                Toast.makeText(this,"Bienvenido"+ listaUsuarios.get(i).getNombre() , Toast.LENGTH_SHORT).show();
//                usuarioEncontrado = true;
//                Intent inicio = new Intent( MainActivity.this, InicioActivity.class);
//                objUsuio = listaUsuarios.get(i);
//                inicio.putExtra("Usuario", objUsuio);
//                startActivity(inicio);
//            }
//            else if (email.isEmpty() || password.isEmpty())
//            {
//                Toast.makeText(this, "Rellenar los campos", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//
//        if (!usuarioEncontrado)
//        {
//            Toast.makeText(this, "El email o password incorrectos", Toast.LENGTH_SHORT).show();
//        }
//    }

}