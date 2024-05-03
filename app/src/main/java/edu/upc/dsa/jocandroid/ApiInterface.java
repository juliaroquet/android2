package edu.upc.dsa.jocandroid;
import java.util.List;

import edu.upc.dsa.jocandroid.modelo.LoginUsuario;
import edu.upc.dsa.jocandroid.modelo.RegistroReq;
import edu.upc.dsa.jocandroid.modelo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface
{
    /** USUARIO *******************/

    @POST("Usuario/register")
    Call<Usuario> addUser(@Body RegistroReq user);

    @POST("Usuario/login")
    Call<LoginUsuario> loginUser(@Body LoginUsuario user);

    @GET("Usuario/logout/{userName}")
    Call<Void> logoutUser(@Path("userName") String userName);

    @GET("Usuario/{userName}")
    Call<Usuario> getUser(@Path("userName") String userName);

    @DELETE("user/{userName}")
    Call<Void> deleteUser(@Path("userName") String userName);

}
