package edu.upc.dsa.jocandroid.modelo;

public class RegisterUsuario {
    private String fullname;

    private String username;

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    private String email;
    private String password;
    private String password2;

    public RegisterUsuario() {
    }


    public RegisterUsuario(String fullname, String username, String email, String password, String password2) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsuario() {
        return username;
    }


}