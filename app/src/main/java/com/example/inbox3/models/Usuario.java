package com.example.inbox3.models;

public class Usuario {

    private String usuario;
    private String contrasenia;
    private int id;
    private int activo;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Usuario(String usuario, String contrasenia, int id, int activo) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.id = id;
        this.activo = activo;
    }

}
