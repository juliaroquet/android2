package edu.upc.dsa.jocandroid.modelo;

import java.io.Serializable;

public class Usuario implements Serializable
{

    private String nombre;
    private String username;
    private String password;
    private String email;
    private String verify_password;

    public Usuario(String nombre, String usuario, String password, String email) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    private String token;
    private String codigo;
    private String newPassword;


    public Usuario()
    {

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerify_password() {
        return verify_password;
    }

    public void setVerify_password(String verify_password) {
        this.verify_password = verify_password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}