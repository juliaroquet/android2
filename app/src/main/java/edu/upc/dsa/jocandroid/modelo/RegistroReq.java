package edu.upc.dsa.jocandroid.modelo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class RegistroReq
{
    private String usuario;
    private String password;
    private String password2;
    private String nombre;
    private String email;


    public RegistroReq()
    {

    }

    public RegistroReq(String usuario, String password, String password2, String nombre, String email) {
        this.usuario = usuario;
        this.password = password;
        this.password2 = password2;
        this.nombre = nombre;
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
