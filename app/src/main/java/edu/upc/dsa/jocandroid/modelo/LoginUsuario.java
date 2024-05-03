package edu.upc.dsa.jocandroid.modelo;

public class LoginUsuario


{
    public LoginUsuario()
    {

    }
    public LoginUsuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String email;
    public String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsuario() {


        return null;
    }
}
