# ANDROID
Mas pruebas

button_register = findViewById(R.id.btn_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(editTextEmail.getText().toString()) || TextUtils.isEmpty(editTextPassword.getText().toString()) || TextUtils.isEmpty(editTextPasswordC.getText().toString()) || TextUtils.isEmpty(editTextUsername.getText().toString()) || TextUtils.isEmpty(editTextName.getText().toString()) || TextUtils.isEmpty(editTextLastname.getText().toString())){
                    String message = "All inputs are needed";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                    spinner.setVisibility(View.GONE);
                }else if (! editTextPassword.getText().toString().equals(editTextPasswordC.getText().toString())){
                    String message = "Passwords are not the same";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                    spinner.setVisibility(View.GONE);
                }
                else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setEmail(editTextEmail.getText().toString());
                    registerRequest.setPassword(editTextPassword.getText().toString());
                    registerRequest.setUsername(editTextUsername.getText().toString());
                    registerRequest.setName(editTextName.getText().toString());
                    registerRequest.setLastName(editTextLastname.getText().toString());
                    registerUser(registerRequest);}
            }
        });

        ackage com.example.loginregister;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.*;

public class ApiClient {

    public static Retrofit getRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        String URL = "http://147.83.7.206:80/dsaApp/";
        //String URL = "localhost:8080/dsaApp/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    return retrofit;
    }



    https://developer.android.com/develop/ui/views/launch/splash-screen?hl=es-419

    public static UserService getService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

}
