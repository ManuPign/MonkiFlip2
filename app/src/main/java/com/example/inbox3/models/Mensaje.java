package com.example.inbox3.models;

public class Mensaje {


    private int id;
    private String remitente;
    private String categoria;
    private String asunto;
    private String contenido;
    private String enlaceImagen;

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEnlaceImagen() {
        return enlaceImagen;
    }

    public void setEnlaceImagen(String enlaceImagen) {
        this.enlaceImagen = enlaceImagen;
    }

    public Mensaje(int id, String remitente, String correo, String asunto, String contenido, String color) {
        this.id = id;
        this.remitente = remitente;
        this.categoria = correo;
        this.asunto = asunto;
        this.contenido = contenido;
        this.enlaceImagen = color;
    }

    public Mensaje() {

    }
}
