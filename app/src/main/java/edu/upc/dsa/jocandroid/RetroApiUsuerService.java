package edu.upc.dsa.jocandroid;

import edu.upc.dsa.jocandroid.modelo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Field;



public interface RetroApiUsuerService
{
    //Metodos para Acceder a la API
    @FormUrlEncoded
    @POST("REGISTER") //nom EndPoint
    Call<Usuario> REGISTER_CALL(
            //Parametros y tipo de parametros
            @Field("Nombre") String nombre,
            @Field("emai") String email,
            @Field("Password") String password,
            @Field("veryfy_password") String veryfy_password
    );

    @FormUrlEncoded
    @POST ("login")
    Call<Usuario>LOGIN_CALL(
            @Field("Email") String email,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST ("verificar_email")
    Call<Usuario>VALIDATE_CALL(
            @Field("Email") String email,
            @Field("codigo") String codigo
    );

    @FormUrlEncoded
    @POST ("lOGOUT")
    Call<Usuario>LOGOUT_CALL(
            @Field("Token") String token
    );

}
