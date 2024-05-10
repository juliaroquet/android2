package edu.upc.dsa.jocandroid;


import edu.upc.dsa.jocandroid.modelo.LoginUsuario;
import edu.upc.dsa.jocandroid.modelo.RegisterUsuario;
import edu.upc.dsa.jocandroid.modelo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;

public interface ApiInterface
{
    @POST("usuario/register")
    Call<Usuario> addUser(@Body RegisterUsuario usuario);

    @POST("user/login")
    Call<LoginUsuario> loginUser(@Body LoginUsuario usuario);

    @GET ("usuario/username")
    Call<Usuario> getUser(@Path("username") String username);

    @DELETE ("usuario/username")
    Call<Void> deleteUser(@Path("username") String username);



}

